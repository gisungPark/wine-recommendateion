//ex| auth.js - 인증 관련 예시
import http from './http';

// 와인 스크랩  추가
export function postWineScrap(wineId) {
  return http.post(`/wine/scrap/${wineId}`);
}

// 와인 스크랩 삭제
export function deleteWineScrap(wineId) {
  return http.delete(`wine/scrap/${wineId}`);
}
