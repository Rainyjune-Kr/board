import OpenColor from "open-color";
import styled from "styled-components";
import { shadow } from "../../lib/styleUtil";

const Wrapper = styled.div`
  margin-top: 1rem;
  padding-top: 0.6rem;
  padding-bottom: 0.5rem;

  background: ${OpenColor.teal[6]};
  color: white;

  text-align: center;
  font-size: 1.25rem;
  font-wieght: 500;

  cursor: pointer;
  user_select: none;
  transition: .2s all;

  &:hover {
    background: ${OpenColor.teal[5]}';
    ${shadow(0)}
  }

  &:active {
    background: ${OpenColor.teal[7]};
  }
`

function AuthButton ({children, onClick}) {
  return (
    <Wrapper onClick={onClick}>
      {children}
    </Wrapper>
  )
}

export default AuthButton