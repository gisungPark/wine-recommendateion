package com.ssafy.yourwine;

import com.ssafy.yourwine.model.entity.Review;
import com.ssafy.yourwine.model.entity.TodayWine;
import com.ssafy.yourwine.model.entity.TopTen;
import com.ssafy.yourwine.model.entity.Wine;
import com.ssafy.yourwine.repository.ReviewRepository;
import com.ssafy.yourwine.repository.TodayWineRepository;
import com.ssafy.yourwine.repository.TopTenRepository;
import com.ssafy.yourwine.repository.WineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * batch작업 진행
 *
 */

@Component
@RequiredArgsConstructor
@Transactional
public class ScheduleTask {
	private final TopTenRepository topTenRepository;
	private final WineRepository wineRepository;
	private final ReviewRepository reviewRepository;
	private final TodayWineRepository todayWineRepository;

	// 매일 밤 12시기준 배치작업
	@Scheduled(cron = "0 0 0 * * ? ", zone = "Asia/Seoul")
	private void pup1() {

		//Top 10 score 계산
		List<Wine> wineList = wineRepository.findAll();

		for(Wine wine: wineList){
			TopTen topTen = new TopTen();

			List<Review> reviewList = reviewRepository.findByWine(wine);
			int sum = 0;
			double devSqvSum = 0;
			double avg;
			double std;
			for(Review review: reviewList)
				sum += review.getPoint();

			avg = sum / reviewList.size();

			for(Review review: reviewList)
				devSqvSum += Math.pow(avg - review.getPoint(), 2);

			std = Math.sqrt(devSqvSum/reviewList.size());

			double score = avg/(5 + Math.pow(std,2));
			topTen.setWineId(wine.getWineId());
			topTen.setScore(score);
			topTen.setPrice(wine.getPrice());

			topTenRepository.save(topTen);
		}
		//Top 10 score 계산

		//오늘의 와인
		List<Wine> wines = wineRepository.findByAvgGreaterThanEqual((float)4.0);
		int select = (int) (Math.random() * wines.size());
		todayWineRepository.deleteAll();
		TodayWine todayWine = new TodayWine();
		todayWine.setWineId(wines.get(select).getWineId());
		todayWineRepository.save(todayWine);
		//
	}

	// 매일 1시간마다 배치작업
	@Scheduled(cron = "0 0 0/1 * * ? ", zone = "Asia/Seoul")
	private void pup2() {

		//Wine별 평균 평점 계산
		List<Wine> wineList = wineRepository.findAll();

		for(Wine wine: wineList){
			List<Review> reviewList = reviewRepository.findByWine(wine);
			float sum = 0;
			for(Review review: reviewList)
				sum += (float)review.getPoint();
			float avg = sum / reviewList.size();
			System.out.println(avg);

			wine.setAvg(avg);

			wineRepository.save(wine);
		}
		//Wine별 평균 평점 계산
	}
	
	//test 10초마다 배치작업 - 테스트용
//	@Scheduled(cron = "0/30 * * * * ? ", zone = "Asia/Seoul")
//	private void test() {
//
//	}
}
