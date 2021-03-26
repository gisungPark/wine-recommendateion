package com.ssafy.yourwine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yourwine.model.entity.Grape;


public interface GrapeRepository extends JpaRepository<Grape, Long>{

	Grape findByGrapeId(Long grapeId);
	
}
