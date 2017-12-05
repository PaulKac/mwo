import React from 'react';
import {NavLink} from 'react-router-dom';
import loginUtils from '../config/loginUtils';
import styled from 'styled-components';

const Padded = styled.li`
    padding: 10px 20px 0px 20px;
    font-size: 1.2em;
`;

class Nav extends React.Component {
    render() {

        return (
            <ul className="nav nav-tabs">
                <Padded className="nav-item">
                    <NavLink exact className='nav-link' activeClassName='nav-link active' to='/frontend/'>Home</NavLink>
                </Padded>
                {loginUtils.isLoggedIn() ? <Padded className="nav-item">
                    <NavLink exact className='nav-link' activeClassName='nav-link active'
                             to='/frontend/profile'>Profile</NavLink>
                </Padded> : null}

                {loginUtils.isLoggedIn() ? <Padded className="nav-item">
                    <NavLink exact className='nav-link' activeClassName='nav-link active'
                             to='/frontend/createTable'>Create table</NavLink>
                </Padded> : null}
                {loginUtils.isLoggedIn() ? <Padded className="nav-item">
                    <NavLink exact className='nav-link' activeClassName='nav-link active'
                             to='/frontend/logout'>Log out</NavLink>
                </Padded> : null}
                {!loginUtils.isLoggedIn() ? <Padded className="nav-item">
                    <NavLink exact className='nav-link' activeClassName='nav-link active'
                             to='/frontend/register'>Register</NavLink>
                </Padded> : null}
                {!loginUtils.isLoggedIn() ? <Padded className="nav-item">
                    <NavLink exact className='nav-link' activeClassName='nav-link active'
                             to='/frontend/login'>Log in
                    </NavLink>
                </Padded> : null}
            </ul>

        )
    }
}

export default Nav;