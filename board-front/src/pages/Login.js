import { InputGroup, Form, Button } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'
function Login() {
  const navigate = useNavigate()

  return (
    <>
      <h1>로그인</h1>
      <Form.Label>아이디</Form.Label>
      <InputGroup className='mb-3'>
        <Form.Control
          placeholder='아이디'
          aria-label='userId'
          aria-describedby='basic-addon1'
          name='userId' />
        <Button className='btn btn-primary'>중복확인</Button>
      </InputGroup>

      <Form.Label>비밀번호</Form.Label>
      <InputGroup className='mb-3'>
        <Form.Control
          type='password'
          placeholder='비밀번호'
          aria-label='password'
          aria-describedby='basic-addon1'
          name='password'
        />
      </InputGroup>

      <div style={{textAlign: 'center'}}>
        <Button className='btn btn-primary' type='submit'>로그인</Button>
        <Button className='btn btn-primary' onClick={() => {navigate('/join')}}>회원가입</Button>
      </div>
    </>
  )
}

export default Login