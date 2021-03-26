package com.ssafy.yourwine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yourwine.model.entity.Scrap;
import com.ssafy.yourwine.model.entity.User;
import com.ssafy.yourwine.model.entity.Wine;

public interface ScrapRepository extends JpaRepository<Scrap, Long>{

	List<Scrap> findScrapByUser(User user);
	Optional<Scrap> findByUserAndWine (User user, Wine wine);
}
