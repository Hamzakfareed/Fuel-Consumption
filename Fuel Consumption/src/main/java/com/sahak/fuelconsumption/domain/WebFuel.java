package com.sahak.fuelconsumption.domain;

import java.util.Date;

public class WebFuel {
	private int type;
	private float price;
	private float volumeInLiter;
	private Date date;
	private long driver;
	
	public WebFuel(int type,float price,float volumeInLiter,Date date,long driver) {
		this.type =type;
		this.price = price;
		this.volumeInLiter = volumeInLiter;
		this.date = date;
		this.driver = driver;
	}
	
	public WebFuel getWebFuel() {
		return new WebFuel(type,price,volumeInLiter,date,driver);
	}
}
