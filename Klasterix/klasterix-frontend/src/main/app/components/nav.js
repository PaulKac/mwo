import React from 'react';
import {NavLink} from 'react-router-dom';

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
                    <NavLink exact className='nav-link' activeClassName='nav-link active' to='/frontend'>Home</NavLink>
                </Padded>
                <Padded className="nav-item">
                    <NavLink exact className='nav-link' activeClassName='nav-link active'
                             to='/frontend/profile'>Profile</NavLink>
                </Padded>
            </ul>
        )
    }
}

export default Nav;