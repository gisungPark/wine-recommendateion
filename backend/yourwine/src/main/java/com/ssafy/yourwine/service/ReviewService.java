package com.ssafy.yourwine.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.yourwine.config.security.JwtTokenProvider;
import com.ssafy.yourwine.model.dto.ReviewDTO;
import com.ssafy.yourwine.model.entity.Review;
import com.ssafy.yourwine.model.entity.User;
import com.ssafy.yourwine.model.entity.Wine;
import com.ssafy.yourwine.model.key.ReviewKey;
import com.ssafy.yourwine.repository.ReviewRepository;
import com.ssafy.yourwine.repository.UserRepository;
import com.ssafy.yourwine.repository.WineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

	private final ModelMapper modelMapper = new ModelMapper();
	private final ReviewRepository reviewRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private final WineRepository wineRepository;
	

	//해당 와인 리뷰 전체 리스트
	public List<ReviewDTO> getWineReviewList (Long wineId){
		Wine wine = wineRepository.findById(wineId).orElseThrow(() -> new IllegalArgumentException("no wine data"));
		List<Review> reviewList = reviewRepository.findByWine(wine);
		return reviewList.stream().map(ReviewDTO::new).collect(Collectors.toList());
	}
	
	//리뷰 작성	
	@Transactional
	public void saveReview(ReviewDTO reviewDto, String token) {
		String userId = jwtTokenProvider.getUserId(token);
		Review review = new Review();
		ReviewKey reviewKey = new ReviewKey();
		reviewKey.setUserId(userId);
		reviewKey.setWineId(reviewDto.getWineId());
		modelMapper.map(reviewDto, review);
		if(reviewDto.getTime() == null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Date time = new Date(timestamp.getTime());
			review.setTime(time);
		}
		review.setReviewId(reviewKey);
		reviewRepository.save(review);
	}
	
	//리뷰 수정
	@Transactional
	public void updateReview(ReviewDTO reviewDto, String token) {
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		Wine wine = wineRepository.findById(reviewDto.getWineId()).orElseThrow(() -> new IllegalArgumentException("no wine data"));
		Review review =  reviewRepository.findByUserAndWine(user, wine).orElseThrow(() -> new IllegalArgumentException("no review data"));
		modelMapper.map(reviewDto, review);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Date time = new Date(timestamp.getTime());
		review.setTime(time);
		reviewRepository.save(review);
	}
	
	//리뷰 삭제
	@Transactional
	public void deleteReview(String token, Long wineId) {
		String userId = jwtTokenProvider.getUserId(token);
		Review review = new Review();
		ReviewKey reviewKey = new ReviewKey();
		wineRepository.findById(wineId).orElseThrow(() -> new IllegalArgumentException("no wine data"));
		reviewKey.setUserId(userId);
		reviewKey.setWineId(wineId);
		review.setReviewId(reviewKey);
		reviewRepository.delete(review);
	}
	
}
