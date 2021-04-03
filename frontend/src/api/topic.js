import http from '@/api/http';

export function getGrapeKind() {
    return http.get('/wine/grape', );
}

