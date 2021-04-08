package com.ssafy.yourwine.repository;

import com.ssafy.yourwine.model.entity.TodayWine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayWineRepository extends JpaRepository<TodayWine, Long>{
}
