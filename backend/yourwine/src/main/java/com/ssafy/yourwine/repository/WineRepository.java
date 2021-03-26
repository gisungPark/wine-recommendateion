package com.ssafy.yourwine.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ssafy.yourwine.model.entity.Wine;

public interface WineRepository extends JpaRepository<Wine, Long>{

	Wine findByWineId(Long wineId);
	
}
