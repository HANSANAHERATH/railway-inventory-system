import React, { useEffect } from "react";
import { connect } from "react-redux";
import { withStyles, makeStyles } from "@material-ui/core/styles";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import MuiDialogContent from "@material-ui/core/DialogContent";
import MuiDialogActions from "@material-ui/core/DialogActions";
import Typography from "@material-ui/core/Typography";
import AddIcon from "@material-ui/icons/Add";
import TextField from "@material-ui/core/TextField";
import "date-fns";
import MuiAlert from "@material-ui/lab/Alert";
import Paper from "@material-ui/core/Paper";
import Draggable from "react-draggable";
import Autocomplete from "@material-ui/lab/Autocomplete";

import DialogTitle from "./DialogTitle";
import { InputAdornment } from "@material-ui/core";
import { submitItemInventoryCreate, submitItemInventoryCreateReset } from "actions/ItemInventory";

function Alert(props) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

const useStyles = makeStyles((theme) => ({
  root: {
    padding: theme.spacing(4),
    fontFamily: '"Roboto", "Helvetica", "Arial", sans-serif',
  },
  paper: {
    borderRadius: "20px",
  },
  model: {
    boxShadow: "0px 0px 14px #0000000D",
    opacity: 1,
    borderRadius: "20px",
  },
  inputrow: {
    display: "flex",
    justifyContent: "space-between",
    gap: "15px",
    [theme.breakpoints.down(500)]: {
      display: "block",
      justifyContent: "space-between",
    },
  },
  datePicker: {
    marginTop: "0px",
    [theme.breakpoints.down(500)]: {
      width: "100%",
    },
  },
  inputLabel: {
    letterSpacing: "0px",
    color: "#043B84",
    fontFamily: '"Roboto", "Helvetica", "Arial", sans-serif',
  },
  inputFeild: {
    width: "100%",
    background: "#F3F3F3 0% 0% no-repeat padding-box",
    borderRadius: "2px",
    height: "53px",
    fontFamily: '"Roboto", "Helvetica", "Arial", sans-serif',
  },
  titleRow: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "end",
  },
  deleteIcon: {
    position: "absolute",
    right: "23px",
    top: "43px",
    color: "red",
  },
  confirmationDialog: {
    padding: "20px",
  },
  confirmationActions: {
    paddingTop: "20px",
    display: "flex",
    justifyContent: "center",
  },
  deleteConfirmBtn: {
    color: "red",
  },
  validateMsg: {
    color: "red",
    fontSize: 16,
  },
  createSession: {
    marginBottom: "20px",
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

const DialogContent = withStyles((theme) => ({
  root: {
    padding: theme.spacing(4),
  },
}))(MuiDialogContent);

const DialogActions = withStyles((theme) => ({
  root: {
    padding: theme.spacing(4),
  },
}))(MuiDialogActions);

const SubmitButton = withStyles((theme) => ({
  root: {
    backgroundColor: "#004F8B",
    width: "100%",
    "&:hover": {
      backgroundColor: "#004F8B",
    },
    height: "50px",
  },
}))(Button);

const AddInventory = ({
  submitFormItemRemove,
  confirmation,
  seletedRowData,
  error,
  submitFormItemCreate,
  item,
  submitItemInventoryCreate,
  submitItemInventoryCreateReset,
  fetchExistingItemsInventory,
  random,
}) => {
  const classes = useStyles();

  useEffect(() => {
    if (seletedRowData) {
      setEditMode(true);
      setOpen(true);
      setLoading(false);
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

  const [loading, setLoading] = React.useState(false);
  const [open, setOpen] = React.useState(false);
  const [editMode, setEditMode] = React.useState(false);

  const [itemName, setItemName] = React.useState("");
  const [quantity, setQuantity] = React.useState("");
  const [unitType, setUnitType] = React.useState([]);
  const [notes, setNotes] = React.useState("");
  const [reference, setReference] = React.useState("");
  const [shedStoreNo, setShedStoreNo] = React.useState("");
  const [description, setDescription] = React.useState("");
  const [supervisorName, setSupervisorName] = React.useState("");
  const [handoverTo, setHandoverTo] = React.useState("");
  const [itemInventoryType, setItemInventoryType] = React.useState("");
  
  const validForm =
    itemName !== "" && quantity !== "" && unitType !== "" && notes !== "" && itemInventoryType !== "";

  useEffect(() => {
    if (item != null) {
      setItemName(item.itemName);
      setUnitType(item?.itemUnits?.unitName);
    }
  }, [item,random]);

  /** Change state when change inputs */

  const handleNotesChange = (event) => {
    let temp = event.target.value || "";
    setNotes(temp);
  };

  const handleSelectInventoryType = (event, value) => {
    setItemInventoryType(value || '');
  }

  const handleQuantityChange = (event) => {
    let tmpSessionVaccines = Math.abs(event.target.value);
    setQuantity(tmpSessionVaccines);
  };

  const handleReferenceChange = (event) => {
    setReference(event.target.value);
  };

  const handleShedStoreNoChange = (event) => {
    setShedStoreNo(event.target.value);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const handleSupervisorNameChange = (event) => {
    setSupervisorName(event.target.value);
  };

  const handleHandoverToChange = (event) => {
    setHandoverTo(event.target.value);
  };

  /** Change state when change input End */

  /** Submit form for create a session */
  const handleInventoryCreateSubmission = () => {
    setLoading(true);
    submitItemInventoryCreate({
      id: null,
      additionalNote: notes,
      quantity: parseFloat(quantity),
      unitId: unitType?.id,
      userId: 0,
      reference: reference,
      shedStoreNo: shedStoreNo,
      description: description,
      supervisorName: supervisorName,
      handoverTo: handoverTo,
      itemsEntityId: item?.id,

    });
  };
  /** Submit form for create a session End*/

  /** Reset Form function */
  const handleSessionFormReset = (event) => {
    handleResetSessionObj();
    submitItemInventoryCreateReset({});
    setOpen(false);
    fetchExistingItemsInventory();
  };

  const handleResetSessionObj = () => {
    setItemName("");
    setQuantity("");
    setNotes("");
    setReference("");
    setShedStoreNo("");
    setDescription("");
    setSupervisorName("");
    setHandoverTo("");
  };
  /** Reset Form function */

  const sessionCreated =
    confirmation.payload?.statusMessage === "Create Success.";
  const sessionUpdated =
    confirmation.payload?.statusMessage === "Update Successful";
  const notEdit = !(sessionCreated || sessionUpdated);
  const sessionDeleted = notEdit
    ? confirmation.payload?.statusMessage === "Delete Successful"
    : false;

  const confirmed = sessionCreated || sessionUpdated || sessionDeleted || error;

  return (
    <div>
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
          Add Inventory
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
            borderRadius: "20px",
          },
        }}
        PaperComponent={PaperComponent}
      >
        <div className={classes.titleRow}>
          <DialogTitle
            style={{ cursor: "move" }}
            id="session-create-dialog-title"
            onClose={handleClose}
          >
            {" "}
            {!editMode ? "Create Inventory" : "Update Inventory"}
          </DialogTitle>
        </div>

        <DialogContent dividers>
          {error ? (
            <Alert severity="error">{error}</Alert>
          ) : sessionUpdated && editMode ? (
            <Alert severity="success">Item updated.</Alert>
          ) : sessionCreated ? (
            <Alert severity="success">New Inventory created.</Alert>
          ) : sessionDeleted ? (
            <Alert severity="success">Item removed.</Alert>
          ) : null}

          {!confirmed ? (
            <div>
              <div className={classes.inputLabel}>Item Name</div>
              <TextField
                className={classes.inputFeild}
                required
                type="text"
                id="itemName"
                variant="outlined"
                disabled
                //onChange={handleItemNameChange}
                value={itemName || ""}
              />
              <br />
              <br />

              <div className={classes.inputLabel}>Quantity</div>
              <TextField
                className={classes.inputFeild}
                required
                type="number"
                id="quantity"
                variant="outlined"
                onChange={handleQuantityChange}
                value={quantity || ""}
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start">{unitType}</InputAdornment>
                  ),
                  inputProps: { min: 0, max: 100000 },
                }}
              />

              <br />
              <br />
              <div className={classes.inputLabel}>Inventory Type</div>
              <Autocomplete
                className={classes.inputFeild}
                id="itemInventoryType"
                value={itemInventoryType}
                options={[{id: 1, name: 'Credited'}, {id: 2, name: 'Debited'}]}
                getOptionLabel={(option) => option?.name || ""}
                renderInput={(params) => (
                  <TextField 
                    {...params} 
                    variant="outlined"
                     />
                )}
                onChange={(event, newValue) => {
                  handleSelectInventoryType(event, newValue);
                 }}
              />
              <br />
              <br />

              <div className={classes.inputLabel}>Reference</div>
              <TextField
                className={classes.inputFeild}
                required
                type="text"
                id="reference"
                variant="outlined"
                onChange={handleReferenceChange}
                value={reference || ""}
              />

              <br />
              <br />

              <div className={classes.inputLabel}>Shed Store No</div>
              <TextField
                className={classes.inputFeild}
                required
                type="text"
                id="shedStoreNo"
                variant="outlined"
                onChange={handleShedStoreNoChange}
                value={shedStoreNo || ""}
              />

              <br />
              <br />

              <div className={classes.inputLabel}>Description</div>
              <TextField
                className={classes.inputFeild}
                required
                type="text"
                id="description"
                variant="outlined"
                onChange={handleDescriptionChange}
                value={description || ""}
              />

              <br />
              <br />

              <div className={classes.inputLabel}>Supervisor Name</div>
              <TextField
                className={classes.inputFeild}
                required
                type="text"
                id="supervisorName"
                variant="outlined"
                onChange={handleSupervisorNameChange}
                value={supervisorName || ""}
              />

              <br />
              <br />

              <div className={classes.inputLabel}>Handover To</div>
              <TextField
                className={classes.inputFeild}
                required
                type="text"
                id="handoverTo"
                variant="outlined"
                onChange={handleHandoverToChange}
                value={handoverTo || ""}
              />

              <br />
              <br />

              <div className={classes.inputLabel}>Additional Note</div>
              <TextField
                className={classes.inputFeild}
                required
                type="text"
                id="notes"
                variant="outlined"
                onChange={handleNotesChange}
                value={notes || ""}
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
                onClick={handleInventoryCreateSubmission}
                color="primary"
                variant="contained"
                disabled={loading || !validForm}
              >
                {loading ? "Creating..." : "Create"}
              </SubmitButton>
            ) : (
             null
            )
          ) : null}
        </DialogActions>
      </Dialog>
    </div>
  );
};

function mapStateToProps({ itemInventory }) {
  let { error, confirmation } = itemInventory;

  return {
    error,
    confirmation,
  };
}

export default connect(mapStateToProps, {
  submitItemInventoryCreate,
  submitItemInventoryCreateReset,
})(AddInventory);
