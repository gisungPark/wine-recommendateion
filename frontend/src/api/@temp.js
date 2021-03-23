//ex| auth.js - 인증 관련 예시
import http from './http';

//get
export function duplicate(email) {
  return http.get(`/account/confirm/emailCheck/${email}`);
}

//post 지양
export function login(email, password) {
  return http.post('/account/confirm/login', {
    user_email: email,
    user_password: password,
  });
}
//post 추천
export function login(payload) {
  return http.post('/account/confirm/login', payload);
}

//put
export function putPassword(payloard) {
  return http.put('/account/user/password', payloard);
}
