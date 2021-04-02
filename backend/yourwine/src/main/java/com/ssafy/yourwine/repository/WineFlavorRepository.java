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
	
}
