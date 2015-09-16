/**
 *
 */
package org.solar.apac.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author michele.mazzilli
 *
 */
@Entity
@Table
public class Person extends Identifier {

	private static final long serialVersionUID = 655142730628513658L;

	@Column
	private String surname;
	@Column
	private String name;
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="person_contact", joinColumns=@JoinColumn(name="person_id"), inverseJoinColumns=@JoinColumn(name="contact_id"))
	private Set<Contact> contacts;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="person_address", joinColumns=@JoinColumn(name="person_id"), inverseJoinColumns=@JoinColumn(name="address_id"))
	private Set<Address> addresses;

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the contacts
	 */
	public Set<Contact> getContacts() {
		return contacts;
	}
	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	/**
	 * @return the addresses
	 */
	public Set<Address> getAddresses() {
		return addresses;
	}
	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}
