import React from 'react';
import { LinearProgress, CircularProgress } from '@material-ui/core';

const spinnerStyle = {
    height: '300px',
    width: '100%',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
};

const Spinner = ({ type }) => {
    return (
        <div style={spinnerStyle} className="spinner-view">
            {type === 'linear' ? (
                <LinearProgress></LinearProgress>
            ) : (
                <CircularProgress></CircularProgress>
            )}
        </div>
    );
};

export default Spinner;
