import axios from 'axios';

export default {
    users: {
        getRegisteredUsers: payload =>
            axios
               //.get(`/sessions/${payload.id}/all`, {
                .get(`/dashboard/usersummary/${payload.sessionId}`)
                .then(res => res.data),
    },
    dashboard: {
        getSessionList: payload =>
            axios
                .get(`/sessionsadmin/${payload.centerId}`)
                .then(res => res.data),
    },
};
