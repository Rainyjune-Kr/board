import { useState } from 'react'
import { InputGroup, Form, Button } from 'react-bootstrap'

function Join () {
  let [userInfo, setUserInfo] = useState({userId: null, password: null, passwordChk: null, email: null});
  function changeUserInfo(info, value) {
    if (info === 'userId')
      userInfo.userId = value
    else if (info === 'password')
      userInfo.password = value
    else if (info === 'passwordChk')
      userInfo.passwordChk = value
    else if (info === 'email')
      userInfo.email = value

    setUserInfo(userInfo)
  }

  return (
    <>
      <div style={{maxWidth:'1024px'}}>
        <h1>회원가입</h1>

        <Form.Label>아이디</Form.Label>
        <InputGroup className='mb-3'>
          <Form.Control
            placeholder='아이디'
            aria-label='userId'
            aria-describedby='basic-addon1'
            name='userId'
            onChange={(e) => { changeUserInfo('userId', e.target.value)}}
          />
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
            onChange={(e) => { changeUserInfo('password', e.target.value)}}
          />
        </InputGroup>

        <Form.Label>비밀번호 확인</Form.Label>
        <InputGroup className='mb-3'>
          <Form.Control
            type='password'
            placeholder='비밀번호 확인'
            aria-label='password_check'
            aria-describedby='basic-addon1'
            name='password_check'
            onChange={(e) => { changeUserInfo('passwordChk', e.target.value)}}
          />
        </InputGroup>

        <Form.Label>이메일</Form.Label>
        <InputGroup className='mb-3'>
          <Form.Control
            type='email'
            placeholder='name@example.com'
            aria-label='e-mail'
            aria-describedby='basic-addon1'
            name='email'
            onChange={(e) => { changeUserInfo('email', e.target.value)}}
          />
        </InputGroup>

        <Button className='btn btn-primary' type='submit'>제출</Button>
      </div>
    </>
  )
}

export default Join