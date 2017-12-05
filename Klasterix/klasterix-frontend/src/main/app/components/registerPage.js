import React from 'react';
import history from "../config/history";
import loginUtils from "../config/loginUtils";
import {configuration} from "../config/config";
import client from "../config/client";
import { Link } from 'react-router-dom';
import { Card, CardText } from 'material-ui/Card';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';


class RegisterPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            errors: {},
            user: {
                email: '',
                password: ''
            }
        };

        this.processForm = this.processForm.bind(this);
        this.changeUser = this.changeUser.bind(this);
    }

    changeUser(event) {
        const field = event.target.name;
        const user = this.state.user;
        user[field] = event.target.value;

        this.setState({
            user
        });
    }

    processForm(event) {
        event.preventDefault();

        let user = {};
        user.name = this.state.user.email;
        user.password = loginUtils.encryptPassword(this.state.user.password);

        client({
            method: 'POST',
            path: `${configuration.path}/api/register`,
            entity: user
        }).done(response => {
            if (response.status.code === 200) {
                loginUtils.logIn(response.entity);
                history.push("/frontend/");
            }
            //409 name arleady exists
            //400 zly form
        });
    }

    render() {
        return (
            <RegisterForm
                onSubmit={this.processForm}
                onChange={this.changeUser}
                errors={this.state.errors}
                user={this.state.user}
            />
        );
    }
}

class RegisterForm extends React.Component {
    render() {
        return(
            <Card className="container">
                <form action="/" onSubmit={this.props.onSubmit}>
                    <h2 className="card-heading">Sign Up</h2>

                    {this.props.errors.summary && <p className="error-message">{this.props.errors.summary}</p>}

                    <div className="field-line">
                        <TextField
                            floatingLabelText="Email"
                            name="email"
                            errorText={this.props.errors.email}
                            onChange={this.props.onChange}
                            value={this.props.user.email}
                        />
                    </div>

                    <div className="field-line">
                        <TextField
                            floatingLabelText="Password"
                            type="password"
                            name="password"
                            onChange={this.props.onChange}
                            errorText={this.props.errors.password}
                            value={this.props.user.password}
                        />
                    </div>

                    <div className="button-line">
                        <RaisedButton type="submit" label="Create New Account" primary />
                    </div>

                    <CardText>Already have an account? <Link to={'/frontend/login'}>Log in</Link></CardText>
                </form>
            </Card>
        );
    }
}

export default RegisterPage;