import React from 'react';
import { Router } from 'react-router-dom';
import { Switch, Route } from 'react-router-dom'
import Nav from './nav';
import Home from './home';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import UserPage from './userPage';
import RegisterPage from './registerPage';
import LoginPage from './loginPage';
import LogoutPage from './logoutPage';
import CreateTable from './createTable';
import createBrowserHistory from "../config/history";

class App extends React.Component {
    render() {
        return (
            <MuiThemeProvider muiTheme={getMuiTheme()}>
                <Router history={createBrowserHistory}>
                    <div>
                        <Nav />

                        <Switch>
                            <Route exact path='/frontend/' component={Home} />
                            <Route exact path='/frontend/profile' component={UserPage}/>
                            <Route exact path='/frontend/createTable' component={CreateTable}/>
                            <Route exact path='/frontend/register' component={RegisterPage}/>
                            <Route exact path='/frontend/login' component={LoginPage}/>
                            <Route exact path='/frontend/logout' component={LogoutPage}/>
                        </Switch>
                    </div>
                </Router>
            </MuiThemeProvider>
        )
    }
}

export default App;