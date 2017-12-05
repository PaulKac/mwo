import React from 'react';
import styled from 'styled-components'

import RaisedButton from 'material-ui/RaisedButton';
require('../styles/material.css')
import loginUtils from '../config/loginUtils';
import history from '../config/history';

const Title = styled.h1`
	font-size: 1.5em;
	font-weight: bold;
	margin-bottom: 50px;
	text-align: center;
`;

const Wrapper = styled.section`
	background: white;
	color: black;
`;

class Home extends React.Component {
    goToLogin() {
        history.push("/frontend/login");
    }

    render() {
        let loginStatus = '';

        if (loginUtils.isLoggedIn()) {
            loginStatus = <Title>You are logged in {loginUtils.getCurrentUser()}!</Title>;
        } else {
            loginStatus = <div><Title>You are, not logged in :(</Title>
                <div className="button-line"><Title><RaisedButton type="submit" label="Log in" primary
                                                           onClick={this.goToLogin.bind(this)}/></Title></div>
            </div>
        }

        return (

            <Wrapper>
                <Title>
                    Welcome to Klasterix! Enjoy.

                    <form style={{maxWidth: 350}} className="form-signin mx-auto" method="POST" action="../loginToUser">
                        <h2 className="form-signin-heading">Logowanie</h2>
                        <label htmlFor="input" className="sr-only">Nazwa</label>
                        <input type="text" id="inputLogin" className="form-control p-6" placeholder="Nazwa użytkownika"
                               required autoFocus name="login"/>
                        <label htmlFor="inputPassword" className="sr-only">Hasło</label>
                        <input type="password" id="inputPassword" className="form-control mb-3 p-6" placeholder="Password"
                               required name="pass"/>
                        <button className="btn btn-primary btn-block" type="submit">Zaloguj</button>
                    </form>

                </Title>
                {loginStatus}
            </Wrapper>
        )
    }
}

export default Home;