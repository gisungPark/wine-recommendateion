package com.ssafy.yourwine.repository;

import java.util.List;

import javax.persistence.OrderColumn;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.yourwine.model.entity.Food;
import com.ssafy.yourwine.model.entity.Wine;
import com.ssafy.yourwine.model.entity.WineFoodMatch;

public interface WineFoodMatchRepository extends JpaRepository<WineFoodMatch, Long>{

	List<WineFoodMatch> findByWine (Wine wine);
	
	@Query( value = "select * from wine_food_match as f join wine as w on f.wine_id = w.wine_id where food_id = ? order by w.avg DESC", nativeQuery = true)
	List<WineFoodMatch> findByFood (Food food, Pageable pageable);
}
