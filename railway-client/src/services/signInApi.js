import axios from 'axios';

export default {
    login: {
        loginApi: payload =>
            axios.post(`api/auth/signin`, { ...payload }).then(res => res.data)
    }
};
