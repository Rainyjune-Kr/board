import OpenColor from "open-color";
import { Link } from "react-router-dom";
import styled from "styled-components";

const Aligner = styled.div`
  margin-top: 1rem;
  text-align: right;
`

const StyledLink = styled(Link)`
  color: ${OpenColor.gray[6]};
  &:hover {
    color: ${OpenColor.gray[7]};
  }
`

function RightAlignedLink ({to, children}) {
  return (
    <Aligner>
      <StyledLink to={to}>{children}</StyledLink>
    </Aligner>
  )
}

export default RightAlignedLink