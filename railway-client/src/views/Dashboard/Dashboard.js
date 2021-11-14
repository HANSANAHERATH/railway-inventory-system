import React, {useEffect, useState} from 'react';
import { makeStyles } from '@material-ui/styles';
import { Grid, TextField, Typography } from '@material-ui/core';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';
import { Button } from '@material-ui/core';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableRow from '@material-ui/core/TableRow';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import ErrorDisplay from 'views/Common/ErrorDisplay';
import Spinner from 'views/Common/Spinner';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import {fetchDistricts} from "../../actions/session";
import {fetchDashboard, fetchDashboardReset} from '../../actions/dashboard';
import { DataGrid, GridToolbar } from '@material-ui/data-grid';
import Autocomplete from '@material-ui/lab/Autocomplete';

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

/* const StyledTableRow = withStyles(theme => ({
    root: {
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.action.hover,
        },
    },
}))(TableRow); */

/* const StyledTableCell = withStyles(theme => ({
    head: {
        backgroundColor: '#e6e6e6',
        color: theme.palette.common.black,
    },
    body: {
        fontSize: 15,
    },
}))(TableCell); */

const Dashboard = ({
    loading,
    error,
    loginSuccess,
    fetchDistricts,
    districtsList,
    fetchDashboard,
    data,
    fetchDashboardReset,
}) => {

    useEffect(() => {
        fetchDistricts();
        //fetchDashboardReset();
    }, [sessionStorage.getItem('token'),fetchDistricts]);

    const classes = useStyles();

    const [district, setDistrict] = React.useState('');
    const [mohList, setMohList] = useState([]);
    const [moh, setMoh] = useState('');

    const handleChangeDistrict = (event, value) => {
        resetDistrict();
        if( value !== null){
            setDistrict(value);
            setMohList(value.districtFullListMohDto);
        }
    }

    const resetDistrict = () => {
        setDistrict('');
        setMoh('');
        setMohList([]);
        fetchDashboardReset();
    }

    const handleChangeMohArea = (event, value) => {
        setMoh('');
        fetchDashboardReset();
        if( value !== null){
            setMoh(value);
        }
    }

    const handleSearchData = () => {
        fetchDashBoardApi();
    }

    const fetchDashBoardApi = () => {
        fetchDashboard(moh.mohId);
    }

    const reLoadDashBoard = () => {
     fetchDashBoardApi();
    }

  /*   useEffect(() =>{
        fetchDashBoardApi();
    },[fetchDashboard]) */


    const [open, setOpen] = React.useState(false);
    const [validationMessage, setValidationMessage] = React.useState('');

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const columns = [
        {
            field: 'vaccineDate',
            headerName: 'SESSION DATE',
            type: 'date',
            flex: 0.5,
            cellClassName: 'table--cell',
        },
        {
            field: 'mohName',
            headerName: 'MOH AREA',
            flex: 0.5,
            cellClassName: 'table--cell',
        },
        {
            field: 'centerName',
            headerName: 'VACCINE CENTER',
            flex: 0.75,
            cellClassName: 'table--cell',
        },
        {
            field: 'vaccineName',
            headerName: 'VACCINE NAME',
            flex: 0.5,
            cellClassName: 'table--cell',
        },
        {
            field: 'dose',
            headerName: 'VACCINE DOSAGE',
            flex: 0.5,
            cellClassName: 'table--cell',
        },
        {
            field: 'availableCount',
            headerName: 'TOTAL VACCINES',
            flex: 0.5,
            cellClassName: 'table--cell',
        },
        {
            field: 'registeredCount',
            headerName: 'REGISTERED COUNT',
            flex: 0.5,
            cellClassName: 'table--cell',
        },
    ];

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
                                Dashboard
                            </Typography>
                            <Typography
                                component="h1"
                                variant="h2"
                                align="left"
                                className={classes.mainFont}
                            >
                                <i
                                    className="fa fa-refresh"
                                    onClick={() => {
                                        reLoadDashBoard();
                                    }} 
                                ></i>
                            </Typography>   
                        </div> 
                    </div>
                    <div>
                        <br></br>
                        {/** Select Districts */}
                        <Autocomplete
                            //className={classes.sessionListSelector}
                            id="districtSelector"
                            value={district}
                            options={districtsList}
                            getOptionLabel={option => option?.districtName || ''}
                            renderInput={params => (
                                <TextField
                                    {...params}
                                    variant="outlined"
                                    label="Select the district"
                                />
                            )}
                            onChange={(event, newValue) => {
                                handleChangeDistrict(event, newValue);
                            }}
                        />
                        <br/>
                        <br/>

                        {/** Select MOH Area */}
                        <Autocomplete
                            //className={classes.sessionListSelector}
                            id="mohAreaSelector"
                            value={moh}
                            options={mohList}
                            getOptionLabel={option => option?.mohName || ''}
                            renderInput={params => (
                                <TextField
                                    {...params}
                                    variant="outlined"
                                    label="Select the MOH area"
                                />
                            )}
                            onChange={(event, newValue) => {
                                handleChangeMohArea(event, newValue);
                            }}
                        />
                        <br/>
                        <br/>
                        <Button variant="contained" color="primary" onClick = {handleSearchData}>Search</Button>
                        <br/>
                        <br/>
                            <TableContainer component={Paper}>
                                <div
                                    style={{ height: 650, minWidth: '1000px' }}
                                    className={classes.root}
                                >
                                {data ? (
                                    <DataGrid
                                        pagination
                                        showColumnRightBorder
                                        disableColumnMenu
                                        rows={data}
                                        columns={columns}
                                        pageSize={10}
                                        components={{
                                            Toolbar: GridToolbar,
                                        }}
                                    />
                                ) : null}
                        </div>
                    </TableContainer>
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

};

function mapStateToProps({ session, dashboard }) {
    let loginSuccess = sessionStorage.getItem('loginSuccess');
    let { districts } = session;
    let { data } = dashboard;

    return {
        loginSuccess,
        loading: districts.loading,
        error: districts.error,
        districtsList: districts.data,
        data,
    };
}

export default connect(mapStateToProps, {
    fetchDistricts,
    fetchDashboard,
    fetchDashboardReset,
})(Dashboard);

