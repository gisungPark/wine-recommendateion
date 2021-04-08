package com.ssafy.yourwine.repository;

import java.util.List;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.yourwine.model.entity.Flavor;
import com.ssafy.yourwine.model.entity.Grape;
import com.ssafy.yourwine.model.entity.Wine;
import com.ssafy.yourwine.model.entity.WineFlavor;

public interface WineRepository extends JpaRepository<Wine, Long> {

	Wine findByWineId(Long wineId);

	List<Wine> findByGrape(Grape grape);

	Page<Wine> findAll(Pageable pageable);

	List<Wine> findAll();

	
	//유저가 쓴 리뷰기준 와인 리스트 
	@Query(value = "select w.* from review as r join wine as w "
			+ "on r. wine_id = w.wine_id "
			+ "where user_id = ?1", nativeQuery = true)
	List<Wine> findAllByUserReview (String userId);
	
	// 선호도 다 입력할 경우
	@Query(value = "select * from wine where wine_id in "
			+ "(select distinct(w.wine_id) from wine_flavor as w where w.flavor_id in ?1 "
			+ "and w.wine_id not in (select distinct(wine_id) from wine_flavor where flavor_id in ?2) "
			+ "order by w.wine_id) "
			+ "order by avg DESC", nativeQuery = true)
	List<Wine> findAllByFlavorList(List<Flavor> likeFlavor, List<Flavor> disLikeFlavor, Pageable pageable);

	// 좋아하는 선호도만 입력할 경우
	@Query(value = "select * from wine where wine_id in "
			+ "(select distinct(wine_id) from wine_flavor where flavor_id in ?1) "
			+ "order by avg DESC", nativeQuery = true)
	List<Wine> findAllByLikeFlavor(List<Flavor> likeFlavor, Pageable pageable);

	// 선호도 다 입력 + 맛필터링
	@Query(value ="select * from wine "
			+ "where (acidity between ?3 and ?4) "
			+ "and (sweet between ?5 and ?6) "
			+ "and (body between ?7 and ?8) "
			+ "and (tannin between ?9 and ?10) "
			+ "and wine_id in "
			+ "(select distinct(w.wine_id) from wine_flavor as w where w.flavor_id in ?1 "
			+ "and w.wine_id not in "
			+ "(select distinct(wine_id) from wine_flavor where flavor_id in ?2) order by w.wine_id)"
			+ "order by avg DESC"
			, nativeQuery = true)
	List<Wine> findAllByTaste (List<Flavor> likeFlavor, List<Flavor> disLikeFlavor, 
			int startAcidity, int endAcidity,
			int startSweet, int endSweet, 
			int startBody, int endBody,
			int startTannin, int endTannin,
			Pageable pageable
 );
	
	//좋아하는 선호도만 입력 + 맛필터링
	@Query(value = "select * from wine "
			+ "where (acidity between ?2 and ?3)"
			+ " and (sweet between ?4 and ?5)"
			+ " and (body between ?6 and ?7)"
			+ " and (tannin between ?8 and ?9)"
			+ " and wine_id in"
			+ "(select distinct(wine_id) from wine_flavor where flavor_id in ?1) "
			+ "order by avg DESC", nativeQuery = true)
	List<Wine> findAllByTasteLikeFlavor (List<Flavor> likeFlavor,
			int startAcidity, int endAcidity,
			int startSweet, int endSweet, 
			int startBody, int endBody,
			int startTannin, int endTannin,
			Pageable pageable
 );
	
	@Query(value = "select * from wine where wine_id in ?1", nativeQuery = true)
	List<Wine> findAllByWineList (int[] wineId);

//	//싫어하는 선호도만 입력할 경우
//	@Query(value = "select * from wine where wine_id not in (select distinct(wine_id) from wine_flavor where flavor_id in ?1) order by avg DESC", nativeQuery = true)
//	List<Wine> findAllByDisLikeFlavor (List<Flavor> DisLikeFlavor, Pageable pageable);
//	


	List<Wine> findByAvgGreaterThanEqual(float avg);

}
