/* import React from 'react';
import { connect } from 'react-redux';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/Input';
import InputAdornment from '@material-ui/core/InputAdornment';
import IconButton from '@material-ui/core/IconButton';
import SearchIcon from '@material-ui/icons/Search';
import { Redirect } from 'react-router-dom';

import { requestOTP, otpEntered } from 'actions/signin';
import ErrorDisplay from 'views/Common/ErrorDisplay';
import Spinner from 'views/Common/Spinner';
import AddCenter from './CenterAdd';

const useStyles = makeStyles(theme => ({
    layout: {
        width: 'auto',
        marginLeft: theme.spacing(2),
        marginRight: theme.spacing(2),
        [theme.breakpoints.up(600 + theme.spacing(2) * 2)]: {
            width: '85%',
            marginLeft: 'auto',
            marginRight: 'auto',
        },
    },
    paper: {
        marginTop: theme.spacing(3),
        marginBottom: theme.spacing(3),
        padding: theme.spacing(2),
        [theme.breakpoints.up(600 + theme.spacing(3) * 2)]: {
            marginTop: theme.spacing(6),
            marginBottom: theme.spacing(6),
            padding: theme.spacing(3),
        },
    },
    buttons: {
        display: 'flex',
        justifyContent: 'flex-end',
    },
    mainFont: {
        color: '#004F8B',
        'font-weight': 1000,
    },
    textBox: {
        padding: '10px',
    },
    subtitle: {
        color: '#848484',
    },
    tableHeader: {
        display: 'flex',
        justifyContent: 'space-between',
    },
    tableHeaderTitle: {
        display: 'flex',
        gap: '20px',
    },
}));

const StyledTableRow = withStyles(theme => ({
    root: {
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.action.hover,
        },
    },
}))(TableRow);

const StyledTableCell = withStyles(theme => ({
    head: {
        textAlign: 'left',
        font: 'normal normal 600 18px/24px Segoe UI',
        letterSpacing: '0px',
        color: '#616161',
    },
    body: {
        fontSize: 14,
    },
}))(TableCell);

const Centers = ({ loading, error, loginSuccess }) => {
    const classes = useStyles();
    return !loginSuccess ? (
        <Redirect to="/sign-in" />
    ) : loading ? (
        <Spinner></Spinner>
    ) : error ? (
        <ErrorDisplay error={error}></ErrorDisplay>
    ) : (
        <div className={classes.root}>
            <main className={classes.layout}>
                <Paper className={classes.paper}>
                    <div className={classes.tableHeader}>
                        <div className={classes.tableHeaderTitle}>
                            <Typography
                                component="h1"
                                variant="h2"
                                align="left"
                                className={classes.mainFont}
                            >
                                Vaccination centers
                            </Typography>

                            <AddCenter />
                        </div>

                        <div>
                            <TextField
                                id="standard-adornment-password"
                                type={'search'}
                                variant="filled"
                                endAdornment={
                                    <InputAdornment position="end">
                                        <IconButton aria-label="toggle password visibility">
                                            <SearchIcon />
                                        </IconButton>
                                    </InputAdornment>
                                }
                            />
                        </div>
                    </div>

                    <br />
                    <br />
                    <TableContainer component={Paper}>
                        <Table
                            className={classes.table}
                            aria-label="customized table"
                        >
                            <TableHead>
                                <TableRow>
                                    <StyledTableCell>
                                        <Typography
                                            component="h3"
                                            variant="h6"
                                            align="left"
                                            className={classes.subtitle}
                                        >
                                            #
                                        </Typography>
                                    </StyledTableCell>
                                    <StyledTableCell>
                                        {' '}
                                        <Typography
                                            component="h3"
                                            variant="h6"
                                            align="left"
                                            className={classes.subtitle}
                                        >
                                            Name
                                        </Typography>
                                    </StyledTableCell>
                                    <StyledTableCell>
                                        {' '}
                                        <Typography
                                            component="h3"
                                            variant="h6"
                                            align="left"
                                            className={classes.subtitle}
                                        >
                                            Contact
                                        </Typography>
                                    </StyledTableCell>
                                    <StyledTableCell>
                                        {' '}
                                        <Typography
                                            component="h3"
                                            variant="h6"
                                            align="left"
                                            className={classes.subtitle}
                                        >
                                            MOH
                                        </Typography>
                                    </StyledTableCell>
                                    <StyledTableCell>
                                        <Typography
                                            component="h3"
                                            variant="h6"
                                            align="left"
                                            className={classes.subtitle}
                                        >
                                            Appoinments
                                        </Typography>
                                    </StyledTableCell>
                                    <StyledTableCell></StyledTableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <StyledTableRow>
                                    <StyledTableCell component="th" scope="row">
                                        C0001
                                    </StyledTableCell>
                                    <StyledTableCell>
                                        Dr. Channa Ranasignhe
                                    </StyledTableCell>
                                    <StyledTableCell>Kadawatha</StyledTableCell>
                                    <StyledTableCell>
                                        MOH Kadawatha
                                    </StyledTableCell>
                                    <StyledTableCell>100</StyledTableCell>
                                    <StyledTableCell align="right">
                                        <Button
                                            variant="contained"
                                            className={classes.button}
                                        >
                                            Change
                                        </Button>
                                    </StyledTableCell>
                                </StyledTableRow>
                            </TableBody>
                        </Table>
                    </TableContainer>
                    <br />
                    <br />
                </Paper>
            </main>
        </div>
    );
};

function mapStateToProps({ registrationList }) {
    let { requestedOTP, loading, error } = registrationList;
    return {
        requestedOTP,
        loading,
        error,
    };
}

export default connect(mapStateToProps, { requestOTP, otpEntered })(Centers); */
