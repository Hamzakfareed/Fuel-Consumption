package com.sahak.fuelconsumption.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sahak.fuelconsumption.App;
import com.sahak.fuelconsumption.domain.Driver;
import com.sahak.fuelconsumption.services.DriverService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
@Transactional

public class DriverServiceTest {
	
	
	@Autowired
	private  DriverService driverService;
	
	@Test
	public void testSaveDriver() {
	ArrayList<Driver> drivers = new ArrayList<Driver>();
	drivers.add(new Driver("Hamza","sahak","Iamhsk1@gmail.com","ghost111",1));
	drivers.add(new Driver("ema","hoston","ema@gmail.com","emahoston",1));
	drivers.add(new Driver("tom","cruise","tom@gmail.com","cruise111",1));
	drivers.add(new Driver("johny","deep","john@gmail.com","johndeep",1));
	}

	
	
	@Test
	public void getDriver() {
		Driver driver = new Driver("Hamza","sahak","Iamhsk1@gmail.com","ghost111",1);
		driverService.saveDriver(driver);
		Driver retreivedDriver = driverService.getUser(driver.getEmail());
		assertEquals("Driver retrived:",driver,retreivedDriver);
		
	}
	
	public void testPrivateMethodDeleteDriver() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = DriverService.class.getDeclaredMethod("deleteDriver", Driver.class);
		method.setAccessible(true);
		
		Driver driver = new Driver("Hamza","sahak","Iamhsk1@gmail.com","ghost111",1);
		driverService.save(driver);
		
		Driver retreivedDriver = driverService.getUser(driver.getEmail());
		assertEquals("Driver retrived:",driver,retreivedDriver);
		
		method.invoke(driverService, driver);
		
		Driver r = driverService.getUser(driver.getEmail());
		assertNull("Driver retrived  must be null:",r);
		
		
	}
}
