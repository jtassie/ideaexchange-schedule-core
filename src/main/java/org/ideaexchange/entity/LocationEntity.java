package org.ideaexchange.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LocationEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocationEntity(){
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	public Long getId() {
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}