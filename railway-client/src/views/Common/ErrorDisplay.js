import React from 'react';
import { Typography, Button } from '@material-ui/core';
import ErrorIcon from '@material-ui/icons/Error';

const errorDisplay = {
    height: '300px',
    width: '100%',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
};

const refreshButtonStyle = {
    display: 'flex',
    justifyContent: 'center',
};

const ErrorDisplay = ({ error, refreshButton }) => {
    return (
        <div>
            <div style={errorDisplay} className="error-display">
                <ErrorIcon color="error"></ErrorIcon>
                <Typography variant="h5">{error}</Typography>
            </div>
            <div style={refreshButtonStyle}>
                {refreshButton && (
                    <Button
                        onClick={() => window.location.reload()}
                        variant="contained"
                        color="primary"
                    >
                        Refresh
                    </Button>
                )}
            </div>
        </div>
    );
};

export default ErrorDisplay;
