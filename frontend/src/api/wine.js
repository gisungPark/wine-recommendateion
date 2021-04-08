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

// export function getWineReviewById(pageNum, time, wineId) {
//     console.log(pageNum + " " +  time + " "+ wineId);
//     return http.get(`/wine/review-list/${wineId}`,{
//         params: {
//             page: pageNum,
//             time: time,
//         }
//     });
// }
