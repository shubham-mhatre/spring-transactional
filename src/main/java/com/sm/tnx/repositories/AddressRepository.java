package com.sm.tnx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sm.tnx.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
