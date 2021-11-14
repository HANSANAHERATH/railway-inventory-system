import DateFnsUtils from '@date-io/date-fns';
import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow, TextField, Typography } from '@material-ui/core';
import Autocomplete from '@material-ui/lab/Autocomplete';
import { KeyboardDatePicker, MuiPickersUtilsProvider } from '@material-ui/pickers';
import { makeStyles, withStyles } from '@material-ui/styles';
import moment from 'moment';
import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import ErrorDisplay from 'views/Common/ErrorDisplay';
import Spinner from 'views/Common/Spinner';

import {fetchDistricts} from "../../actions/session";
import {fetchlocationData, resetFetchlocationData } from "../../actions/location";

const useStyles = makeStyles(theme => ({
    root: {
        '& .table--cell': {
            fontSize: '13px',
        },
    },
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
        borderRadius: '20px',
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
    tableCell: {
        minWidth: 'fit-content',
    },
    inputLabel: {
        letterSpacing: '0px',
        color: '#043B84',
        fontFamily: '"Roboto", "Helvetica", "Arial", sans-serif',
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
        backgroundColor: '#e6e6e6',
        color: theme.palette.common.black,
    },
    body: {
        fontSize: 15,
    },
}))(TableCell);

const Location = ({
    loginSuccess,
    loading,
    error,
    districtsList,
    fetchDistricts,
    fetchlocationData,
    locationData,
    resetFetchlocationData,
}) => {
    const classes = useStyles();

    useEffect(() => {
        fetchDistricts();
        resetFetchlocationData();
    }, [sessionStorage.getItem('token'),fetchDistricts, fetchlocationData]);

    const FromDateMin = new Date('2020-01-01');
    const [toDateMin, setToDateMin] = useState(new Date(FromDateMin));
    const [fromDate, setFromDate] = useState('');
    const [toDate, setToDate] = useState('');
    const [selectedDistrict,setSelectedDistrict] = useState('');

    /** Table Component Functions */
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(5);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = event => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    /** Table Component Functions End */

    /** Date Validation Dialog */

    const [open, setOpen] = React.useState(false);
    const [validationMessage, setValidationMessage] = React.useState('');

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };
    /** Date Validation Dialog End */

    const handleSelectDistrict = (event , value) => {
        resetFetchlocationData();
        setFromDate(null);
        setToDate(null);
        setToDateMin(new Date(FromDateMin));
        if(value === null){
            setSelectedDistrict('');
         }else{
            setSelectedDistrict(value);
         }
    }

    const handleFromDateDateChange = event => {
        let tmpDate = new Date(event);
        tmpDate = moment(tmpDate).format(moment.HTML5_FMT.DATE);
        setFromDate(tmpDate);
        setToDate(tmpDate);
        setToDateMin(tmpDate);
    }

    const handleToDateChange = event => {
        if(fromDate === ''){
            setValidationMessage("Please Select Start Date.")
            handleClickOpen();
        }
        let tmpDate = new Date(event);
        tmpDate = moment(tmpDate).format(moment.HTML5_FMT.DATE);
        setToDate(tmpDate);
       // fetchLocationFilterData(selectedDistrict, fromDate, toDate);
    }

    const fetchLocationFilterData = (districtId, fromDate, toDate) => {
        fetchlocationData({
            "districtId": districtId.id,
            "fromDate": fromDate,
            "toDate": toDate
        });
    } 

    const handleFilter = () => {
        fetchLocationFilterData(selectedDistrict, fromDate, toDate);
    }

    return !loginSuccess ? (
        <Redirect to="/sign-in" />
    ) : loading ? (
        <Spinner></Spinner>
    ) : error ? (
        <ErrorDisplay error={error} refreshButton={true}></ErrorDisplay>
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
                                Location Dashboard
                            </Typography>
                            {/* <Typography
                                component="h1"
                                variant="h2"
                                align="left"
                                className={classes.mainFont}
                            >
                                <i
                                    className="fa fa-refresh"
                                    onClick={() => {
                                        //reLoadDashBoard();
                                    }} 
                                ></i>
                            </Typography>  */}  
                        </div>
                    </div>
                    <div>
                    <br></br>
                        <Autocomplete
                            className={classes.sessionListSelector}
                            id="district"
                            value={selectedDistrict}
                            options={districtsList || []}
                            getOptionLabel={option =>
                                option?.districtName || ''
                            } 
                            renderInput={params => (
                                <TextField
                                    {...params}
                                    variant="outlined"
                                    label="Select District"
                                />
                            )}
                            onChange={(event, newValue) => {
                                handleSelectDistrict(event, newValue);
                            }}
                        />
                        <br></br>
                        <MuiPickersUtilsProvider utils={DateFnsUtils}>
                            <KeyboardDatePicker
                                className={classes.datePicker}
                                label="Session Date From"
                                disableToolbar
                                variant="inline"
                                format="dd-MM-yyyy"
                                margin="normal"
                                id="sessionDateFrom"
                                value={fromDate || null}
                                keyboardbuttonprops={{
                                    'aria-label': 'change date',
                                }}
                                minDate={FromDateMin}
                                autoOk={true}
                                onChange={handleFromDateDateChange}
                            />
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <KeyboardDatePicker
                                className={classes.datePicker}
                                label="Session Date To"
                                disableToolbar
                                variant="inline"
                                format="dd-MM-yyyy"
                                margin="normal"
                                id="sessionDateTo"
                                value={toDate || null}
                                keyboardbuttonprops={{
                                    'aria-label': 'change date',
                                }}
                                minDate={toDateMin}
                                autoOk={true}
                                onChange={handleToDateChange}
                            />
                        </MuiPickersUtilsProvider>
                        <br></br>


                        <Button style={{float: "right", marginBottom: "10px"}} variant="contained" color="primary" onClick={handleFilter}>
                            Search
                        </Button>

                        <br></br>

                        <React.Fragment>
                            <TableContainer component={Paper}>
                                <Table
                                    className={classes.table}
                                    aria-label="customized table"
                                >
                                    <TableHead>
                                        <TableRow>
                                            <StyledTableCell>
                                                Date
                                            </StyledTableCell>
                                            <StyledTableCell>
                                                Location
                                            </StyledTableCell>
                                            <StyledTableCell>
                                                Appointment - Requests
                                            </StyledTableCell>
                                            <StyledTableCell>
                                                Appointment - Confirmed
                                            </StyledTableCell>
                                            <StyledTableCell>
                                                Appointment - Pending
                                            </StyledTableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {locationData
                                            .slice(
                                                page * rowsPerPage,
                                                page * rowsPerPage +
                                                    rowsPerPage
                                            )
                                            .map((data,index) => (
                                                <StyledTableRow
                                                    key={index}
                                                >
                                                    <StyledTableCell
                                                        component="th"
                                                        scope="row"
                                                    >
                                                        {data.sessionDate}
                                                    </StyledTableCell>
                                                    <StyledTableCell
                                                        component="th"
                                                        scope="row"
                                                    >
                                                        {data.vaccineLocation}
                                                    </StyledTableCell>
                                                    <StyledTableCell
                                                        component="th"
                                                        scope="row"
                                                        style={{paddingLeft:'50px'}}>
                                                        {data.totalCount}

                                                    </StyledTableCell>
                                                    <StyledTableCell
                                                        component="th"
                                                        scope="row"
                                                        style={{paddingLeft:'50px'}}>
                                                        {data.informedCount}
                                                    </StyledTableCell>
                                                    <StyledTableCell
                                                        component="th"
                                                        scope="row"
                                                        style={{paddingLeft:'50px'}}>
                                                        {data.pendingCount}
                                                    </StyledTableCell>
                                                    
                                                </StyledTableRow>
                                            ))}
                                    </TableBody> 
                                </Table>
                            </TableContainer>
                            <TablePagination
                                rowsPerPageOptions={[5, 10, 50]}
                                component="div"
                                count={locationData.length}
                                rowsPerPage={rowsPerPage}
                                page={page}
                                onChangePage={handleChangePage}
                                onChangeRowsPerPage={
                                    handleChangeRowsPerPage
                                }
                            /> 
                        </React.Fragment>
                    </div>
                </Paper>
            </main>
            <Dialog
                open={open}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">
                    Error
                </DialogTitle>
                <DialogContent>
                    <DialogContentText id="alert-dialog-description">
                       {validationMessage}
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary" autoFocus>
                       Close
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

function mapStateToProps({session, location}) {
    let loginSuccess = sessionStorage.getItem('loginSuccess');
    let { districts } = session;
    let { loading } = session || location;
    let { error } = location.location;
    //let { location } = location;
    return {
        loginSuccess,
        loading,
        error,
        districtsList: districts.data,
        locationData: location.location.data,
    }
}

export default connect(mapStateToProps, {
    fetchDistricts,
    fetchlocationData,
    resetFetchlocationData,
})(Location);
