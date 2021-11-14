import React from 'react';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/styles';

import Topbar from '../components/Topbar/Topbar';
import Footer from '../components/Footer/Footer';

const useStyles = makeStyles(() => ({
    root: {
        height: '100%',
    },
}));

const Minimal = props => {
    const { children } = props;

    const classes = useStyles();

    return (
        <div className={classes.root}>
            <Topbar isMainLayout={false} />
            <main>
                {children}
                <Footer />
            </main>
        </div>
    );
};

Minimal.propTypes = {
    children: PropTypes.node,
    className: PropTypes.string,
};

export default Minimal;
