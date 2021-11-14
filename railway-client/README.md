# Covid Vaccine System - ADMIN Portal - Sri Lanka
This repository is for the Covid Vaccination Citizen System in Sri Lanka, which will be implemented by ICTA in Sri Lanka. This is the Admin Portal

## How to Run

### Step 1: Install Yarn

First make sure you have yarn installed in your machine. Use the following link.

Yarn installation guide for any Operating System: https://classic.yarnpkg.com/en/docs/install

If you have a machine with **Ubuntu Operating System**, please run the following commands.

```
curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
```
```
echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list
```
```
sudo apt update && sudo apt install yarn
```

### Step 2: Make sure you have Node Installed where version is greater than or equal 12

type the following command to see the node version
```
node --version
```
Please google and see how to upgrade it to 12 or higher.

### Step 3: Clone Project

This repository can be cloned from Github

### Step 4: Install Dependencies

run the following commands within the project folder

```
yarn install
```

### Step 5: Update the environment file

Go to the `.env` file and update the relevant values.

```
REACT_APP_BACKEND_URL=<The host of the backend Service being Deployed>
```

An Example `.env` file is given below.
```
REACT_APP_BACKEND_URL=http://localhost:8080
```

### Step 6: Run the Frontend Application

Run the following command.

```
yarn start 
```
