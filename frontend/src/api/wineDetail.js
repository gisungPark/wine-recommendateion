//ex| auth.js - 인증 관련 예시
import http from './http';

// 와인 상세 정보 리스트
export function getWineDetail(wineId) {
  return http.get(`/wine/${wineId}`);
}
