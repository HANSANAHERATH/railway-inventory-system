import React from 'react';
import { Switch, Redirect, Route } from 'react-router-dom';

import { RouteWithLayout } from './components';
import { Minimal as MinimalLayout } from './layouts';

import {
    SignIn as SignInView,
    NotFound as NotFoundView,
    CenterList as CenterListView,
    SessionList as SessionListView,
} from './views';

const Routes = () => {
    return (
        <Switch>
            <Redirect exact from="/" to="/sign-in" />
            <Route path="/health">
                <h3>Hey There!!! The App is Healthy</h3>
            </Route>
            <RouteWithLayout
                component={SignInView}
                exact
                layout={MinimalLayout}
                path="/sign-in"
            />
            <RouteWithLayout
                component={CenterListView}
                exact
                layout={MinimalLayout}
                path="/update-inventory"
            />
            <RouteWithLayout
                component={SessionListView}
                exact
                layout={MinimalLayout}
                path="/item-list"
            />
            <RouteWithLayout
                component={NotFoundView}
                exact
                layout={MinimalLayout}
                path="/not-found"
            />
            <Redirect to="/not-found" />
        </Switch>
    );
};

export default Routes;
