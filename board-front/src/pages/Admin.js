import { Container, Nav, Row, Col } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import { Outlet, useNavigate } from "react-router-dom";


function AdminPage() {
  let navigate = useNavigate()
  return (
    <div>
      <Container className="conatiner" style={{ maxWidth: '100%' }}>
        <Row>
          <Col className="bg-dark col-md-2 min-vh-100">
            <a className="text-decoration-none text-white d-flex align-itemcenter">
              <span className="ms-1 fs-4">관리자 페이지</span>
            </a>
            <Nav className="nav nav-pills flex-column">
              <Nav.Link className="text-white fs-5" onClick={() => { navigate('./manageUser') }} aria-current='page'>사용자 관리</Nav.Link>
              <Nav.Link className="text-white fs-5" onClick={() => { }} aria-current='page'>게시판 관리</Nav.Link>
            </Nav>
          </Col>
          <Col>
            <Outlet></Outlet>
          </Col>
        </Row>
      </Container>
    </div>
  )
}

function ManageUser() {
  return <h1>유저관리페이지</h1>
}

function ManageBoard() {
  return <h1>게시판관리페이지</h1>
}

export { AdminPage, ManageUser, ManageBoard } 
