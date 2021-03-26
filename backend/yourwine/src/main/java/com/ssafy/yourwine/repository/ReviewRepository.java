package com.ssafy.yourwine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yourwine.model.entity.Review;
import com.ssafy.yourwine.model.entity.User;
import com.ssafy.yourwine.model.entity.Wine;
import com.ssafy.yourwine.model.key.ReviewKey;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	Optional<Review> findByUserAndWine (User user, Wine wine);
	List<Review> findByUser (User user);
	List<Review> findByWine (Wine wine);
	
}
