import React from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/styles';

const useStyles = makeStyles(theme => ({
    root: {
        padding: theme.spacing(4),
        bottom: 0,
      //  width: '100%',
        position: 'relative',
      //  position: 'absolute',
        width: '-webkit-fill-available',
    },
    flexGrow: {
        flexGrow: 1,
    },
    img: {
        width: 75,
    },
    logoWrapper: {
        width: '100%',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        bottom: '0',
    },
    footerDiv : {
        background: '#f6f6f6',
        padding: '15px',
        textAlign: 'center',
        color: '#fff',
        fontSize: '11px',
        fontFamily: 'Raleway, sans-serif',
        paddingLeft: '0',
        paddingRight: '0',
        width: '100%',
        float: 'left',
        display: 'flex',
        justifyContent: 'center',
    },
    footerText : {
        color: 'black',
    }
}));

const Footer = props => {
    const { className, ...rest } = props;

    const classes = useStyles();

    return (
        <div {...rest} className={clsx(classes.root, className)}>
             <div className={classes.footerDiv}><p className={classes.footerText}>© All Rights Reserved.</p></div>
            <div className={classes.logoWrapper}>
                <img
                    className={classes.img}
                    alt="Logo"
                    src="/images/logos/icta-full-logo.png"
                />
            </div>
        </div>
    );
};

Footer.propTypes = {
    className: PropTypes.string,
};

export default Footer;
