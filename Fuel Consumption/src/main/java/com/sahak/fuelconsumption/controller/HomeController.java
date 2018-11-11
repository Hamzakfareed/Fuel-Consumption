package com.sahak.fuelconsumption.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sahak.fuelconsumption.domain.Driver;
import com.sahak.fuelconsumption.domain.Fuel;
import com.sahak.fuelconsumption.services.DriverService;
import com.sahak.fuelconsumption.services.FuelService;

@Controller
public class HomeController {
	
	@Autowired
	private FuelService fuelService;
	@Autowired
	private DriverService driverService;
	
	private Driver getDriver() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		return driverService.getUser(email);
		
	}
	@RequestMapping("/")
	public ModelAndView showHome(ModelAndView modelAndView) {
		ArrayList fuels = fuelService.getAllFuelByDriver(getDriver());
		modelAndView.getModel().put("fuels", fuels);
		modelAndView.setViewName("web.home");
		return modelAndView;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView modelAndView) {
		
		modelAndView.setViewName("driver.login");
		return modelAndView;
	}
	
	//GSON format output
	/*
	@RequestMapping(value="/",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> showHome(ModelAndView modelAndView) {
		ArrayList<Fuel> fuels = fuelService.getAllFuelByDriver(getDriver());
		
		modelAndView.getModel().put("fuels", fuels);
		modelAndView.setViewName("web.home");
		return new ResponseEntity<ArrayList<Fuel>>(fuels,HttpStatus.OK);
	}
	
	
	*/
	

}
