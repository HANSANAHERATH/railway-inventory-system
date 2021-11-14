import axios from 'axios';

axios.defaults.baseURL = process.env.REACT_APP_BACKEND_URL;

export default function setupAxios() {
    axios.interceptors.request.use(
        // setting headers to requests sent via this application
        config => {
            config.headers['Authorization'] = sessionStorage.getItem('token');
            return config;
        },
        error => {
            return Promise.reject(error);
        }
    );
    createAxiosResponseInterceptor();
}

const createAxiosResponseInterceptor = () => {
    axios.interceptors.response.use(
        response => {
            return response;
        },
        async error => {
            let errorResponse = error.response;
            if (errorResponse && errorResponse.status === 401) {
            }
            return Promise.reject(error);
        }
    );
};
