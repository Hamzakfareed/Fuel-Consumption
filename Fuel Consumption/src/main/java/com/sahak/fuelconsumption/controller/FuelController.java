package com.sahak.fuelconsumption.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sahak.fuelconsumption.domain.Driver;
import com.sahak.fuelconsumption.domain.Fuel;
import com.sahak.fuelconsumption.services.DriverService;
import com.sahak.fuelconsumption.services.FuelService;


@Controller
public class FuelController {
	String filename = "e:\\fuel_record.txt";
	@Autowired
	private FuelService fuelService;
	
	@Autowired
	private DriverService driverService;
	
	private Driver getDriver() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Driver driver = driverService.getUser(username);
		System.out.println("Driver :"+driver);
		return driver;
	}
	
	@RequestMapping("/fuel")
	public ModelAndView fuel(ModelAndView modelAndView) throws IOException {
		modelAndView.getModel().put("fuel", new Fuel());
		
		File file = new File(filename);
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String line = null;
		while((line = reader.readLine())!=null) {
			System.out.println(line);
		}
		reader.close();
		
		modelAndView.setViewName("web.fuel");
		return modelAndView;
		
	}
	@RequestMapping(value="/fuel_added",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Fuel> addFuel(@ModelAttribute("fuel")@Valid Fuel fuel,BindingResult result) {
		
		Fuel webFuel = fuel.getWebFuel(fuel);
		if(result.hasErrors()) {
			return new ResponseEntity<Fuel>(webFuel,HttpStatus.EXPECTATION_FAILED);
		}
		
		Driver driver = getDriver();
		fuel.setDriver(driver);
		fuelService.save(fuel);
		
		
		try {

			File newFile = new File(filename);
			FileWriter fileWriter = new FileWriter(newFile,true);
			BufferedWriter br = new BufferedWriter(fileWriter);
			PrintWriter writer = new PrintWriter(br);
			
			if(newFile.exists() == false) {
				newFile.createNewFile();
			}
//			String r = String.format("%s%d %20s%d %20s%d %20s%s %20s%s", "Type:",fuel.getType(),"Price:",fuel.getPrice(),"Liter:",fuel.getVolumeInLiter(),"Date:",fuel.getAdded(),"Driver:",fuel.getDriver().getEmail());
			writer.print("Type:"+fuel.getType()+",");
			writer.print("Price:"+fuel.getPrice()+",");
			writer.print("Litter:"+fuel.getVolumeInLiter()+",");
			writer.print("Date:"+fuel.getAdded()+",");
			writer.print("Driver ID:"+fuel.getDriver().getEmail());
//			writer.print(r);
			writer.println();
			writer.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		 return new ResponseEntity<Fuel>(webFuel,HttpStatus.OK);
	}

}
