import React from 'react';
import { Router } from 'react-router-dom';
import { Switch, Route } from 'react-router-dom'
import Nav from './nav';
import Home from './home';
import UserPage from './userPage';
import CreateTable from './createTable';
import createBrowserHistory from "../config/history";

class App extends React.Component {
    render() {
        return (
            <Router history={createBrowserHistory}>
                <div>
                    <Nav />

                    <Switch>
                        <Route exact path='/frontend/' component={Home} />
                        <Route exact path='/frontend/profile' component={UserPage}/>
                        <Route exact path='/frontend/createTable' component={CreateTable}/>
                    </Switch>
                </div>
            </Router>
        )
    }
}

export default App;