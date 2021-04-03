package com.ssafy.yourwine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yourwine.model.entity.BatchData;
import com.ssafy.yourwine.model.entity.User;

public interface BatchRepository extends JpaRepository<BatchData, String> {

	BatchData findByUserId (String userId);
}
