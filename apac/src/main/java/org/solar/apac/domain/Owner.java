/**
 *
 */
package org.solar.apac.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author michele.mazzilli
 *
 */
@Entity
@Table
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Owner extends Person {

	private static final long serialVersionUID = 8892449024259592397L;

	@OneToMany
	@JoinTable(name="owner_mean", joinColumns=@JoinColumn(name="owner_id"), inverseJoinColumns=@JoinColumn(name="mean_id"))
	private Set<Mean> means;

	/**
	 * @return the means
	 */
	public Set<Mean> getMeans() {
		return means;
	}

	/**
	 * @param means the means to set
	 */
	public void setMeans(Set<Mean> means) {
		this.means = means;
	}
}
