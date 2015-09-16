/**
 *
 */
package org.solar.apac.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author michele.mazzilli
 *
 */
@MappedSuperclass
public abstract class Identifier implements Serializable {

	private static final long serialVersionUID = 4118245906901333237L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", insertable=true, updatable=false, nullable=false)
	private Long id;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
