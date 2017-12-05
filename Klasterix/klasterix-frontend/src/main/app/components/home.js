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
                </Title>
                {loginStatus}
            </Wrapper>
        )
    }
}

export default Home;