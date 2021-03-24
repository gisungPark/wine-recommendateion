package com.ssafy.yourwine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yourwine.model.entity.Grape;


public interface GrapeRepository extends JpaRepository<Grape, Integer>{

	Grape findByGrapeId(int grapeId);
	
}
