import React from 'react'
import styled from 'styled-components'
import { shadow } from '../../lib/styleUtil'
import oc from 'open-color'
import { Link } from 'react-router-dom'

const Positioner = styled.div`
  position: relative;
  top: 30%;
  left: 30%;
  transform: translate(0%, 30%);
`;

const ShadowedBox = styled.div`
  width: 500px;
  ${shadow(2)}
`;

const LogoWrapper = styled.div`
  background: ${oc.teal[7]};
  height: 5rem;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Logo = styled(Link)`
  color: white;
  font-family: 'Rajdhani';
  font-size: 2.4rem;
  letter-spacing: 5px;
  text-decoration: none;
`;

const Contents = styled.div`
  background: white;
  padding: 2rem;
  height: auto;
`;

function AuthWrapper ({children}){
  return(
    <Positioner>
      <ShadowedBox>
        <LogoWrapper>
          <Logo to="/">게시판</Logo>
        </LogoWrapper>
        <Contents>
          {children}
        </Contents>
      </ShadowedBox>
    </Positioner>
  )
}

export default AuthWrapper;

