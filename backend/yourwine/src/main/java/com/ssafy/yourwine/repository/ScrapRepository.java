package com.ssafy.yourwine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yourwine.model.entity.Scrap;
import com.ssafy.yourwine.model.entity.User;

public interface ScrapRepository extends JpaRepository<Scrap, Long>{

	List<Scrap> findScrapByUser(User user);
}
