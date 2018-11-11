package com.sahak.fuelconsumption.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sahak.fuelconsumption.App;
import com.sahak.fuelconsumption.domain.Driver;
import com.sahak.fuelconsumption.domain.Fuel;
import com.sahak.fuelconsumption.services.DriverService;
import com.sahak.fuelconsumption.services.FuelService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
@Transactional
public class FuelServiceTest {
	
	@Autowired
	private FuelService fuelService;
	
	@Autowired
	private DriverService driverService;
	 
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	private ArrayList<Fuel> fuelsList = new ArrayList<Fuel>();
	

	@Test
	@WithMockUser(username="test@gmail.com")
	public void testSaveAndDeleteFuel() throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		Driver driver = driverService.getUser(email);
		//id 433
		Fuel fuel = new Fuel(77,3,53,new Date());
		 fuel.setDriver(driver);
		
	   mockMvc.perform(post("/fuel_added").flashAttr("fuel",fuel )).andExpect(status().isOk());
	  
	   fuelService.save(fuel);
	   assertEquals("exptected retrieved and save fuels  are equal",fuel,fuelService.getAllFuelByDriver(driver).get(0));
	}
	
	
	@Test
	@WithMockUser(username="test@gmail.com")
	public void testgetAllFuelByDriver() {
		fuelsList.add(new Fuel(12,4,5,new Date()));
		fuelsList.add(new Fuel(13,4,53,new Date()));
		fuelsList.add(new Fuel(14,3,53,new Date()));
		fuelsList.add(new Fuel(55,3,53,new Date()));
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Driver driver = driverService.getUser(email);
		for(int i=0; i < fuelsList.size();i++) {
			Fuel fuel = fuelsList.get(i);
			fuel.setDriver(driver);
			fuelService.save(fuel);
		}
		
		ArrayList<Fuel> fuels = fuelService.getAllFuelByDriver(driver);
		
		
		assertEquals("Insert values and retrieved values size must be equals:",fuelsList.size(),fuels.size() );
		
	
		
	}
	
	
	@Test
	@WithMockUser(username="test@gmail.com")
	public void testFuelByType() {
		
		//type = 12 testing
		fuelsList.add(new Fuel(12,4,5,new Date()));
		fuelsList.add(new Fuel(12,4,53,new Date()));
		fuelsList.add(new Fuel(14,3,53,new Date()));
		fuelsList.add(new Fuel(55,3,53,new Date()));
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Driver driver = driverService.getUser(email);
		for(int i=0; i < fuelsList.size();i++) {
			Fuel fuel = fuelsList.get(i);
			fuel.setDriver(driver);
			fuelService.save(fuel);
		}
		
		ArrayList<Fuel> fuels = fuelService.getByType(12);
		assertEquals("Insert values and retrieved values size must be 2:",2,fuels.size() );
	}
	
	
	@Test
	@WithMockUser(username="test@gmail.com")
	public void testFuelByMonth() {
		
		//type = 12 testing
		fuelsList.add(new Fuel(12,4,5,new Date()));
		fuelsList.add(new Fuel(12,4,53,new Date()));
		fuelsList.add(new Fuel(14,3,53,new Date()));
		fuelsList.add(new Fuel(55,3,53,new Date()));
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Driver driver = driverService.getUser(email);
		for(int i=0; i < fuelsList.size();i++) {
			Fuel fuel = fuelsList.get(i);
			fuel.setDriver(driver);
			fuelService.save(fuel);
		}
		
		ArrayList<Fuel> fuels = fuelService.getByMonth(new Date());
		
		
		//all values are added on same month so the size should be equal to 4
		assertEquals("Insert values and retrieved values size must be 4 :",fuelsList.size(),fuels.size() );
	}
	
	
	

	
	
	

}
