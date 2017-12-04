import React from 'react';
import styled from 'styled-components'

const Title = styled.h1`
	font-size: 1.5em;
	margin-bottom: 50px;
	text-align: center;
`;

const Wrapper = styled.section`
	background: white;
	color: black;
`;

class Home extends React.Component {
    render() {
        return (
            <Wrapper>
                <Title>
                    Welcome to Klasterix! Enjoy.
                </Title>
            </Wrapper>
        )
    }
}

export default Home;