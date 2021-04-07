import http from '@/api/http';

export function getWineReviewById(pageNum, wineId) {
    return http.get(`/wine/reivew-list/${wineId}`,{
        params: {
            page: pageNum,
        }
    });
}

export function writeReview(contents, cost, point, wineId) {
    return http.post(`/wine/reivew`, { 
            contents: contents,
            cost: cost,
            point: point,
            wineId: wineId,
    });
}

export function deleteReview(wineId) {
    return http.delete(`/wine/reivew/${wineId}`);
}