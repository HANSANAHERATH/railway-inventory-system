import axios from 'axios';

export default {
    dashboard: {
        fetchDashboard: payload =>
            axios.get(
                `/dashboard/summary/${payload}`)
            .then(res => res.data),
    },
};
