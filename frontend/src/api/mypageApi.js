import http from '@/api/http';

export function mypageReview(pageNum){
    return http.get(`/user/getReview?page=${pageNum}`);
}

export function mypageFlavor(){
    return http.get(`/user/getFlavor`);
}

export function updatePreference(payload){
    return http.put('/reco/updatePreference', payload);
}
