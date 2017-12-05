import React from "react";
import * as FontAwesome from 'react-icons/lib/fa';

class UserTable extends React.Component {
    render() {
        let columns = [];

        for (var key in this.props.columns) {
            if (this.props.columns.hasOwnProperty(key)) {
                columns.push({name: key, type: this.props.columns[key]});
            }
        }
        let rendered = columns.map((column, i) => <ClientColumn key={i} column={column}/>);

        let tableName = this.props.tableName !== '' ? this.props.tableName.split("_").slice(1) : '';

        return (
            <div className="w-75" style={{padding: '20px'}}>
                <div className="panel panel-primary"
                     style={{padding: '0 0 0 0', borderBottom: 'none', marginBottom: '-1px'}}>
                    <div className="panel-heading">
                        <h3 className="panel-title center" style={{fontWeight: 'bold'}}>
                            <FontAwesome.FaTable/> {tableName}
                        </h3>
                    </div>
                    <div className="panel-body">
                        <div className="tab-content">
                            {rendered}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

class ClientColumn extends React.Component {
    render() {
        return (
            <div className="card col-md-2" style={{padding: '20px', margin: '10px'}}>
                <div className="card-block">
                    <h4 className="card-title">Name: {this.props.column.name}</h4>
                    <h6 className="card-subtitle mb-2 text-muted">Type: {this.props.column.type}</h6>
                </div>
            </div>
        );
    }
}

export default UserTable;