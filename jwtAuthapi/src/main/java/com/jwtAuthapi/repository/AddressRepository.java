package com.jwtAuthapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtAuthapi.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	

}
