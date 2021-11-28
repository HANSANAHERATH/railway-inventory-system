import React, { useEffect } from "react";
import { connect } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import { withStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Typography from "@material-ui/core/Typography";
import ErrorDisplay from "views/Common/ErrorDisplay";
import Spinner from "views/Common/Spinner";
import { Redirect } from "react-router-dom";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TablePagination,
  TableRow,
  TextField,
} from "@material-ui/core";
import Autocomplete from "@material-ui/lab/Autocomplete";
import AddInventory from "./AddInventory";
import { getInventoryList, getLookupValues, getInventoryListReset } from "actions/ItemInventory";
import MuiAlert from "@material-ui/lab/Alert";
//import CircularProgress from '@mui/material/CircularProgress';

const useStyles = makeStyles((theme) => ({
  root: {
    "& .table--cell": {
      fontSize: "13px",
    },
  },
  layout: {
    width: "auto",
    marginLeft: theme.spacing(2),
    marginRight: theme.spacing(2),
    [theme.breakpoints.up(600 + theme.spacing(2) * 2)]: {
      width: "85%",
      marginLeft: "auto",
      marginRight: "auto",
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
    borderRadius: "20px",
  },
  buttons: {
    display: "flex",
    justifyContent: "flex-end",
  },
  mainFont: {
    color: "#004F8B",
    "font-weight": 1000,
  },
  textBox: {
    padding: "10px",
  },
  subtitle: {
    color: "#848484",
  },
  tableHeader: {
    display: "flex",
    justifyContent: "space-between",
  },
  tableHeaderTitle: {
    display: "flex",
    gap: "20px",
  },
  tableCell: {
    minWidth: "fit-content",
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
  cancellationMsg: {
    width: "100%",
    cursor: "auto",
    padding: "5px",
  },
  filterRow: {
    display: "flex",
    alignItems: "flex-end",
  },
  inputFeild: {
    width: "50%",
  },
  searchWrapper: {
    width: "100%",
    justifyContent: "center",
    display: "flex",
  },
  alertWrapper: {
    fontSize: '20px',
    alignItems: 'center',
    height: '70px',
    lineHeight: '30px',

  }
}));

function Alert(props) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

const StyledTableRow = withStyles((theme) => ({
  root: {
    "&:nth-of-type(odd)": {
      backgroundColor: theme.palette.action.hover,
    },
  },
}))(TableRow);

const StyledTableCell = withStyles((theme) => ({
  head: {
    backgroundColor: "#e6e6e6",
    color: theme.palette.common.black,
  },
  body: {
    fontSize: 15,
  },
}))(TableCell);

const ItemInventoryList = ({
  loading,
  error,
  loginSuccess,
  itemsInventoryList,
  getLookupValues,
  itemList,
  getInventoryList,
  balanceDto,
  getInventoryListReset,
}) => {
  const classes = useStyles();

  const [searchItemName, setSearchItemName] = React.useState("");
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const [select, setSelection] = React.useState(null);
  const [item, setItem] = React.useState(null);

  const handleSearchFieldChange = (event, value) => {
    setSearchItemName(value || "");
    if (value !== null) {
      getInventoryList(value.id);
      setItem(value);
    }else{
      getInventoryListReset();
    }
  };

  useEffect(() => {
    getLookupValues();
  }, [getLookupValues]);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const updateItemList = () => {
    setSelection(null);
    setItem();
  };

  const fetchExistingItemsInventory = () => {
    getInventoryList(item?.id);
    getLookupValues();
  };

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
                Item Inventory
              </Typography>
            </div>
            {searchItemName ? (
              <AddInventory
                seletedRowData={select}
                setSelection={setSelection}
                updateItemList={updateItemList}
                item={item}
                random={Math.random()}
                fetchExistingItemsInventory={fetchExistingItemsInventory}
              />
            ) : null}
          </div>
          <br />
          <br />
          <div
            style={{ height: "100%", width: "100%" }}
            className={classes.root}
          >
            <div className={classes.searchWrapper}>
              <Autocomplete
                className={classes.inputFeild}
                id="unitType"
                value={searchItemName}
                options={itemList}
                getOptionLabel={(option) => option?.itemName || ""}
                renderInput={(params) => (
                  <TextField {...params} variant="outlined" />
                )}
                onChange={(event, newValue) => {
                  handleSearchFieldChange(event, newValue);
                }}
              />

              <Autocomplete
                className={classes.inputFeild}
                id="unitType"
                value={searchItemName}
                options={itemList}
                getOptionLabel={(option) => option?.itemName || ""}
                renderInput={(params) => (
                  <TextField {...params} variant="outlined" />
                )}
                onChange={(event, newValue) => {
                  handleSearchFieldChange(event, newValue);
                }}
              />
            </div>
            <br />
            <br />
            {searchItemName !== "" ? (
              <div>
                <Alert severity="info" className={classes.alertWrapper}>
                  <div>Total Quantity: {balanceDto?.totalQuantity} {item.itemUnits.unitName}</div>
                  <div>Balance: {balanceDto?.balance} {item.itemUnits.unitName}</div>
                </Alert>
                <br />
                <br />
              </div>
            ) : null}

            {itemsInventoryList?.length === 0 ? (
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
                        <StyledTableCell width={"10%"}>DATE</StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          QUANTITY ({searchItemName?.itemUnits?.unitName})
                        </StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          REFERENCE
                        </StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          SHED STORE NO
                        </StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          DESCRIPTION
                        </StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          SUPERVISOR NAME
                        </StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          HANDOVER TO
                        </StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          ADDITIONAL NOTES
                        </StyledTableCell>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {itemsInventoryList
                        ?.slice(
                          page * rowsPerPage,
                          page * rowsPerPage + rowsPerPage
                        )
                        .map((item, index) => (
                          <StyledTableRow key={item.id}>
                            <StyledTableCell>{item?.date}</StyledTableCell>
                            <StyledTableCell>{item?.quantity}</StyledTableCell>
                            <StyledTableCell>{item?.reference}</StyledTableCell>
                            <StyledTableCell>
                              {item?.shedStoreNo}
                            </StyledTableCell>
                            <StyledTableCell>
                              {item?.description}
                            </StyledTableCell>
                            <StyledTableCell>
                              {item?.supervisorName}
                            </StyledTableCell>
                            <StyledTableCell>
                              {item?.handoverTo}
                            </StyledTableCell>
                            <StyledTableCell>
                              {item?.additionalNote}
                            </StyledTableCell>
                          </StyledTableRow>
                        ))}
                    </TableBody>
                  </Table>
                </TableContainer>
                <TablePagination
                  rowsPerPageOptions={[5, 10, 50]}
                  component="div"
                  count={itemsInventoryList?.length}
                  rowsPerPage={rowsPerPage}
                  page={page}
                  onChangePage={handleChangePage}
                  onChangeRowsPerPage={handleChangeRowsPerPage}
                />
              </React.Fragment>
            )}
          </div>
        </Paper>
      </main>
    </div>
  );
};

function mapStateToProps({ itemInventory }) {
  let loginSuccess = sessionStorage.getItem("loginSuccess");
  return {
    loginSuccess,
    loading: itemInventory.loading,
    itemList: itemInventory.lookups.data,
    error: null,
    itemsInventoryList: itemInventory?.inventory?.data,
    balanceDto: itemInventory?.inventory?.balanceDto,
  };
}

export default connect(mapStateToProps, { getLookupValues, getInventoryList,getInventoryListReset })(
  ItemInventoryList
);
