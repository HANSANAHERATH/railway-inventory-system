import {
    FETCH_DASHBOARD,
    FETCH_DASHBOARD_SUCCESS,
    FETCH_DASHBOARD_FAILED,
    RESET_DASHBOARD,
} from './types';

export const fetchDashboard = payload => {
    return { type: FETCH_DASHBOARD, payload };
};

export const fetchDashboardSuccess = payload => {
    return { type: FETCH_DASHBOARD_SUCCESS, payload };
};

export const fetchDashboardFailed = payload => {
    return { type: FETCH_DASHBOARD_FAILED, payload };
};

export const fetchDashboardReset = payload => {
    return { type: RESET_DASHBOARD, payload };
};
