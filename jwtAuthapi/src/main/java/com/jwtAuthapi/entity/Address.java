package com.jwtAuthapi.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "address")
public class Address {
	
	@Id
	private int id;
	
	@Column(name = "street_name")
    private String streetName;
	
	@Column(name = "city")
    private String city;
	

	public Address() {
		super();
	}

	public Address(int id, String streetName, String city, List<Employee> employee) {
		super();
		this.id = id;
		this.streetName = streetName;
		this.city = city;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	
	

}
