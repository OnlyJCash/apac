/**
 *
 */
package org.solar.apac.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.solar.apac.exception.AuthenticationException;
import org.solar.apac.exception.CustomExceptionHandler;
import org.solar.apac.rs.converter.CustomGsonHttpMessageConverter;
import org.solar.apac.rs.interceptor.AuthRestApiInterceptor;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import cz.jirutka.spring.exhandler.RestHandlerExceptionResolver;



/**
 * @author michele.mazzilli
 *
 */
public abstract class MvcBootConfiguration extends WebMvcConfigurerAdapter {

	// @Bean
	public ApacContextInitializer configureContextInitializer(){
		return new ApacContextInitializer();
	}

	@Bean
	public FilterRegistrationBean configureFilter(ServletRegistrationBean servletRegistrationBean){
		FilterRegistrationBean filterBean = new FilterRegistrationBean();
		filterBean.addServletRegistrationBeans(servletRegistrationBean);
		CorsFilter corsFilter = new CorsFilter();
		filterBean.addInitParameter(CorsFilter.PARAM_CORS_ALLOWED_HEADERS, CorsFilter.DEFAULT_ALLOWED_HTTP_HEADERS + ", sessionToken");
		filterBean.setFilter(corsFilter);
		return filterBean;
	}

	@Bean
	public AuthRestApiInterceptor getAuthRestApiInterceptor(){
		return new AuthRestApiInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(getAuthRestApiInterceptor()).excludePathPatterns("/api/**/register", "/api/**/token", "/api/**/firstAccess/**").addPathPatterns("/**");
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add( exceptionHandlerExceptionResolver() ); // resolves @ExceptionHandler
		resolvers.add( restExceptionResolver() );
	}

	@Bean
	public RestHandlerExceptionResolver restExceptionResolver() {
		return RestHandlerExceptionResolver.builder()
				.defaultContentType(MediaType.APPLICATION_JSON)
				.addErrorMessageHandler(EmptyResultDataAccessException.class, HttpStatus.NOT_FOUND)
				.addErrorMessageHandler(IllegalArgumentException.class, HttpStatus.BAD_REQUEST)
				.addHandler(AuthenticationException.class, new CustomExceptionHandler())
				.build();
	}


	@Bean
	public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
		ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(getMessageConverter());
		resolver.setMessageConverters(messageConverters);
		return resolver;
	}

	@Bean
	public HttpMessageConverter<Object> getMessageConverter(){
		return new CustomGsonHttpMessageConverter();
	}
}
