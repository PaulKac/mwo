import React from 'react';
import styled from 'styled-components';
import * as FontAwesome from 'react-icons/lib/fa';

const PaddedButton = styled.button`
    padding: '10px';
`;

class CreateTable extends React.Component {
    constructor(props) {
        super(props);

        this.state = {columns: [{name: "TestName", type: "TestType"}], tableCreated: false};

        this.handleAddColumn = this.handleAddColumn.bind(this);
    }

    handleTableCreate() {
        this.setState({tableCreated: true});
    }

    handleAddColumn(column) {
        var columnArray = this.state.columns;
        columnArray.push(column);
        this.setState({columns: columnArray});
    }

    render() {
        let userTable = this.state.tableCreated ?
            <UserTable columns={this.state.columns} addColumn={this.handleAddColumn}/> : '';

        return (
            <div>
                <PaddedButton type="button" className="btn btn-primary" disabled={this.state.tableCreated}
                              onClick={this.handleTableCreate.bind(this)}>Create table!</PaddedButton>
                {userTable}
            </div>
        )
    }
}

class UserTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {columnName: '', columnType: ''};

        this.handleColumnNameChange = this.handleColumnNameChange.bind(this);
        this.handleColumnTypeChange = this.handleColumnTypeChange.bind(this);
    }

    handleAddColumn(event) {
        event.preventDefault();
        this.props.addColumn({name: this.state.columnName, type: this.state.columnType});
    }

    handleColumnNameChange(event) {
        this.setState({columnName: event.target.value});
    }

    handleColumnTypeChange(event) {
        this.setState({columnType: event.target.value});
    }

    render() {
        let clientColumns = this.props.columns.map((column, i) => <td key={i}>{column.name}</td>);
        let clientRows = this.props.columns.map((column, i) => <td key={i}>{column.type}</td>);

        return (
            <div>
                <table className="table">
                    <thead className="thead-dark">
                    <tr>
                        {clientColumns}
                        <td>
                            <form onSubmit={this.handleAddColumn.bind(this)}>
                                <div className="form-group">
                                    <PaddedButton type="button" className="btn btn-default btn-lg" type="submit">
                                        <FontAwesome.FaPlusCircle/>
                                    </PaddedButton>
                                </div>
                                <div className="form-group">
                                    <input type="text" value={this.state.columnName}
                                           onChange={this.handleColumnNameChange}/>
                                </div>
                                <div className="form-group">
                                    <input type="text" value={this.state.columnType}
                                           onChange={this.handleColumnTypeChange}/>
                                </div>
                            </form>
                        </td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        {clientRows}
                    </tr>
                    </tbody>
                </table>
            </div>
        );
    }
}

export default CreateTable;