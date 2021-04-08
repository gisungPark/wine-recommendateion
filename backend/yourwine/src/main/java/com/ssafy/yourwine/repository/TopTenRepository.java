package com.ssafy.yourwine.repository;

import com.ssafy.yourwine.model.entity.TopTen;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopTenRepository extends JpaRepository<TopTen, Long>{
    List<TopTen> findByPriceGreaterThanEqualAndPriceLessThanEqual(Integer min, Integer max, Pageable pageable);
	
}
