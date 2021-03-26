package com.ssafy.yourwine.repository;

import com.ssafy.yourwine.model.entity.LikeFlavor;
import com.ssafy.yourwine.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LikeFlavorRepository extends JpaRepository<LikeFlavor, Integer>{
    void deleteByUser(User user);
    List<LikeFlavor> findByUser(User user);
}
