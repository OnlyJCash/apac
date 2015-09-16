/**
 *
 */
package org.solar.apac.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * @author michele.mazzilli
 *
 */
@Entity
@Table
public class Contact extends Identifier {

	private static final long serialVersionUID = -5403160756701969628L;

	public enum ContactType{ PHONE, EMAIL}

	@Column
	@Enumerated(EnumType.STRING)
	private ContactType type;
	@Column
	private String value;

	/**
	 * @return the type
	 */
	public ContactType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(ContactType type) {
		this.type = type;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
