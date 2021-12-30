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
  FormControlLabel,
  FormLabel,
  Radio,
  RadioGroup,
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
import { fetchItemCategory, fetchUnitTyps, } from "actions/goods";
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
    width: '100%',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-evenly',
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
  getInventoryListReset,
  categoryList,
  fetchItemCategory,
  fetchUnitTyps,
  unitList,
  totalCount,
}) => {
  const classes = useStyles();

  const [searchItemName, setSearchItemName] = React.useState("");
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const [select, setSelection] = React.useState(null);
  const [item, setItem] = React.useState(null);
  const [itemCategory, setItemCategory] = React.useState(null);
  const [inventoryType, setInventoryType] = React.useState(null);

  const handleSearchFieldChange = (event, value) => {
    setSearchItemName(value || "");
    if (value !== null) {
      setItem(value);
    } else {
      getInventoryListReset();
    }
  };

  const handleSelectItemCategory = (event, value) => {
    setItemCategory(value || "");
    if (value !== null) {
      getLookupValues(value?.id);
    } else {
      setSearchItemName('');
      getInventoryListReset();
    }
  };

  useEffect(() => {
    fetchUnitTyps();
    fetchItemCategory();
  }, [fetchUnitTyps, fetchItemCategory]);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
    if (itemCategory !== null && searchItemName !== "" && inventoryType !== null) {
      getInventoryList({
        item: item?.id,
        inventoryType: inventoryType,
        pageSize: rowsPerPage,
        pageNo: newPage,
      });
    }
  };

  const handleChangeRowsPerPage = event => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
    if (itemCategory !== null && searchItemName !== "" && inventoryType !== null) {
      getInventoryList({
        item: item?.id,
        inventoryType: inventoryType,
        pageSize: parseInt(event.target.value, 10),
        pageNo: page,
      });
    }
  };

  const updateItemList = () => {
    setSelection(null);
    setItem();
  };

  const fetchExistingItemsInventory = () => {
    // getInventoryList({
    //   item: item?.id,
    //   inventoryType: "ALL",
    //   pageSize: 10,
    //   pageNo: 0,
    // });
    getLookupValues(itemCategory?.id);
  };

  const handleChangeInventoryType = (event) => {
    setInventoryType(event.target.value);
    getInventoryList({
      item: item?.id,
      inventoryType: event.target.value,
      pageSize: 10,
      pageNo: 0,
    });
  }

  const getUnitName = (inventoryItem) => {
    return unitList.filter(res => res.id === inventoryItem?.unit)[0]?.name
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
              <div>Category</div>
              <Autocomplete
                className={classes.inputFeild}
                id="itemCategory"
                value={itemCategory}
                options={categoryList}
                getOptionLabel={(option) => option?.name || ""}
                renderInput={(params) => (
                  <TextField {...params} variant="outlined" />
                )}
                onChange={(event, newValue) => {
                  handleSelectItemCategory(event, newValue);
                }}
              />
            </div>
            <br />
            <div className={classes.searchWrapper}>
              <div>Item</div>
              <Autocomplete
                className={classes.inputFeild}
                id="unitType"
                value={searchItemName}
                options={itemList}
                getOptionLabel={(option) => option?.name || ""}
                renderInput={(params) => (
                  <TextField {...params} variant="outlined" />
                )}
                onChange={(event, newValue) => {
                  handleSearchFieldChange(event, newValue);
                }}
              />
            </div>
            <br />
            <div className={classes.searchWrapper}>
              <div></div>
              <RadioGroup
                aria-label="inventoryType"
                name="radio-buttons-group"
                row
                value={inventoryType}
                onChange={handleChangeInventoryType}

              >
                <FormControlLabel value="ALL" control={<Radio />} label="Goods In/Out" disabled={searchItemName === ""} />
                <FormControlLabel value="IN" control={<Radio />} label="Goods In" disabled={searchItemName === ""} />
                <FormControlLabel value="OUT" control={<Radio />} label="Goods Out" disabled={searchItemName === ""} />
              </RadioGroup>
            </div>
            <br />
            {searchItemName !== "" ? (
              <div>
                <Alert severity="info" className={classes.alertWrapper}>
                  {itemsInventoryList.length > 0 ? <div>Remain Quantity: {itemsInventoryList[0]?.totalQuantity} {unitList.filter(res => res.id === itemsInventoryList[0]?.unit)[0]?.name}</div> : <div>No Data</div>}
                </Alert>
                <br />
                <br />
              </div>
            ) : null}

            {totalCount === 0 ? (
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
                        <StyledTableCell width={"20%"}>DATE</StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          QUANTITY ({getUnitName(itemsInventoryList[0])})
                        </StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          GOODS IN/OUT
                        </StyledTableCell>
                        <StyledTableCell width={"20%"}>
                          SHED STORE NO
                        </StyledTableCell>
                        <StyledTableCell width={"20%"}>
                          DESCRIPTION
                        </StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          SUPERVISOR NAME
                        </StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          HANDOVER TO
                        </StyledTableCell>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {itemsInventoryList.map((item, index) => (
                        <StyledTableRow key={item.id}>
                          <StyledTableCell>{item?.date}</StyledTableCell>
                          <StyledTableCell>{item?.quantity}</StyledTableCell>
                          <StyledTableCell>{item?.inventoryType.split("_")[1]}</StyledTableCell>
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
                        </StyledTableRow>
                      ))}
                    </TableBody>
                  </Table>
                </TableContainer>
                <TablePagination
                  rowsPerPageOptions={[5, 10, 50]}
                  component="div"
                  count={totalCount}
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

function mapStateToProps({ itemInventory, goods }) {
  let loginSuccess = sessionStorage.getItem("loginSuccess");
  let { itemCategoryList, unitTypes } = goods;
  return {
    loginSuccess,
    loading: itemInventory.loading,
    itemList: itemInventory?.nameLookups?.data,
    error: null,
    itemsInventoryList: itemInventory?.inventory?.content,
    categoryList: itemCategoryList?.data,
    unitList: unitTypes?.data,
    totalCount : itemInventory?.inventory?.totalCount
  };
}

export default connect(mapStateToProps, {
  getLookupValues,
  getInventoryList,
  getInventoryListReset,
  fetchItemCategory,
  fetchUnitTyps,
})(ItemInventoryList);
