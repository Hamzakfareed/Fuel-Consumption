package com.sahak.fuelconsumption.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="fuel")
public class Fuel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="fuel_type")
	@Min(value=1)
	@NotNull(message="${NotNull.fuel.type}")
	
	private int type;
	
	@NotNull(message="${NotNull.fuel.volume}")
	@Column(name="volume_in_liter")
	@Min(value=1)
	private float volumeInLiter;
	
	@Column(name="price")
	@Min(value=1)
	@NotNull(message="${NotNull.fuel.price}")
	private float price;
	
	@Column(name="total_price")
	private float totalPrice;
	
	@OneToOne(targetEntity = Driver.class)
	@JoinColumn(name = "driver", nullable = false)
	private Driver driver;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy/MM/dd hh/mm/ss")
	private Date added;

	public Fuel() {
		
	}
	
	
	public Fuel(int type, float volumeInLiter, float price, Date added) {
	
		this.type = type;
		this.volumeInLiter = volumeInLiter;
		this.price = price;
	
		this.added = added;
	}

	public Fuel(int id,int type, float volumeInLiter, float price, Date added) {
		this.id = id;
		this.type = type;
		this.volumeInLiter = volumeInLiter;
		this.price = price;
	
		this.added = added;
	}



	@PrePersist
	protected void onCreated() {
		if (added == null) {
			added = new Date();
		}
	}


	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getVolumeInLiter() {
		return volumeInLiter;
	}

	public void setVolumeInLiter(float volumeInLiter) {
		this.volumeInLiter = volumeInLiter;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public void setTotal() {
		this.totalPrice = price * volumeInLiter;
	}

	public float getTotalPrice() {
		return totalPrice;
	}
/*	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
*/
	
	
	

	@Override
	public String toString() {
		return "Fuel [id=" + id + ", type=" + type + ", volumeInLiter=" + volumeInLiter + ", price=" + price
				+ ", driver="  + "]";
	}




	public Driver getDriver() {
		return driver;
	}




	public void setDriver(Driver driver) {
		this.driver = driver;
	}




	public Date getAdded() {
		return added;
	}




	public void setAdded(Date added) {
		this.added = added;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((added == null) ? 0 : added.hashCode());
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + Float.floatToIntBits(totalPrice);
		result = prime * result + type;
		result = prime * result + Float.floatToIntBits(volumeInLiter);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fuel other = (Fuel) obj;
		if (added == null) {
			if (other.added != null)
				return false;
		} else if (!added.equals(other.added))
			return false;
		if (driver == null) {
			if (other.driver != null)
				return false;
		} else if (!driver.equals(other.driver))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (Float.floatToIntBits(totalPrice) != Float.floatToIntBits(other.totalPrice))
			return false;
		if (type != other.type)
			return false;
		if (Float.floatToIntBits(volumeInLiter) != Float.floatToIntBits(other.volumeInLiter))
			return false;
		return true;
	}


	public Fuel getWebFuel(Fuel fuel) {
		if(fuel.type != 0 ) {
			this.type= fuel.getType();
		}
		
		if(fuel.price !=0) {
			this.price = fuel.getPrice();
		}
		
		if(fuel.driver !=null) {
			this.driver = fuel.getDriver();
		}
		
		if(fuel.volumeInLiter !=0) {
			this.volumeInLiter = fuel.getVolumeInLiter();
		}
		
		return this;
	}
	
	
	
	

}
