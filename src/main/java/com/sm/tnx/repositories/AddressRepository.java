package com.sm.tnx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sm.tnx.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
