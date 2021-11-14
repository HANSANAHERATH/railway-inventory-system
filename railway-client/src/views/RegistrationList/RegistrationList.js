import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import ErrorDisplay from 'views/Common/ErrorDisplay';
import Spinner from 'views/Common/Spinner';
import {
    submitFormSessionCreate,
    fetchDistricts,
    fetchVaccineTyps,
} from 'actions/session';
import {
    fetchSelectedSession,
    fetchSelectedSessionReset,
    fetchRegisteredUsers,
    fetchRegisteredUsersReset,
    forceUpdate
} from 'actions/registrationList';
import { DataGrid, GridToolbar } from '@material-ui/data-grid';
import { Redirect } from 'react-router-dom';
import Autocomplete from '@material-ui/lab/Autocomplete';
import TextField from '@material-ui/core/TextField';
import TableContainer from '@material-ui/core/TableContainer';
import { Button } from '@material-ui/core';

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
    sessionListSelector: {
        width: '50%',
    },
    sessionListSelectorInMobile: {
        marginTop: '20px',
        width: '100%',
    },
    reLoadIcon: {
        ':hover': {
            cursor: 'pointer',
        },
    },
}));

const Registrations = ({
    loading,
    error,
    fetchSessions,
    loginSuccess,
    fetchRegisteredUsers,
    fetchDistricts,
    districtList,
    fetchSelectedSession,
    sessionList,
    fetchSelectedSessionReset,
    registerUserList,
    forceUpdate,
    fetchRegisteredUsersReset,
}) => {
    useEffect(() => {
        fetchDistricts();
        fetchRegisteredUsersReset();
        setWindowsDimentions();
    }, [fetchRegisteredUsersReset]);

    

    const reLoadData = () => {
    };
    const classes = useStyles();

    const [district, setDistrict] = React.useState('');
    const [mohList, setMohList] = useState([]);
    const [moh, setMoh] = useState('');
    const [vaccineCenterList, setVaccineCenterList] = useState([]);
    const [vaccineCenter, setVaccineCenter] = useState('');
    //const [sessionList, setSessionList] = useState([]);
    const [sessionSelected, setSessionSelected] = useState('');
    const [userList, setUserList] = useState([]);


    useEffect(() => {
        // fetchRegisteredUsers();
        if(registerUserList.length > 0){
            setUserList(registerUserList);
            //forceUpdate();
        }
     }, [registerUserList]);

    /** Change Drop down functions */
    const handleChangeDistrict = (event, value) => {
        resetDistrict();
        if( value !== null){
            setDistrict(value);
            setMohList(value.districtFullListMohDto);
        }
    }

    const handleChangeMohArea = (event, value) => {
        resetMohCeter();
        if( value !== null){
            setMoh(value);
            setVaccineCenterList(value.districtFullListLocationDto);
        }
    }

    const handleChangeVaccineCenter = (event, value) => {
        resetVaccineCenter();
        if( value !== null){
            setVaccineCenter(value);
            fetchSelectedSession({centerId: value.locationId});
        }
    }

    const resetDistrict = () => {
        setDistrict('');
        setMohList([]);
        resetMohCeter();
    }

    const resetMohCeter = () => {
        setMoh('');
        setVaccineCenterList([]);
        resetVaccineCenter();
    }

    const resetVaccineCenter = () => {
        setVaccineCenter('');
        setSessionSelected('');
        fetchSelectedSessionReset();
        fetchRegisteredUsersReset();
    }

    const handleSessionSelection = (event, value) => {
            setSessionSelected('');
            fetchRegisteredUsersReset();
        if( value !== null){
            setSessionSelected(value);
        }
    }

    const handleSearchData = (event, value) => {
        fetchRegisteredUsers({sessionId: sessionSelected.id})
    }

    /** Change Drop down functions end*/

    /* function getUserNic(rowData) {
        return rowData.row.citizenInfo?.nic;
    }

    function getUserFirstDosage(rowData) {
        return rowData.row.citizenInfo?.recivedFisrtDose;
    }

    function getUserName(rowData) {
        return rowData.row.citizenInfo?.name;
    }

    function getUserGender(rowData) {
        return rowData.row.citizenInfo?.gender;
    }

    function getUserMobile(rowData) {
        return rowData.row.citizenInfo?.mobileNo;
    } */

    const columns = [
        {
            field: 'nic',
            headerName: 'NIC Number',
            flex: 0.3,
            cellClassName: 'table--cell',
        },
        {
            field: 'mobile',
            headerName: 'Mobile Number',
            //valueGetter: getUserName,
            flex: 0.3,
            cellClassName: 'table--cell',
        }
        /* ,
        {
            field: 'firstDoseDate',
            headerName: 'FIRST DOSE DATE',
            //valueGetter: getUserMobile,
            flex: 1,
            cellClassName: 'table--cell',
        },
        {
            field: 'name',
            headerName: 'NAME',
            //valueGetter: getUserNic,
            flex: 1,
            cellClassName: 'table--cell',
        } */
    ];

     const [select, setSelection] = React.useState(null);

   /* const handleSessionSelection = (event, value) => {
        let tmpSession = value || '';
        setSelection(tmpSession);
        fetchRegisteredUsers(tmpSession);
    };
*/
    const [isMobile, setIsMobile] = React.useState(false);

    window.addEventListener('resize', function() {
        window.innerWidth < 600 ? setIsMobile(true) : setIsMobile(false);
    });

    window.addEventListener('load', function() {
        window.innerWidth < 600 ? setIsMobile(true) : setIsMobile(false);
    });

    function setWindowsDimentions() {
        window.innerWidth < 600 ? setIsMobile(true) : setIsMobile(false);
    } 

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
                                Registered Users
                            </Typography>
                            <Typography
                                component="h1"
                                variant="h2"
                                align="left"
                                className={classes.mainFont}
                            >
                                {/* <i
                                    className="fa fa-refresh reLoadIcon"
                                    onClick={() => {
                                        reLoadData();
                                    }}
                                ></i> */}
                            </Typography>
                        </div>
                        {/* {isMobile ? (
                            ''
                        ) : (
                            <Autocomplete
                                className={classes.sessionListSelector}
                                id="sessionCenter"
                                value={select}
                                options={sessionsList}
                                getOptionLabel={option =>
                                    option?.mohCentersEntity === undefined
                                        ? ''
                                        : option?.mohCentersEntity
                                              ?.displayName +
                                          ' | ' +
                                          option.date
                                }
                                renderInput={params => (
                                    <TextField
                                        {...params}
                                        variant="outlined"
                                        label="Select the session"
                                    />
                                )}
                                onChange={(event, newValue) => {
                                    handleSessionSelection(event, newValue);
                                }}
                            />
                        )} */}
                    </div>

                    {/* {!isMobile ? (
                        ''
                    ) : (
                        <Autocomplete
                            className={classes.sessionListSelectorInMobile}
                            id="sessionCenter"
                            value={select}
                            options={[]}
                            getOptionLabel={option =>
                                option?.mohCentersEntity === undefined
                                    ? ''
                                    : option?.mohCentersEntity?.displayName +
                                      ' | ' +
                                      option.date
                            }
                            renderInput={params => (
                                <TextField
                                    {...params}
                                    variant="outlined"
                                    label="Select the session"
                                />
                            )}
                            onChange={(event, newValue) => {
                                handleSessionSelection(event, newValue);
                            }}
                        />
                    )} */}
                    <br />
                    <br />
                    {/* <TableContainer component={Paper}>
                        <div
                            style={{ height: 400, minWidth: '1000px' }}
                            className={classes.root}
                        >
                            {usersList ? (
                                <DataGrid
                                    //pagination
                                    showColumnRightBorder
                                    disableColumnMenu
                                    rows={usersList}
                                    columns={columns}
                                    pageSize={5}
                                    components={{
                                        Toolbar: GridToolbar,
                                    }}
                                />
                            ) : null}
                        </div>
                    </TableContainer> */}
                    <div>
                         {/** Select Districts */}
                        <Autocomplete
                            //className={classes.sessionListSelector}
                            id="districtSelector"
                            value={district}
                            options={districtList}
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

                        {/** Select Vaccine Center */}
                        <Autocomplete
                           // className={classes.sessionListSelector}
                            id="VaccineCenterSelector"
                            value={vaccineCenter}
                            options={vaccineCenterList}
                            getOptionLabel={option => option?.locationName || ''}
                            renderInput={params => (
                                <TextField
                                    {...params}
                                    variant="outlined"
                                    label="Select the vaccine center"
                                />
                            )}
                            onChange={(event, newValue) => {
                                handleChangeVaccineCenter(event, newValue);
                            }}
                        />
                        <br/>
                        <br/>

                        {/** Select Vaccine Sessions */}
                        <Autocomplete
                           // className={classes.sessionListSelector}
                            id="sessionCenter"
                            value={sessionSelected}
                            options={sessionList}
                            getOptionLabel={option => option?.date === undefined ? '' : (option?.date + ' | ' + option?.vaccineName + ' | ' + option?.doseType + (option?.activeStatus === 'C' ? ' | Cancelled' : ''))}
                            renderInput={params => (
                                <TextField
                                    {...params}
                                    variant="outlined"
                                    label="Select the session"
                                />
                            )}
                            onChange={(event, newValue) => {
                                handleSessionSelection(event, newValue);
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
                                {registerUserList ? (
                                    <DataGrid
                                        pagination
                                        showColumnRightBorder
                                        disableColumnMenu
                                        rows={registerUserList}
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
        </div>
    );
};

//  mention the reducer
function mapStateToProps({ session, signin, registrationList }) {
    // what inside the reducer
    let loginSuccess = sessionStorage.getItem('loginSuccess');
    let { districts } = session;
    let { sessionsSelected, registeredUsers } = registrationList;

    // define attr inside the objs
    return {
        loginSuccess,
        loading: session.loading || registrationList.loading || registeredUsers.loading,
        districtList: districts.data,
        sessionList: sessionsSelected.selected,
        registerUserList: registeredUsers.data
    };
}

// bind the actions here in a object
export default connect(mapStateToProps, {
    fetchRegisteredUsers,
    fetchRegisteredUsersReset,
    fetchDistricts,
    fetchSelectedSession,
    fetchSelectedSessionReset,
    forceUpdate,
})(Registrations);
