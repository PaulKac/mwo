import React from 'react';
import loginUtils from '../config/loginUtils';
import history from "../config/history";
import {configuration} from "../config/config";
import client from "../config/client";
import UserTable from "./userTable";

class UserPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user: '', tables: []
        }
    }

    componentWillMount() {
        let userName = loginUtils.getCurrentUser();

        client({
            method: 'GET',
            path: `${configuration.path}/api/db/users/search/findByName?name=${userName}`
        }).then(user => {
            return client({
                method: 'GET',
                path: `${configuration.path}/api/db/tables/search/findByUser?user=${user.entity._links.self.href}`
            }).then(tables => {
                this.tables = tables.entity._embedded.clientTableInfoes;
                return user;
            });
        }).done(user => {
            this.setState({
                user: user.entity,
                tables: this.tables
            });
        });
    }

    render() {
        let tables = this.state.tables.map((table, i) => <UserTable key={i} columns={table.columns} tableName={table.collectionName}/>)

        return (
            <div className="page-header">
                <h1>Hey {this.state.user.name}, these are your tables:</h1>
                {tables}
            </div>

        )
    }
}

export default UserPage;