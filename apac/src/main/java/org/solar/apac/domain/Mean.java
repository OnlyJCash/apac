/**
 *
 */
package org.solar.apac.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author michele.mazzilli
 *
 */
@Entity
@Table
public class Mean extends Identifier {

	private static final long serialVersionUID = -477368656704939790L;

	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String sequenceId;
	@Column
	@Temporal(TemporalType.DATE)
	private Date purchaseDate;

	@OneToMany
	@JoinTable(name="mean_tax", joinColumns=@JoinColumn(name="mean_id"), inverseJoinColumns=@JoinColumn(name="tax_id"))
	private Set<Tax> payedTaxes;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the sequenceId
	 */
	public String getSequenceId() {
		return sequenceId;
	}
	/**
	 * @param sequenceId the sequenceId to set
	 */
	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}
	/**
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	/**
	 * @return the payedTaxes
	 */
	public Set<Tax> getPayedTaxes() {
		return payedTaxes;
	}
	/**
	 * @param payedTaxes the payedTaxes to set
	 */
	public void setPayedTaxes(Set<Tax> payedTaxes) {
		this.payedTaxes = payedTaxes;
	}

}
