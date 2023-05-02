import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import Join from './pages/Join'
import Logon from './pages/Logon'
import { useNavigate, Route, Routes } from 'react-router-dom';
import { Navbar, Nav, Container } from 'react-bootstrap';
import Board from './pages/Board';
import { AdminPage, ManageUser, ManageBoard } from './pages/Admin';
import { useState } from 'react';

function App() {
  let navigate = useNavigate()
  let [user, setUser] = useState({id: null, userName: null, userLevel: null})

  return (
    <div className='App'>
      <Navbar bg="dark" variant="dark">
        <Container>
          <Navbar.Brand onClick={() => { navigate('/') }}>웹페이지</Navbar.Brand>
          <Nav className="me-auto">
            {
              user === null || typeof user === 'undefined' || user.id === null || String(user.id).length === 0 ?
                <>
                  <Nav.Link onClick={() => { navigate('/join') }}>회원가입</Nav.Link>
                  <Nav.Link onClick={() => { navigate('/logon') }}>로그인</Nav.Link>
                </>
                :
                null
            }
            {
              user.userLevel === "A" ? <Nav.Link onClick={() => { navigate('/admin')}}>관리자</Nav.Link> : null
            }
            <Nav.Link onClick={() => { navigate('/board')}}>게시판</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <Routes>
        <Route path='/' element={<h1>메인페이지</h1>} />
        <Route path='/logon' element={<Logon />} />
        <Route path='/join' element={<Join />} />
        <Route path='/board' element={<Board/>}/>
        <Route path='/admin' element={<AdminPage />}>
          <Route path='manageUser' element={<ManageUser />} />
          <Route path='manageBoard' element={<ManageBoard />} />
        </Route>
        <Route path='*' element={<h1>사용할 수 없는 페이지</h1>}/>
      </Routes>
    </div>
  );
}

export default App;
