/* import React from 'react';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import Typography from '@material-ui/core/Typography';
import AddIcon from '@material-ui/icons/Add';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import 'date-fns';

const useStyles = makeStyles(theme => ({
    root: {
        padding: theme.spacing(4),
    },
    model: {
        boxShadow: '0px 0px 14px #0000000D',
        borderRadius: '20px',
    },
    inputLabel: {
        letterSpacing: '0px',
        color: '#043B84',
    },
    inputFeild: {
        background: '#F3F3F3 0% 0% no-repeat padding-box',
        borderRadius: '2px',
        height: '53px',
    },
}));

const DialogTitle = withStyles(theme => ({
    root: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        paddingTop: theme.spacing(2),
    },
    mainFont: {
        color: '#004F8B',
        'font-weight': 1000,
    },
}))(props => {
    const { children, classes, onClose, ...other } = props;
    return (
        <MuiDialogTitle disableTypography {...other}>
            <div className={classes.root}>
                <div>
                    <Typography variant="h2" className={classes.mainFont}>
                        {children}
                    </Typography>
                </div>
            </div>
        </MuiDialogTitle>
    );
});

const DialogContent = withStyles(theme => ({
    root: {
        padding: theme.spacing(2),
    },
}))(MuiDialogContent);

const DialogActions = withStyles(theme => ({
    root: {
        margin: 0,
        padding: theme.spacing(1),
    },
}))(MuiDialogActions);

const SubmitButton = withStyles(theme => ({
    root: {
        backgroundColor: '#004F8B',
        width: '100%',
        '&:hover': {
            backgroundColor: '#004F8B',
        },
    },
}))(Button);

const CenterAdd = () => {
    const classes = useStyles();

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };

    const top100Films = [
        { title: 'MOH Nugegoda', vaccines: 2000 },
        { title: 'MOH Mahara', vaccines: 1000 },
        { title: 'MOH Kadawatha', vaccines: 200 },
        { title: 'MOH Borella', vaccines: 3000 },
    ];

    return (
        <div>
            <Button
                onClick={handleClickOpen}
                variant=""
                color="primary"
                endIcon={<AddIcon />}
            >
                <Typography
                    component="h3"
                    variant="h6"
                    align="left"
                    className={classes.mainFont}
                >
                    Create center
                </Typography>
            </Button>
            <Dialog
                width="400px"
                onClose={handleClose}
                aria-labelledby="customized-dialog-title"
                open={open}
                className={classes.model}
            >
                <DialogTitle id="customized-dialog-title" onClose={handleClose}>
                    Create center
                </DialogTitle>
                <DialogContent dividers>
                    <div className={classes.inputLabel}>Vaccination Center</div>
                    <Autocomplete
                        className={classes.inputFeild}
                        id="combo-box-demo"
                        options={top100Films}
                        getOptionLabel={option => option.title}
                        renderInput={params => (
                            <TextField {...params} variant="outlined" />
                        )}
                    />
                    <br />
                    <div className={classes.inputLabel}>Name</div>
                    <TextField
                        className={classes.inputFeild}
                        required
                        id="outlined-required"
                        variant="outlined"
                    />
                    <br />
                    <br />
                    <div className={classes.inputLabel}>Contact person</div>
                    <TextField
                        className={classes.inputFeild}
                        required
                        id="outlined-required"
                        variant="outlined"
                    />
                    <br />
                    <br />
                    <div className={classes.inputLabel}>City</div>
                    <TextField
                        className={classes.inputFeild}
                        required
                        id="outlined-required"
                        variant="outlined"
                    />
                </DialogContent>
                <DialogActions>
                    <SubmitButton
                        className={classes.buttonStyle}
                        autoFocus
                        onClick={handleClose}
                        color="primary"
                        variant="contained"
                    >
                        Create
                    </SubmitButton>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default CenterAdd;
 */