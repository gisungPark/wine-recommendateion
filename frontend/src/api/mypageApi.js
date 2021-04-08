import http from '@/api/http';

/**
 * 내가 작성한 와인
 */
export function mypageReview(pageNum){
    return http.get(`/user/getReview?page=${pageNum}`);
}

export function mypageFlavor(){
    return http.get(`/user/getFlavor`);
}

export function updatePreference(payload){
    return http.put('/reco/updatePreference', payload);
}

export function getPreference(){
    return http.post(`/reco/getPreference`);
}
/**
 * 스크랩한 와인
 */
export function mypageScrap(){
    return http.get(`/user/getScrap`);
}
/**
 * 스크랩한 와인 삭제
 */
 export function deleteScrap(windId){
    return http.delete(`/wine/scrap/${windId}`);
}
/**
 * 프로필 이미지 변경
 */
 export function updateProfile(index){
    return http.put(`/user/updateProfile?number=${index}`);
}