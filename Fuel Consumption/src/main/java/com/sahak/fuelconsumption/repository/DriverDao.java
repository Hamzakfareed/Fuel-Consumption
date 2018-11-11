package com.sahak.fuelconsumption.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sahak.fuelconsumption.domain.Driver;



@Repository
public interface DriverDao extends CrudRepository<Driver,Long> {
	public Driver findByEmail(String email);

}
