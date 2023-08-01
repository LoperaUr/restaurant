package com.pragma.restaurant.repository;

import com.pragma.restaurant.entity.Claim;
import com.pragma.restaurant.entity.Client;
import com.pragma.restaurant.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {


}
