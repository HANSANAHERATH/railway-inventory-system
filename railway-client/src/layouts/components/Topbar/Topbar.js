import React from 'react';
import { Link as RouterLink } from 'react-router-dom';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/styles';
import { AppBar, Toolbar, Typography } from '@material-ui/core';
import Menubar from '../Menubar/Menubar';

const useStyles = makeStyles(theme => ({
    root: {
        boxShadow: 'none',
        backgroundColor: 'white',
        zIndex: 100,
        padding: '20px',
        position: 'inherit',
        marginBottom: '30px',
    },
    flexGrow: {
        flexGrow: 1,
    },
    img: {
        width: 60,
    },
    logoWrapper: {
        width: '100%',
        display: 'flex',
        alignItems: 'center',
    },
    mainFont: {
        color: '#004F8B',
        'font-size': '20px',
        'font-weight': 1000,
    },
    textBox: {
        padding: '10px',
    },
    subtitle: {
        color: '#848484',
    },
}));

const Topbar = props => {
    const classes = useStyles();
    return (
        <AppBar className={clsx(classes.root)}>
            <Toolbar className={clsx(classes.toolBar)}>
                <div className={classes.logoWrapper}>
                    <RouterLink to="/dashboard">
                        <img
                            className={classes.img}
                            alt="Logo"
                            src="/images/logos/moh-logo-2.png"
                        />
                    </RouterLink>
                    <div className={classes.textBox}>
                        {/* <Typography variant="h3" className={classes.mainFont}>
                            COVID-19
                        </Typography> */}
                        <Typography variant="h3" className={classes.mainFont}>
                            Inventory System
                        </Typography>
                        <hr />
                        <Typography
                            inline="true"
                            component="h2"
                            variant="h5"
                            className={classes.subtitle}
                        >
                            Sri Lanka Railway
                        </Typography>
                    </div>
                </div>

                <div className={classes.flexGrow} />
            </Toolbar>

            {/* sessionStorage.getItem('token') !== null ?  */<Menubar /> /* : null */}
        </AppBar>
    );
};

Topbar.propTypes = {
    className: PropTypes.string,
    onSidebarOpen: PropTypes.func,
};

export default Topbar;
