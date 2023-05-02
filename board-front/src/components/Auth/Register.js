import { useDispatch, useSelector } from "react-redux";
import AuthButton from "./AuthButton";
import AuthContent from "./AuthContent";
import InputWithLabel from "./InputWithLabel";
import { useEffect } from "react";
import { changeInput, initializeForm } from "../../redux/modules/auth";

function Register() {
  let dispatch = useDispatch()
  useEffect(() => {
    return() => {
      dispatch(initializeForm())
      // console.log('auth re-initialized')
    }
  }, [])
  
  function handleChange(e) {
    const { name, value } = e.target
    let payload = {form:'register', name, value}
    dispatch(changeInput(payload))
  }

  return (
    <AuthContent title="회원가입">
      <InputWithLabel
        label='아이디'
        name='userId'
        placeholder='example'
        onChange={(e) => handleChange(e)} />
      <InputWithLabel
        label='비밀번호'
        name='password'
        placeholder='password'
        type='password'
        onChange={(e) => handleChange(e)} />
      <InputWithLabel
        label='비밀번호 확인'
        name='passwordConfirm'
        placeholder='password'
        type='password'
        onChange={(e) => handleChange(e)} />
      <InputWithLabel
        label='이메일'
        name='email'
        placeholder='example@company.com'
        onChange={(e) => handleChange(e)} />
      <AuthButton>회원가입</AuthButton>
    </AuthContent>
  )
}

export default Register