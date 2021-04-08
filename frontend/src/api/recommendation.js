//ex| auth.js - 인증 관련 예시
import http from './http';

// 평점 기반 추천 리스트 요청
export function getRatingBasedRecom() {
  return http.get(`/reco/avg-recolist`);
}

//선호도 입력 여부 확인
export function getCheckPreference() {
  return http.get(`/reco/checkpreference`);
}

// 선호도 기반 추천 리스트 요청
export function getPreferenceBasedRecom(page) {
  return http.get(`/reco/preference-recolist?page=${page}`);
}
