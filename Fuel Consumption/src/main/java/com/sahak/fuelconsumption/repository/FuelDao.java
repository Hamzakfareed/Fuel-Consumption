package com.sahak.fuelconsumption.repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sahak.fuelconsumption.domain.Driver;
import com.sahak.fuelconsumption.domain.Fuel;

@Repository
public interface FuelDao extends CrudRepository<Fuel,Long> {
	public ArrayList<Fuel> findByDriver(Driver driver);
	public ArrayList<Fuel> findAllGroupByDriver(Driver driver);
	
	public ArrayList<Fuel> findAllGroupByPrice(float price);
	
	@Query("select sum(f.price)  from Fuel f group by  month(f.added)")
	public Double getSumGroupByMonth();

	public ArrayList<Fuel>findAllGroupByType(int type);
	
	@Query("select f from Fuel f where month(f.added) = month(:#{#date})")
	public ArrayList<Fuel> findByMonth(@Param("date")Date date);
}
