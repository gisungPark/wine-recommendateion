package com.ssafy.yourwine.repository;

import com.ssafy.yourwine.model.entity.TasteDegree;
import com.ssafy.yourwine.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasteDegreeRepository extends JpaRepository<TasteDegree, String>{
    TasteDegree findByUser(User user);
}
