import React, { useEffect } from 'react';
import { withStyles } from '@material-ui/styles';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import { IconButton, Typography } from '@material-ui/core';
import CloseIcon from '@material-ui/icons/Close';

const DialogTitle = withStyles(theme => ({
    root: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        paddingTop: theme.spacing(2),
    },
    mainFont: {
        color: '#004F8B',
        'font-weight': 1000,
    },
    closeButton: {
        cursor: 'pointer',
        position: 'absolute',
        top: 0,
        right: 0,
    },
}))(props => {
    const { children, classes, onClose, ...other } = props;
    return (
        <MuiDialogTitle disableTypography {...other}>
            <div className={classes.root}>
                <div>
                    <Typography variant="h2" className={classes.mainFont}>
                        {children}
                    </Typography>
                </div>
                {onClose ? (
                    <IconButton
                        aria-label="close"
                        className={classes.closeButton}
                        onClick={onClose}
                        onTouchEnd={onClose}
                    >
                        <CloseIcon />
                    </IconButton>
                ) : null}
            </div>
        </MuiDialogTitle>
    );
});

export default DialogTitle;