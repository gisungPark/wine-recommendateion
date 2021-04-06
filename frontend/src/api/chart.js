import http from '@/api/http';

export function getBasicStatistic() {
    return http.get('/user/statistics/pre', );
}

export function getflavorStatistic() {
    return http.get('/user/statistics/taste', );
}

export function getSimilarStatistic() {
    return http.get('/user/statistics/similar', );
}

