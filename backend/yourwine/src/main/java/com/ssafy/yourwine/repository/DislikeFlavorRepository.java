package com.ssafy.yourwine.repository;

import com.ssafy.yourwine.model.entity.DislikeFlavor;
import com.ssafy.yourwine.model.entity.LikeFlavor;
import com.ssafy.yourwine.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DislikeFlavorRepository extends JpaRepository<DislikeFlavor, Integer>{
    void deleteByUser(User user);
    List<DislikeFlavor> findByUser(User user);
}
