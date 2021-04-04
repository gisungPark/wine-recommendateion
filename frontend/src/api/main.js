//ex| auth.js - 인증 관련 예시
import http from './http';

// 와인 상세 정보 리스트
export function getTodaysWine() {
  return http.get(`/reco/getTodayWine`);
}
// 와인 top 10
export function getWineTop10() {
  return http.get(`/reco/getTopten/0/999999999`);
}
