import http from '@/api/http';

export function login(email, password) {
    return http.post('/user/login', {
            email: email,
            password: password
    });
}
// 카카오 로그인
export function kakaoLogin(data) {
    return http.post(`/user/checkUser?token=${data.access_token}`);
}
// 카카오 회원가입(닉네임 등록)
export function kakaoJoin(nickname) {
    return http.put(`/user/updateUser?nickname=${nickname}`);
}
// export function kakaoJoin(nickname) {
//     return http.put(`/user/updateUser?nickname=${nickname}`);
// }

export function getUserInfo() {
    return http.get('/user/getUserInfo');
}

export function logout() {
    return http.post('/user/logout',);
}

export function duplicate(email){
    return http.get(`/user/checkemail/${email}`);
}

export function nicknameDuplicate(nickname){
    return http.get(`/user/checknickname/${nickname}`);
}

export function join(payload){
    return http.post('/user/signup', payload);
}
