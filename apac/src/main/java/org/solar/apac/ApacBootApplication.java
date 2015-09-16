/**
 *
 */
package org.solar.apac;

import org.solar.apac.config.MvcBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author michele.mazzilli
 *
 */
@SpringBootApplication
public class ApacBootApplication extends MvcBootConfiguration {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApacBootApplication.class, args);
	}

}
