package com.ssafy.yourwine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yourwine.model.entity.Grape;
import com.ssafy.yourwine.model.entity.Wine;

public interface WineRepository extends JpaRepository<Wine, Long>{

	Wine findByWineId(Long wineId);
	
	List<Wine> findByGrape (Grape grape);
	List<Wine> findByAvgGreaterThanEqual(float avg);
}
