import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { makeStyles } from '@material-ui/core/styles';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import ErrorDisplay from 'views/Common/ErrorDisplay';
import Spinner from 'views/Common/Spinner';

import {
  fetchUnitTyps,
  fetchItems,
  fetchItemCategory,
  fetchItemsReset,
} from 'actions/goods';
import { Redirect } from 'react-router-dom';
import { Button, TextField } from '@material-ui/core';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TablePagination from '@material-ui/core/TablePagination';
import ItemAdd from './ItemAdd';
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
  searchWrapper: {
    width: "100%",
    justifyContent: "center",
    display: "flex",
  },
  inputFeild: {
    width: "50%",
  },
  highlightRow: {
    background: '#a2a28b !important',
  },
  notHighlightRow: {

  }
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

const ItemsList = ({
  loading,
  error,
  loginSuccess,
  isOpenAlertMsgProp,
  fetchUnitTyps,
  fetchUnitTypsList,
  fetchItems,
  itemsList,
  categoryList,
  fetchItemCategory,
  fetchItemsReset,
  totalCount,
}) => {
  useEffect(() => {
    setWindowsDimentions();
    fetchUnitTyps();
    fetchItemCategory();
  }, [fetchUnitTyps, fetchItemCategory]);

  const classes = useStyles();
  const [select, setSelection] = React.useState(null);
  const [itemCategory, setItemCategory] = React.useState('');
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
    if (itemCategory !== null) {
      fetchItems({
        categoryId: itemCategory?.id,
        pageSize: rowsPerPage,
        page: newPage,
      });
    }
  };

  const handleChangeRowsPerPage = event => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
    if (itemCategory !== null) {
      fetchItems({
        categoryId: itemCategory?.id,
        pageSize: parseInt(event.target.value, 10),
        page: 0,
      });
    }
  };

  const handleItemSelection = (selectedItem, ramdomId) => {
    let obj = {
      ramdomId: ramdomId,
      select: selectedItem,
    };
    setSelection(obj);
  };

  const handleCategoryChange = (event, value) => {
    setItemCategory(value || "");
    if (value !== null) {
      fetchItems({
        categoryId: value?.id,
        pageSize: rowsPerPage,
        page: page,
      });
    }else{
      fetchItemsReset();
    }
  };



  const [isMobile, setIsMobile] = React.useState(false);

  window.addEventListener('resize', function () {
    window.innerWidth < 600 ? setIsMobile(true) : setIsMobile(false);
  });

  window.addEventListener('load', function () {
    window.innerWidth < 600 ? setIsMobile(true) : setIsMobile(false);
  });

  function setWindowsDimentions() {
    window.innerWidth < 600 ? setIsMobile(true) : setIsMobile(false);
  }

  const updateItemList = () => {
    setItemCategory('');
    fetchItemsReset();
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
            <ItemAdd
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

            </div>
            {isMobile === true ? (
              ""
            ) : (
              <ItemAdd
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
            <div className={classes.searchWrapper}>
              <Autocomplete
                className={classes.inputFeild}
                id="unitType"
                value={itemCategory}
                options={categoryList}
                getOptionLabel={(option) => option?.name || ""}
                renderInput={(params) => (
                  <TextField {...params} variant="outlined" />
                )}
                onChange={(event, newValue) => {
                  handleCategoryChange(event, newValue);
                }}
              />
            </div>
            <br />
            <br />

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
                        <StyledTableCell width={"20%"}>
                          NAME
                        </StyledTableCell>
                        <StyledTableCell width={"20%"}>
                          MINIMUM QUANTITY
                        </StyledTableCell>
                        <StyledTableCell width={"20%"}>
                          TOTAL QUANTITY
                        </StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          UNIT
                        </StyledTableCell>
                        <StyledTableCell width={"20%"}>
                          DESCRIPTION
                        </StyledTableCell>
                        <StyledTableCell width={"10%"}>
                          ACTION
                        </StyledTableCell>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {itemsList.map((item, index) => (
                        <StyledTableRow
                          key={item.id}
                           className={item?.minQuantity < item?.totalQuantity ? classes.notHighlightRow : classes.highlightRow}
                        >
                          <StyledTableCell component="th" scope="row">
                            {item?.name}
                          </StyledTableCell>
                          <StyledTableCell>
                            {item?.minQuantity}
                          </StyledTableCell>
                          <StyledTableCell>
                            {item?.totalQuantity}
                          </StyledTableCell>
                          <StyledTableCell>
                            {item?.units?.name}
                          </StyledTableCell>
                          <StyledTableCell>
                            {item?.description}
                          </StyledTableCell>
                          <StyledTableCell>
                            <Button onClick={() => { handleItemSelection(item, Math.random()) }}>Edit</Button>
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

// mention the reducer
function mapStateToProps({ goods, signin }) {
  // what inside the reducer
  let loginSuccess = sessionStorage.getItem('loginSuccess');
  let { loading, items, itemCategoryList } = goods;
  let categoryList = itemCategoryList?.data;
  return {
    loginSuccess,
    loading: loading,
    error: null,
    itemsList: items.content,
    totalCount: items.totalCount,
    categoryList,
  };
}

// bind the actions here in a object
export default connect(mapStateToProps, {
  fetchUnitTyps,
  fetchItems,
  fetchItemCategory,
  fetchItemsReset,
})(ItemsList);
