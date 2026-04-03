export const REST_API_KEY = process.env.REACT_APP_MY_KAKAO_API_KEY;
export const REDIRECT_URI = "http://13.209.208.223:9090/oauth/kakao/callback";
export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;	