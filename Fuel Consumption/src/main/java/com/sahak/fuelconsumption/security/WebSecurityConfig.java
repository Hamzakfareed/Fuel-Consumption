package com.sahak.fuelconsumption.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sahak.fuelconsumption.services.DriverService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DriverService driverService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off

		http.authorizeRequests()
								.antMatchers("/",
										"/login",
										"/register",
										"/createuser")
												.permitAll()
								.antMatchers("/fuel",
										"/fuel_added")
										.authenticated()
								.and()
								.formLogin()
									.loginPage("/login")
									.defaultSuccessUrl("/")
									.permitAll()
									.and()
									.logout()
									.logoutSuccessUrl("/login")
									.permitAll();
		// @formatter:on
		
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(driverService).passwordEncoder(passwordEncoder);
	}

	
}
