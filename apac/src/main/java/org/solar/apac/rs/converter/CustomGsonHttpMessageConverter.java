/**
 *
 */
package org.solar.apac.rs.converter;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

/**
 * @author michele.mazzilli
 *
 */
public class CustomGsonHttpMessageConverter extends GsonHttpMessageConverter {

	public CustomGsonHttpMessageConverter() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setExclusionStrategies(new ExclusionTypeStrategy());
		setGson(gsonBuilder.create());
	}

	/**
	 *
	 * @author michele.mazzilli
	 *
	 */
	private static class ExclusionTypeStrategy implements ExclusionStrategy{

		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return clazz.isAnnotationPresent(Exclude.class);
		}

		@Override
		public boolean shouldSkipField(FieldAttributes field) {
			return field.getAnnotation(Exclude.class) != null;
		}

	}
}
