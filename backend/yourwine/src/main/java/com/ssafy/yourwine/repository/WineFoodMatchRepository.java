package com.ssafy.yourwine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yourwine.model.entity.Food;
import com.ssafy.yourwine.model.entity.Wine;
import com.ssafy.yourwine.model.entity.WineFoodMatch;

public interface WineFoodMatchRepository extends JpaRepository<WineFoodMatch, Long>{

	List<WineFoodMatch> findByWine (Wine wine);
	List<WineFoodMatch> findByFood (Food food);
}
