package com.ssafy.yourwine.repository;

import com.ssafy.yourwine.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{
	User findByEmail(String email);
	User findByNickname(String nickname);
	User findByEmailAndPassword(String email, String password);
	User findByToken(String token);
	User findByEmailAndProvider(String email, Integer provider);
	User findByUserId(String user_id);
}
