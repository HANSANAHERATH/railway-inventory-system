import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import Typography from '@material-ui/core/Typography';
import AddIcon from '@material-ui/icons/Add';
import DeleteIcon from '@material-ui/icons/Delete';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import 'date-fns';
import DateFnsUtils from '@date-io/date-fns';
import {
    MuiPickersUtilsProvider,
    KeyboardDatePicker,
} from '@material-ui/pickers';
import MuiAlert from '@material-ui/lab/Alert';
import {
    resetItemCreateForm,
    fetchCancelledSessions,
    submitFormItemCreate,
    submitFormItemUpdate,
    submitFormItemRemove,
} from 'actions/session';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import moment from 'moment';
import Paper from '@material-ui/core/Paper';
import Draggable from 'react-draggable';

import CheckBoxOutlineBlankIcon from '@material-ui/icons/CheckBoxOutlineBlank';
import CheckBoxIcon from '@material-ui/icons/CheckBox';
import DialogTitle from './DialogTitle';

const icon = <CheckBoxOutlineBlankIcon fontSize="small" />;
const checkedIcon = <CheckBoxIcon fontSize="small" />;

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

const useStyles = makeStyles(theme => ({
    root: {
        padding: theme.spacing(4),
        fontFamily: '"Roboto", "Helvetica", "Arial", sans-serif',
    },
    paper: {
        borderRadius: '20px',
    },
    model: {
        boxShadow: '0px 0px 14px #0000000D',
        opacity: 1,
        borderRadius: '20px',
    },
    inputrow: {
        display: 'flex',
        justifyContent: 'space-between',
        gap: '15px',
        [theme.breakpoints.down(500)]: {
            display: 'block',
            justifyContent: 'space-between',
        },
    },
    datePicker: {
        marginTop: '0px',
        [theme.breakpoints.down(500)]: {
            width: '100%',
        },
    },
    inputLabel: {
        letterSpacing: '0px',
        color: '#043B84',
        fontFamily: '"Roboto", "Helvetica", "Arial", sans-serif',
    },
    inputFeild: {
        width: '100%',
        background: '#F3F3F3 0% 0% no-repeat padding-box',
        borderRadius: '2px',
        height: '53px',
        fontFamily: '"Roboto", "Helvetica", "Arial", sans-serif',
    },
    titleRow: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'end',
    },
    deleteIcon: {
        position: 'absolute',
        right: '23px',
        top: '43px',
        color: 'red',
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
    validateMsg: {
        color: 'red',
        fontSize: 16,
    },
    createSession: {
        marginBottom: '20px',
    },
}));

function PaperComponent(props) {
    return (
        <Draggable
            handle="#session-create-dialog-title"
            cancel={'[class*="MuiDialogContent-root"]'}
        >
            <Paper {...props} />
        </Draggable>
    );
}

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

const SessionAdd = ({
    submitFormItemRemove,
    confirmation,
    seletedRowData,
    resetItemCreateForm,
    error,
    unitTypeList,
    submitFormItemCreate,
    submitFormItemUpdate,
    updateItemList,
}) => {

    const classes = useStyles();
    const [selectedDataRow, setSelectedDataRow] = React.useState('');

    useEffect(() => {
        if (seletedRowData) {
            setSelectedDataToForm(seletedRowData.select);
            setEditMode(true);
            setOpen(true);
            setLoading(false);
            setSelectedDataRow(seletedRowData.select);
        }
    }, [seletedRowData]);

    /** Handle model from the create session button Start */
    const handleClickOpen = () => {
        setLoading(false);
        setEditMode(false);
        setOpen(true);
        //updateItemList();
    };
    
    const handleClose = () => {
        handleSessionFormReset();
        handleResetSessionObj();
        setOpen(false);
    };

     /** Handle model from the create session button End */

    const todayDate = new Date();
    const tomorrowDate = new Date(todayDate);
    tomorrowDate.setDate(tomorrowDate.getDate() + 1);

    const [loading, setLoading] = React.useState(false);
    const [open, setOpen] = React.useState(false);
    const [openAlert, setOpenAlert] = React.useState(false);
    const [editMode, setEditMode] = React.useState(false);

    const [itemName, setItemName] = React.useState('');
    const [quantity,setQuantity] = React.useState('');
    const [unitType,setUnitType] = React.useState([]);
    const [notes,setNotes] = React.useState('');
    const [date, setDate] = React.useState(moment(todayDate).format(moment.HTML5_FMT.DATE));
 
    const validForm =
        itemName !== '' &&
        quantity !== '' &&
        unitType !== '' &&
        notes !== '';
    
        /** Change state when change inputs */

        const handleItemNameChange = event =>{
            let temp = event.target.value || '';
            setItemName(temp);
        }

        const handleNotesChange = event =>{
            let temp = event.target.value || '';
            setNotes(temp);
        }

        const handleSessionDateChange = event => {
            let tmpDate = new Date(event);
            tmpDate = moment(tmpDate).format(moment.HTML5_FMT.DATE);
            setDate(tmpDate);
        };

        const handleQuantityChange = event => {
            let tmpSessionVaccines = Math.abs(event.target.value);
            setQuantity(tmpSessionVaccines);
        };

        const handleUnitTypeChange = (event, value) => {
            setUnitType(value || '');
        };

        /** Change state when change input End */

        /** Submit form for create a session */
        const handleSessionCreateSubmission = () => {
            setLoading(true);
            submitFormItemCreate({
                id: null,
                date: moment(date).format(moment.HTML5_FMT.DATE).toString(),
                itemName: itemName,
                notes: notes,
                quantity: parseFloat(quantity),
                unitId: unitType?.id,
                userId: 0,
            });
        }; 
        /** Submit form for create a session End*/

        /** submit for update a session  */
        const handleSessionUpdateSubmission = () => {
            setLoading(true);
            submitFormItemUpdate({
                id: selectedDataRow.id,
                date: moment(date).format(moment.HTML5_FMT.DATE).toString(),
                itemName: itemName,
                notes: notes,
                quantity: parseFloat(quantity),
                unitId: unitType?.id,
                userId: 0,
            });
        };
        /** submit for update a session END */

        /** set data to form when update session */
        const setSelectedDataToForm = selectedData => {   
            const selectedUnitType = unitTypeList.filter(
                res => res.id === selectedData.itemUnits.id
            )[0];

            setItemName(selectedData.itemName);
            setQuantity(selectedData.quantity);
            setUnitType(selectedUnitType);
            setNotes(selectedData.notes);
            setDate(selectedData.date);
        };

        /** set data to form when update session End*/

        /** Reset Form function */
        const handleSessionFormReset = event => {
            resetItemCreateForm({});
            handleResetSessionObj();
            setOpen(false);
            updateItemList();
        };

        const handleResetSessionObj = () => {
            setItemName('');
            setQuantity('');
            setUnitType('');
            setNotes('')
            setDate(todayDate);
        };
        /** Reset Form function */    

        /** open/close Delete confirmation alert */
        const handleOpenAlert = () => {
            setOpenAlert(true);
        };
    
        const handleCloseAlert = () => {
            setOpenAlert(false);
        };
        /** open/close Delete confirmation alert End*/

        /** Delete a session */
        const handleRemoveSession = () => {
            setOpenAlert(false);
            setLoading(true);
            submitFormItemRemove({
                id: selectedDataRow.id,
            });
        };
        /** Delete a session End*/

    const sessionCreated = confirmation.payload?.statusMessage === 'Saved Successful';
    const sessionUpdated = confirmation.payload?.statusMessage === 'Update Successful';
    const notEdit = !(sessionCreated || sessionUpdated);
    const sessionDeleted = notEdit ? confirmation.payload?.statusMessage === 'Delete Successful' : false;

    const confirmed = sessionCreated || sessionUpdated || sessionDeleted || error;

    return (
        <div>
            <Dialog
                open={openAlert}
                onClose={handleCloseAlert}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <div className={classes.confirmationDialog}>
                    Are you sure you want to delete this item ?
                    <div className={classes.confirmationActions}>
                        <Button
                            onClick={handleRemoveSession}
                            className={classes.deleteConfirmBtn}
                        >
                            Yes
                        </Button>
                        <Button onClick={handleCloseAlert}> No </Button>
                    </div>
                </div>
            </Dialog>

            {/** Create Session Button */}
            <Button
                className={classes.createSession}
                onClick={handleClickOpen}
                variant="text"
                color="primary"
                endIcon={<AddIcon />}
            >
                <Typography
                    component="h3"
                    variant="h6"
                    align="left"
                    className={classes.mainFont}
                >
                    Create Item
                </Typography>
            </Button>
            <Dialog
                fullWidth
                maxWidth="sm"
                onClose={handleClose}
                aria-labelledby="customized-dialog-title"
                open={open}
                disableBackdropClick
                className={classes.model}
                PaperProps={{
                    style: {
                        borderRadius: '20px',
                    },
                }}
                PaperComponent={PaperComponent}
            >
                 <div className={classes.titleRow}>
                    <DialogTitle
                        style={{ cursor: 'move' }}
                        id="session-create-dialog-title"
                        onClose={handleClose}
                    >
                        {' '}
                        {!editMode ? 'Create Item' : 'Update Item'}
                    </DialogTitle>
                    <div>
                        {editMode && !loading ? (
                            <Button
                                onClick={handleOpenAlert}
                                variant="text"
                                color="primary"
                                endIcon={<DeleteIcon />}
                                className={classes.deleteIcon}
                                disabled={loading}
                            >
                                Remove this Item
                            </Button>
                        ) : null}
                    </div>
                </div>

                <DialogContent dividers>
                    {error ? (
                        <Alert severity="error">{error}</Alert>
                    ) : sessionUpdated && editMode? (
                        <Alert severity="success">Item updated.</Alert>
                    ) : sessionCreated ? (
                        <Alert severity="success">New Item created.</Alert>
                    ) : sessionDeleted ? (
                        <Alert severity="success">Item removed.</Alert>
                    ) : null}

                    {!confirmed ? (
                        <div>
                            <div className={classes.inputLabel}>
                                Item Name
                            </div>
                            <TextField
                                className={classes.inputFeild}
                                required
                                type="text"
                                id="itemName"
                                variant="outlined"
                                onChange={handleItemNameChange}
                                value={itemName || ''}
                            />
                            <br />
                            <br />

                            {/**  Select Date Time  */}
                            <div className={classes.inputrow}>
                                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                    <KeyboardDatePicker
                                        className={classes.datePicker}
                                        label="Date"
                                        disableToolbar
                                       // disabled={editMode && isAbsorb}
                                        variant="inline"
                                        format="dd-MM-yyyy"
                                        margin="normal"
                                        id="sessionDate"
                                        value={date || todayDate}
                                        keyboardbuttonprops={{
                                            'aria-label': 'change date',
                                        }}
                                        minDate={!editMode ? todayDate :date}
                                        autoOk={true}
                                        onChange={handleSessionDateChange}
                                    />
                                </MuiPickersUtilsProvider>
                            </div>
                            <br />
                            <br />

                            {/**  Select Vaccine Count  */}
                            <div className={classes.inputLabel}>
                                Quantity
                            </div>
                            <TextField
                                className={classes.inputFeild}
                                required
                                type="number"
                                id="quantity"
                                variant="outlined"
                                onChange={handleQuantityChange}
                                value={quantity || ''}
                                InputProps={{
                                    inputProps: { min: 0, max: 100000 },
                                }}
                            />
                            <br />
                            <br />

                            {/** Age group */}
                            <div className={classes.inputLabel}>
                                Unit Type
                            </div>
                            <Autocomplete
                                className={classes.inputFeild}
                                id="unitType"
                                value={unitType}
                                options={unitTypeList}
                                getOptionLabel={option =>
                                    option?.unitName || ''
                                } 
                                renderInput={params => (
                                    <TextField {...params} variant="outlined" />
                                )}
                                onChange={(event, newValue) => {
                                    handleUnitTypeChange(event, newValue);
                                }}
                            />
                        
                            <br />
                            <br />
                            
                            {/**  Select Session Loacation Address  */}
                            <div className={classes.inputLabel}>
                                Notes
                            </div>
                            <TextField
                                className={classes.inputFeild}
                                required
                                type="text"
                                id="notes"
                                variant="outlined"
                                onChange={handleNotesChange}
                                value={notes || ''}
                           /> 
                        </div>
                    ) : null}
                </DialogContent>
                <DialogActions>
                    {confirmed ? (
                        <SubmitButton
                            className={classes.buttonStyle}
                            autoFocus
                            onClick={handleSessionFormReset}
                            color="primary"
                            variant="contained"
                        >
                            Ok
                        </SubmitButton>
                    ) : null}

                    {!confirmed ? (
                        !editMode ? (
                            <SubmitButton
                                className={classes.buttonStyle}
                                autoFocus
                                onClick={handleSessionCreateSubmission}
                                color="primary"
                                variant="contained"
                                disabled={loading || !validForm}
                            >
                                {loading ? 'Creating...' : 'Create'}
                            </SubmitButton>
                        ) : (
                            <SubmitButton
                                className={classes.buttonStyle}
                                autoFocus
                                onClick={handleSessionUpdateSubmission}
                                color="primary"
                                variant="contained"
                                disabled={loading || !validForm}
                            >
                                {loading ? 'loading...' : 'Update'}
                            </SubmitButton>
                        )
                    ) : null}
                </DialogActions>
            </Dialog>
        </div>
    );
};

function mapStateToProps({ session }) {
    let {error, confirmation}  = session;
    let unitTypeList = session?.unitTypes?.data;
    return {
        error,
        unitTypeList,
        confirmation,
    };
}

export default connect(mapStateToProps, {
    submitFormItemCreate,
    submitFormItemUpdate,
    submitFormItemRemove,
    resetItemCreateForm,
    fetchCancelledSessions,
})(SessionAdd);
