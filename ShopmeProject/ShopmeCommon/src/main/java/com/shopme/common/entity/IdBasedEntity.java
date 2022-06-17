package com.shopme.common.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//A mapped superclass has no separate table defined for it. 
//A class designated with the MappedSuperclass annotation can be mapped in the same way as an entity except that the mappings 
//will apply only to its subclasses since no table exists for the mapped superclass itself.

@MappedSuperclass
public abstract class IdBasedEntity {
	@Id  //To indicate that it is the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	This GenerationType indicates that the persistence provider must assign primary keys for the entity using a database identity column
	protected Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
}