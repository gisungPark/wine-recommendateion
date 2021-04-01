package com.ssafy.yourwine;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * batch작업 진행
 *
 */

@Component
public class ScheduleTask {

	// 매일 밤 12시기준 배치작업
	@Scheduled(cron = "0 0 0 * * ? ", zone = "Asia/Seoul")
	private void pup1() {

	}

	// 매일 1시간마다 배치작업
	@Scheduled(cron = "0 0 0/1 * * ? ", zone = "Asia/Seoul")
	private void pup2() {

	}
	
	//test 10초마다 배치작업 - 테스트용
//	@Scheduled(cron = "0/10 * * * * ? ", zone = "Asia/Seoul")
//	private void test() {
//		System.out.println("10초마다 테스트~");
//	}
}
