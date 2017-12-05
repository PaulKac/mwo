import React from 'react';
import styled from 'styled-components';
import * as FontAwesome from 'react-icons/lib/fa';
import client from '../config/client'
import {configuration} from '../config/config';

const PaddedButton = styled.button`
    padding: ${props => props.padding ? props.padding : '10px'};
    margin-top: '5px';
`;

class CreateTable extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            columns: [],
            isTableCreated: false,
            currentColumnName: '',
            currentColumnType: '',
            tempTableName: '',
            createdTableName: '',
            isInvalidColumnName: false,
            isInvalidTableName: false
        };

        this.handleAddColumn = this.handleAddColumn.bind(this);
        this.handleDeleteColumn = this.handleDeleteColumn.bind(this);
        this.handleColumnNameChange = this.handleColumnNameChange.bind(this);
        this.handleColumnTypeChange = this.handleColumnTypeChange.bind(this);

        this.handleTableCreate = this.handleTableCreate.bind(this);
        this.handleTableNameChange = this.handleTableNameChange.bind(this);

        this.handleSendTable = this.handleSendTable.bind(this);
    }

    handleTableCreate(event) {
        event.preventDefault();
        if (this.state.tempTableName === '') {
            this.setState({isInvalidTableName: true});
            return;
        }
        this.setState({isTableCreated: true, createdTableName: this.state.tempTableName, isInvalidTableName: false});
    }

    handleAddColumn(event) {
        event.preventDefault();
        if (this.state.currentColumnName === '') {
            this.setState({isInvalidColumnName: true});
            return;
        }
        for (var i = 0; i < this.state.columns.length; i++) {
            if (this.state.columns[i].name == this.state.currentColumnName) {
                this.setState({isInvalidColumnName: true});
                return;
            }
        }
        var columnArray = this.state.columns;
        columnArray.push({name: this.state.currentColumnName, type: this.state.currentColumnType});
        this.setState({columns: columnArray, isInvalidColumnName: false});
    }

    handleDeleteColumn(columnName) {
        var newColumns = this.state.columns.filter(function (column) {
            return column.name !== columnName;
        });
        this.setState({columns: newColumns});
    }

    handleColumnNameChange(event) {
        this.setState({currentColumnName: event.target.value});
    }

    handleColumnTypeChange(event) {
        this.setState({currentColumnType: event.target.value});
    }

    handleTableNameChange(event) {
        this.setState({tempTableName: event.target.value});
    }

    handleSendTable() {
        var tableCreationRequest = this.state.columns.reduce(function (map, obj) {
            map[obj.name] = obj.type;
            return map;
        }, {});

        client({
            method: 'POST',
            path: `${configuration.path}/api/create/gWCxg/${this.state.createdTableName}`,
            entity: tableCreationRequest
        }).done(response => {
            if (response.status.code === 200) {
                //TODO handle ok
            } else if (response.status.code === 208) {
                //TODO handle table already exists
            } else {
                //TODO handle other
            }
        });
    }

    render() {
        let userTable = this.state.isTableCreated ?
            <UserTable columns={this.state.columns} tableName={this.state.createdTableName}
                       addColumn={this.handleAddColumn}
                       deleteColumn={this.handleDeleteColumn}/> : '';

        let addColumnButton = this.state.isTableCreated ? <AddColumnForm handleNameChange={this.handleColumnNameChange}
                                                                         handleTypeChange={this.handleColumnTypeChange}
                                                                         handleAddColumn={this.handleAddColumn}
                                                                         invalidName={this.state.isInvalidColumnName}
                                                                         columnName={this.state.currentColumnName}
                                                                         columnType={this.state.currentColumnType}/> : '';

        return (
            <div>
                <div className="row" style={{display: '-webkit-flex', display: 'flex'}}>
                    <AddTableForm disabled={this.state.isTableCreated} tableName={this.state.tempTableName}
                                  handleTableNameChange={this.handleTableNameChange}
                                  invalidTableName={this.state.isInvalidTableName}
                                  handleSendTable={this.handleSendTable}
                                  columns={this.state.columns}
                                  handleAddTable={this.handleTableCreate}/>
                    {addColumnButton}
                </div>
                {userTable}
            </div>
        )
    }
}

class AddTableForm extends React.Component {
    render() {
        let nameInputClass = this.props.invalidTableName ? 'form-control is-invalid' : 'form-control';
        let invalidNameWarning = this.props.invalidTableName ?
            <small className="text-danger">Table name cannot be empty!</small> : '';
        let disabledSend = this.props.columns === undefined || this.props.columns == 0;

        return (
            <div className="col-xs-2" style={{padding: '20px', margin: '20px'}}>
                <div className="panel panel-success"
                     style={{padding: '0 0 0 0', borderBottom: 'none', marginBottom: '-1px'}}>
                    <div className="panel-heading">
                        <h3 className="panel-title" style={{fontWeight: 'bold'}}>
                            Add a table! <FontAwesome.FaStickyNoteO/>
                        </h3>
                    </div>
                    <div className="panel-body">
                        <div className="tab-content">
                            <form onSubmit={this.props.handleAddTable}>
                                <div className="form-group">
                                    <input className={nameInputClass} style={{color: 'black'}} type="text"
                                           value={this.props.tableName}
                                           readOnly={this.props.disabled} placeholder="Table name"
                                           onChange={this.props.handleTableNameChange} style={{fontWeight: 'bold'}}/>
                                    {invalidNameWarning}
                                </div>
                                <div className="form-group">
                                    <PaddedButton type="submit" className="btn btn-warning"
                                                  disabled={this.props.disabled}>Create
                                        table! <FontAwesome.FaCheck/></PaddedButton>
                                    <PaddedButton style={{marginTop: '7px'}} type="button" className="btn btn-warning"
                                                  onClick={this.props.handleSendTable}
                                                  disabled={disabledSend}>Send to
                                        server<FontAwesome.FaLocationArrow/></PaddedButton>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

class AddColumnForm extends React.Component {
    render() {
        let nameInputClass = this.props.invalidName ? 'form-control is-invalid' : 'form-control';
        let invalidNameWarning = this.props.invalidName ?
            <small className="text-danger">Invalid column name!</small> : '';

        return (
            <div className="col-xs-2" style={{padding: '20px', margin: '20px'}}>
                <div className="panel panel-success"
                     style={{padding: '0 0 0 0', borderBottom: 'none', marginBottom: '-1px'}}>
                    <div className="panel-heading">
                        <h3 className="panel-title" style={{fontWeight: 'bold'}}>
                            Add new column! <FontAwesome.FaStickyNote/>
                        </h3>
                    </div>
                    <div className="panel-body">
                        <div className="tab-content">
                            <form onSubmit={this.props.handleAddColumn}>
                                <div className="form-group">
                                    <input className={nameInputClass} style={{color: 'black'}} type="text"
                                           value={this.props.currentColumnName} placeholder="Column name"
                                           onChange={this.props.handleNameChange} style={{fontWeight: 'bold'}}/>
                                    {invalidNameWarning}
                                </div>
                                <div className="form-group">
                                    <select className="form-control form-control-lg"
                                            onChange={this.props.handleTypeChange}>
                                        <option>String</option>
                                        <option>Number</option>
                                        <option>Date</option>
                                    </select>
                                </div>
                                <div className="form-group">
                                    <PaddedButton type="submit" className="btn btn-warning"
                                                  disabled={this.props.disabled}>Create
                                        column! <FontAwesome.FaCheck/></PaddedButton>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

class UserTable extends React.Component {
    render() {
        let columns = this.props.columns.map((column, i) => <ClientColumn key={i} column={column}
                                                                          deleteColumn={this.props.deleteColumn}/>);

        return (
            <div className="w-75" style={{padding: '20px'}}>
                <div className="panel panel-primary"
                     style={{padding: '0 0 0 0', borderBottom: 'none', marginBottom: '-1px'}}>
                    <div className="panel-heading">
                        <h3 className="panel-title center" style={{fontWeight: 'bold'}}>
                            <FontAwesome.FaTable/> {this.props.tableName}
                        </h3>
                    </div>
                    <div className="panel-body">
                        <div className="tab-content">
                            {columns}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

class ClientColumn extends React.Component {
    deleteColumn() {
        this.props.deleteColumn(this.props.column.name);
    }

    render() {
        return (
            <div className="card col-md-2" style={{padding: '20px', margin: '10px'}}>
                <div className="card-block">
                    <h4 className="card-title">Name: {this.props.column.name}</h4>
                    <h6 className="card-subtitle mb-2 text-muted">Type: {this.props.column.type}</h6>
                    <a href="#" className="btn btn-danger" style={{marginTop: '5px'}}
                       onClick={this.deleteColumn.bind(this)}>Delete column <FontAwesome.FaClose/></a>
                </div>
            </div>
        );
    }
}

export default CreateTable;