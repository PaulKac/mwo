import React from 'react';
import history from '../config/history'

import Spinner from 'react-spin';
import loginUtils from '../config/loginUtils';

class LogoutPage extends React.Component {
    componentDidMount() {
        loginUtils.logout();
        history.push('/frontend/');
    }

    render() {
        return (
            <Spinner config={{width: 12, radius: 30}}/>
        )
    }
}

export default LogoutPage;