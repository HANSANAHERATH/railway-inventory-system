import React, { useEffect,useState } from 'react';
import { connect } from 'react-redux';
import { makeStyles } from '@material-ui/core/styles';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import ErrorDisplay from 'views/Common/ErrorDisplay';
import Spinner from 'views/Common/Spinner';
import SessionAdd from './SessionAdd';
import {
  fetchSessions,
  cancelSession,
  cancelSessionReset,
  fetchCancelledSessions,
  notifyAll,
  fetchUnitTyps,
  fetchItems,
} from 'actions/session';
import { Redirect } from 'react-router-dom';
import MuiDialogActions from '@material-ui/core/DialogActions';
import MuiDialogContent from '@material-ui/core/DialogContent';
import {Button, colors, TextareaAutosize, TextField} from '@material-ui/core';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TablePagination from '@material-ui/core/TablePagination';
import Dialog from '@material-ui/core/Dialog';
import moment from 'moment';
import Alert from '@material-ui/lab/Alert';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogContentText from '@material-ui/core/DialogContentText';

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
    confirmationDialog: {
        padding: '20px',
    },
    confirmationActions: {
        paddingTop: '20px',
        display: 'flex',
        justifyContent: 'center',
    },
    deleteConfirmBtn: {
        color: 'red',
    },
    cancellationMsg: {
        width: '100%',
        cursor: 'auto',
        padding: '5px',
    },
    filterRow: {
        display: 'flex',
        alignItems: 'flex-end',
    },
}));

const DialogContent = withStyles(theme => ({
    root: {
        padding: theme.spacing(4),
    },
}))(MuiDialogContent);

const DialogActions = withStyles(theme => ({
    root: {
        padding: theme.spacing(4),
    },
}))(MuiDialogActions);

const SubmitButton = withStyles(theme => ({
    root: {
        backgroundColor: '#004F8B',
        width: '100%',
        '&:hover': {
            backgroundColor: '#004F8B',
        },
        height: '50px',
    },
}))(Button);

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

const Sessions = ({
    loading,
    error,
    loginSuccess,
    isOpenAlertMsgProp,
    fetchUnitTyps,
    fetchUnitTypsList,
    fetchItems,
    itemsList,
}) => {
    useEffect(() => {  
        setWindowsDimentions();
        fetchUnitTyps();
        fetchItems();
    }, [sessionStorage.getItem('token'), fetchUnitTyps, fetchItems]);

    const [localSessionListState, setLocalSessionListState] = React.useState([]);

    const reLoadData = () => {
        fetchUnitTyps();
        setSelection(null);
    };

    const cancelMsgRegex = new RegExp("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    const lastWhiteSpaceRegex = new RegExp("\s+$");

    const classes = useStyles();
    const [openAlert, setOpenAlert] = React.useState(false);
    const [notifyAllObj,setNotifyAllObj] = React.useState(false);
    const [select, setSelection] = React.useState(null);
    const [objectIndex, setObjectIndex] = React.useState([]);
    const [warningAlertMessage, setWarningAlertMessage] = React.useState('');
    const [isCancel, setIsCancel] = React.useState(null);
    const [fromDate, setFromDate] = useState('');
    const [toDate, setToDate] = useState('');
    const [validationMessage, setValidationMessage] = React.useState('');
    const [open, setOpen] = React.useState(false);
    const [cancellationMsg, setCancellationMsg] = React.useState('');
    const [districtFilter, setDistrictFilter] = React.useState('');

    const handleChangeCancellationMsg = event => {
        let tempMsg = event.target.value;
        setCancellationMsg(tempMsg);
    }

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleOpenAlert = (session, index, type) => {
        if(type === 'isCancel'){
            setObjectIndex({
                id : session.sid,
                index: index,
                session : session,
            });
            setWarningAlertMessage("warning - Are you sure you want to cancel the session?");
            setIsCancel(true);
        }else {
            setNotifyAllObj({
                session_id: session.sid,	
                moh_codes :[ session.locationDetailsResDto[0].lid],
                session_date : moment(session.sdate).format('DD-MM-YYYY')
            });
            setWarningAlertMessage("warning - Are you sure you want to send notifications?");
            setIsCancel(false);
        }
        setSelection(null);
        setOpenAlert(true);
    };

    const handleCloseAlert = () => {
        setOpenAlert(false);
        setIsCancel(null);
        setCancellationMsg('');
        fetchSessions();
    };

    const handleCancelSession = () => {
        cancelSession({
            session_id: objectIndex.id,
            msg: cancellationMsg,
        });
        handleCloseAlert();
    }

    function getSessionTime(time) {
        let hour = '';
        let minuite = '';
        let suffix = '';

        if (time) {
            let hourString = Number(time.split(':')[0]);
            hour = hourString % 12 || 12;
            minuite = time.split(':')[1];
            suffix = hourString >= 12 ? 'PM' : 'AM';
        }

        return `${hour}:${minuite} ${suffix}`;
    }

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = event => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    const handleItemSelection = (selectedItem, ramdomId) => {
        let obj = {
            ramdomId: ramdomId,
            select: selectedItem,
        };
        setSelection(obj);
    };

    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(5);

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

    const closeAlertMessage = () =>{
        cancelSessionReset();
        fetchSessions();
        updateCancelledSessionList();
    }

    const handleNotifyAbsorbedSessions = () => {
        notifyAll(notifyAllObj);
        fetchSessions();
        handleCloseAlert();
    }

    const updateCancelledSessionList = () => {
        fetchCancelledSessions();
        setSelection(null);
    }

    const updateItemList = () => {
      fetchItems();
      setSelection(null);
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
            {isMobile === false ? (
              ""
            ) : (
              <SessionAdd
                seletedRowData={select}
                setSelection={setSelection}
                updateItemList={updateItemList}
              />
            )}
            <div className={classes.tableHeader}>
              <div className={classes.tableHeaderTitle}>
                <Typography
                  component="h1"
                  variant="h2"
                  align="left"
                  className={classes.mainFont}
                >
                  Items
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
                      reLoadData();
                    }}
                  ></i>
                </Typography>
              </div>
              {isMobile === true ? (
                ""
              ) : (
                <SessionAdd
                  seletedRowData={select}
                  setSelection={setSelection}
                  updateItemList={updateItemList}
                />
              )}
            </div>
            <br />
            <br />
            <div
              style={{ height: "100%", width: "100%" }}
              className={classes.root}
            >
              {itemsList?.length === 0 ? (
                <Typography component="h6" variant="h6" align="center">
                  No Data
                </Typography>
              ) : (
                <React.Fragment>
                  <TableContainer component={Paper}>
                    <Table
                      className={classes.table}
                      aria-label="customized table"
                    >
                      <TableHead>
                        <TableRow>
                          <StyledTableCell width={"10%"}>
                            ITEM NAME
                          </StyledTableCell>
                          <StyledTableCell width={"10%"}>
                            DATE
                          </StyledTableCell>
                          <StyledTableCell width={"10%"}>
                            QUANTITY
                          </StyledTableCell>
                          <StyledTableCell width={"10%"}>
                            UNIT TYPE
                          </StyledTableCell>
                          <StyledTableCell width={"10%"}>
                            NOTES
                          </StyledTableCell>
                          <StyledTableCell width={"10%"}>
                            ACTION
                          </StyledTableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {console.log(itemsList?.length)}
                        {itemsList
                          ?.slice(
                            page * rowsPerPage,
                            page * rowsPerPage + rowsPerPage
                          )
                          .map((item, index) => (
                            <StyledTableRow
                              key={item.id}
                            >
                               <StyledTableCell component="th" scope="row">
                                {item?.itemName}
                              </StyledTableCell>
                              <StyledTableCell>
                                {item?.date}
                              </StyledTableCell>
                              <StyledTableCell>
                                {item?.quantity}
                              </StyledTableCell>
                              <StyledTableCell>
                                {item?.itemUnits?.unitName}
                              </StyledTableCell>
                              <StyledTableCell>
                                {item?.notes}
                              </StyledTableCell>
                              <StyledTableCell>
                                <Button onClick={() => {handleItemSelection(item, Math.random())}}>Edit</Button>
                              </StyledTableCell>
                            </StyledTableRow>
                          ))}
                      </TableBody>
                    </Table>
                  </TableContainer>
                  <TablePagination
                    rowsPerPageOptions={[5, 10, 50]}
                    component="div"
                    count={itemsList?.length}
                    rowsPerPage={rowsPerPage}
                    page={page}
                    onChangePage={handleChangePage}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                  />
                </React.Fragment>
              )}
            </div>
            {/** Notify and cancel confirmation alert */}
            <Dialog
              open={openAlert}
              onClose={handleCloseAlert}
              aria-labelledby="alert-dialog-title"
              aria-describedby="alert-dialog-description"
            >
              {/*<div className={classes.confirmationDialog}>*/}
              <DialogTitle id="alert-dialog-title">
                <Alert variant="filled" severity="warning">
                  {warningAlertMessage}
                </Alert>
              </DialogTitle>
              <DialogContent>
                <DialogContentText id="alert-dialog-description">
                  <span style={{ color: "red" }}>IMPORTANT : </span> After you
                  click <b>YES</b> you won't be able to revert back your action.
                </DialogContentText>

                {isCancel ? (
                  <React.Fragment>
                    <TextareaAutosize
                      className={classes.cancellationMsg}
                      id="cancellationText"
                      aria-label="minimum height"
                      rowsMin={4}
                      placeholder="Cancellation Reason"
                      value={cancellationMsg || ""}
                      onChange={handleChangeCancellationMsg}
                      inputProps={{ maxLength: 30 }}
                    />
                    <p>
                      *Max character length is 30 and does not allow special
                      characters.
                    </p>
                  </React.Fragment>
                ) : null}
              </DialogContent>
              <DialogActions>
                {isCancel &&
                cancellationMsg !== "" &&
                cancellationMsg.split(" ").join("").length > 0 &&
                cancelMsgRegex.test(cancellationMsg) &&
                cancellationMsg.length < 31 ? (
                  <Button
                    onClick={handleCancelSession}
                    className={classes.deleteConfirmBtn}
                  >
                    Yes
                  </Button>
                ) : !isCancel ? (
                  <Button
                    onClick={handleNotifyAbsorbedSessions}
                    className={classes.deleteConfirmBtn}
                  >
                    Yes
                  </Button>
                ) : null}
                <Button onClick={handleCloseAlert}> No </Button>
              </DialogActions>
            </Dialog>

            {/* ************ Notify and Cancel success/error Alert Box ************* */}

            <Dialog
              fullWidth
              maxWidth="sm"
              onClose={closeAlertMessage}
              aria-labelledby="customized-dialog-title"
              open={isOpenAlertMsgProp}
              className={classes.model}
              PaperProps={{
                style: {
                  borderRadius: "20px",
                },
              }}
              //PaperComponent={PaperComponent}
            >
              <DialogContent dividers>
                {/* {notifyResponse.hasOwnProperty("status") ? (
                  notifyResponse.status ? (
                    <Alert severity="success">
                      {notifyResponse?.error_msg}
                    </Alert>
                  ) : (
                    <Alert severity="error">{notifyResponse?.error_msg}</Alert>
                  )
                ) : null}
                {cancelResponse.hasOwnProperty("status") ? (
                  cancelResponse.status ? (
                    <Alert severity="success">
                      {cancelResponse?.error_msg}
                    </Alert>
                  ) : (
                    <Alert severity="error">{cancelResponse?.error_msg}</Alert>
                  )
                ) : null} */}
              </DialogContent>
              <DialogActions>
                <SubmitButton
                  className={classes.buttonStyle}
                  autoFocus
                  onClick={closeAlertMessage}
                  color="primary"
                  variant="contained"
                >
                  OK
                </SubmitButton>
              </DialogActions>
            </Dialog>
          </Paper>
        </main>

        {/** Date Validation Dialog */}
        <Dialog
          open={open}
          onClose={handleClose}
          aria-labelledby="alert-dialog-title"
          aria-describedby="alert-dialog-description"
        >
          <DialogTitle id="alert-dialog-title">Error</DialogTitle>
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

//  mention the reducer
function mapStateToProps({ session, signin }) {
    // what inside the reducer
    let loginSuccess = sessionStorage.getItem('loginSuccess');
    let {  loading, items } = session;
    let fetchUnitTypsList = [];
    // define attr inside the objs
    return {
        loginSuccess,
        loading: loading,
        error: null,
        itemsList: items.data,
    };
}

// bind the actions here in a object
export default connect(mapStateToProps, {
    fetchUnitTyps,
    fetchItems,
})(Sessions);
