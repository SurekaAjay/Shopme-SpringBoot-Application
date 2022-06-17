package com.shopme.admin.setting.state;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shopme.common.entity.Country;
import com.shopme.common.entity.State;

//interface for generic CRUD operations on a repository of a specific type
public interface StateRepository extends CrudRepository<State, Integer> {
	
	public List<State> findByCountryOrderByNameAsc(Country country);
}