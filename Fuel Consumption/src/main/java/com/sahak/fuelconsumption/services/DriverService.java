package com.sahak.fuelconsumption.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sahak.fuelconsumption.domain.Driver;
import com.sahak.fuelconsumption.repository.DriverDao;


@Service
public class DriverService implements UserDetailsService {
	
	@Autowired
	private DriverDao driverDao;
	
	
	
	public void saveDriver(Driver driver) {
		driver.setRole("ROLE_USER");
		driver.setEnabled(1);
		driverDao.save(driver);
	}
	
	public void save(Driver user) {
		driverDao.save(user);
	}
	
	public Driver getUser(String email) {
		return driverDao.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println();
		System.out.println("=====================");
		System.out.println("Email	 :"+email);
		System.out.println("=====================");
		System.out.println();
		Driver driver = driverDao.findByEmail(email);
		System.out.println();
		System.out.println("=====================");
		System.out.println("User :"+driver);
		System.out.println("=====================");
		System.out.println();
		if(driver == null) {
			return null;
		}
	List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(driver.getRole());
	
		
	User u = new User(email,driver.getPassword(),true,true,true,true,auth);
		
		return u;
	}
	
	private void deleteDriver(Driver driver) {
		driverDao.delete(driver);
		
	}

}
