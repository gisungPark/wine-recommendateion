import http from '@/api/http';

export function search(page, keyword, sort, pointFilter,
    typeFilter, grapeFilter, startPrice, endPrice) {
    
    return http.get('/wine/list', {
        params: {
            page: page,
            keyword:keyword,
            sort:sort,
            pointFilter:pointFilter,
            typeFilter:typeFilter,
            grapeFilter:grapeFilter,
            startPrice:startPrice ,
            endPrice: endPrice,
            
        }
    });
}

