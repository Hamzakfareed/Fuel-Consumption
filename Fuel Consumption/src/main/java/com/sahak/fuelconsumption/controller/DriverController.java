package com.sahak.fuelconsumption.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sahak.fuelconsumption.domain.Driver;
import com.sahak.fuelconsumption.services.DriverService;


@Controller
public class DriverController {

	@Autowired
	private DriverService driverService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerUser(ModelAndView modelAndView) {
		Driver driver = new Driver();
		modelAndView.getModel().put("driver", driver);
		modelAndView.setViewName("driver.register");
		return modelAndView;
	}

	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public ModelAndView createuser(ModelAndView modelAndView, @Valid Driver driver, BindingResult result) {

		modelAndView.setViewName("driver.register");

		if (result.hasErrors()) {
			System.out.println();
			System.out.println();
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {

				System.out.println("===================================================");
				System.out.println(error);
				System.out.println("===================================================");

			}
			System.out.println();
			System.out.println();
		}
		if (!result.hasErrors()) {
			driverService.saveDriver(driver);
			modelAndView.setViewName("redirect:/");
		}


		return modelAndView;
	}



}
