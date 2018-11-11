package com.sahak.fuelconsumption.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahak.fuelconsumption.domain.Driver;
import com.sahak.fuelconsumption.domain.Fuel;
import com.sahak.fuelconsumption.repository.FuelDao;

@Service
public class FuelService {
	@Autowired
	private FuelDao fuelDao;
	
	public void save(Fuel fuel) {
		fuelDao.save(fuel);
		
	}
	


	public ArrayList<Fuel> getAllFuelByDriver(Driver driver) {
		return  fuelDao.findByDriver(driver);
	}

	public double getSumGroupByMonth(Driver driver) {
		return  fuelDao.getSumGroupByMonth();
	}
	
	public ArrayList<Fuel> getByType(int type) {
		return fuelDao.findAllGroupByType(type);
	}
	
	public ArrayList<Fuel> getByMonth(Date date) {
		return fuelDao.findByMonth(date);
	}



	public Fuel getFuel(long id) {
		// TODO Auto-generated method stub
		return fuelDao.findOne(id);
	}
	
}
