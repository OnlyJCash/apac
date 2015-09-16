/**
 *
 */
package org.solar.apac.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.solar.apac.rs.converter.Exclude;

/**
 * @author michele.mazzilli
 *
 */
@Entity
@Table
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class User extends Owner {

	private static final long serialVersionUID = -2572877669389148162L;

	public enum Role { ADMIN, SIMPLE }

	@Column(name="email", unique=true, nullable=false, updatable=false)
	private String email;
	@Column(name="password")
	private String password;

	@Column
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column
	@Exclude
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column
	@Exclude
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column
	private String firstAccessToken;

	@Column
	private Boolean privacy;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the firstAccessToken
	 */
	public String getFirstAccessToken() {
		return firstAccessToken;
	}

	/**
	 * @param firstAccessToken the firstAccessToken to set
	 */
	public void setFirstAccessToken(String firstAccessToken) {
		this.firstAccessToken = firstAccessToken;
	}

	/**
	 * @return the privacy
	 */
	public Boolean getPrivacy() {
		return privacy;
	}

	/**
	 * @param privacy the privacy to set
	 */
	public void setPrivacy(Boolean privacy) {
		this.privacy = privacy;
	}

}
