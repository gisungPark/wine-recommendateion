package com.ssafy.yourwine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yourwine.model.entity.Review;
import com.ssafy.yourwine.model.entity.User;
import com.ssafy.yourwine.model.entity.Wine;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	Optional<Review> findByUserAndWine (User user, Wine wine);
}
