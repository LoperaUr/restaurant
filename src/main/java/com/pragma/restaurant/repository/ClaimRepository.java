package com.pragma.restaurant.repository;

import com.pragma.restaurant.entity.Claim;
import com.pragma.restaurant.util.StateClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findAllByClaimState(StateClaim state);

    List<Claim> findByRestaurant(String str);
}
