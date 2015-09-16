/**
 *
 */
package org.solar.apac.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author michele.mazzilli
 *
 */
@Entity
@Table
public class Tax extends Identifier {


	private static final long serialVersionUID = 235567219252975328L;

	private String year;

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

}
