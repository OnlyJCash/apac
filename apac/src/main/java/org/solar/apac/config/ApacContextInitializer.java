/**
 *
 */
package org.solar.apac.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.solar.apac.social.FacebookService;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.kevinsawicki.http.HttpRequest;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.User;
import facebook4j.auth.AccessToken;

/**
 * @author michele.mazzilli
 *
 */
public class ApacContextInitializer implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		Facebook facebook = new FacebookFactory().getInstance();
		Environment env = webApplicationContext.getEnvironment();

		String appId = env.getRequiredProperty("spring.social.facebook.appId", String.class);
		String appSecret = env.getRequiredProperty("spring.social.facebook.appSecret", String.class);

		facebook.setOAuthAppId(appId, appSecret);

		facebook.setOAuthPermissions("email");

		String oAuthAuthorizationURL = facebook.getOAuthAuthorizationURL("http://local-apac.altervista.org:8080/connect/callback");
		System.out.println("oAuthAuthorizationURL: " + oAuthAuthorizationURL);

		FacebookService facebookService = webApplicationContext.getBean(FacebookService.class);
		facebookService.setProvider(facebook);

		try {
			AccessToken oAuthAppAccessToken = facebook.getOAuthAppAccessToken();
			facebook.setOAuthAccessToken(oAuthAppAccessToken);
			User user = facebook.getUser("855047511268896");

			System.out.println(user);
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpRequest.get(oAuthAuthorizationURL).code();
	}

}
