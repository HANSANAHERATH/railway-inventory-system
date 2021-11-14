import {
    FETCH_REFRESH_TOKEN,
    FETCH_REFRESH_TOKEN_SUCCESS,
    FETCH_REFRESH_TOKEN_FAILED,
    RESET_REFRESH_TOKEN,
} from './types';

export const getRefreshToken = payload => {
    return { type: FETCH_REFRESH_TOKEN, payload };
};

export const getRefreshTokenSuccess = payload => {
    return { type: FETCH_REFRESH_TOKEN_SUCCESS, payload };
};

export const getRefreshTokenFailed = payload => {
    return { type: FETCH_REFRESH_TOKEN_FAILED, payload };
};

export const resetGetRefreshToken = payload => {
    return { type: RESET_REFRESH_TOKEN, payload };
};
