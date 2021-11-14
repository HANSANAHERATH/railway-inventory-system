import axios from 'axios';

export default {
    token: {
        getToken: payload =>
            axios.get(`/getRefreshToken`).then(res => res),
    },
};
