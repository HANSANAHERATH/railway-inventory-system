import axios from 'axios';

export default {
    location: {
        fetchLocation: payload =>
            axios.post(
                `/locationstats`,
                {
                    ...payload,
                })
            .then(res => res.data),
    },
};
