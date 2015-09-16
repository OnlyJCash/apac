/**
 *
 */
package org.solar.apac.common;

/**
 * Project Versions
 *
 * @author michele.mazzilli
 *
 */
public enum Version {

	V_1("v1.0");

	private String code;

	private Version(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	public static Version by(String version){
		// TODO
		return V_1;
	}

}
