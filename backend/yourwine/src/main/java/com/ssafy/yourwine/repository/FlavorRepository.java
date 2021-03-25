package com.ssafy.yourwine.repository;

import com.ssafy.yourwine.model.entity.Flavor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlavorRepository extends JpaRepository<Flavor, Integer>{
    Flavor findById(int flavorId);
}
