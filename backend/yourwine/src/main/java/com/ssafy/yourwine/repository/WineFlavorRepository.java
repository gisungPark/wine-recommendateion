package com.ssafy.yourwine.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.yourwine.model.entity.Flavor;
import com.ssafy.yourwine.model.entity.Wine;
import com.ssafy.yourwine.model.entity.WineFlavor;

public interface WineFlavorRepository extends JpaRepository<WineFlavor, Long> {

	List<WineFlavor> findByWine(Wine wine);

	List<WineFlavor> findByFlavor(Flavor flavor);

	@Query(value = "select * from wine_flavor "
			+ "where wine_id in " 
			+ "(select w.wine_id "
			+ "from review as r join wine as w " 
			+ "on r.wine_id = w.wine_id " 
			+ "where user_id = ?1) "
			+ "order by flavor_id", nativeQuery = true)
	List<WineFlavor> findAllByUserReview(String userId);
}
