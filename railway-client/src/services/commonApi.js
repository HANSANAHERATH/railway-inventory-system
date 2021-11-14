import axios from 'axios';

export default {
    projects: {
        getProjects: payload => axios.get(`cdf/projects`).then(res => res.data),
    },
};
