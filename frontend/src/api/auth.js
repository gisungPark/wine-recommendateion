import http from '@/api/http';

export function login(email, password) {
    return http.post('/user/login', {
        SignInDto: {
            email: email,
            password: password
        }
    });
}

export function kakaoCallback(data) {
    return http.post('/user/checkUser', {
        token: data.access_token
    });
}

export function logout() {
    return http.post('/user/logout',);
}

export function join(){
    return http.post('',);
}

export function duplicate(email){
    return http.get(`/user/checkemail/{email}`);
}

export function nicknameDuplicate(nickname){
    return http.get(`/user/checknickname/{nickname}`);
}
