import { useDispatch, useSelector } from "react-redux";
import AuthButton from "./AuthButton";
import AuthContent from "./AuthContent";
import InputWithLabel from "./InputWithLabel";
import RightAlignedLink from "./RightAlignedLink";
import { changeInput, initializeForm } from "../../redux/modules/auth";
import { useEffect } from "react";

function Login () {
  let dispatch = useDispatch()
  
  useEffect(() => {
    return() => {
      dispatch(initializeForm())
      // console.log('auth re-initialized')
    }
  }, [])

  function handleChange(e) {
    const { name, value } = e.target
    let payload = {form:'login', name, value}
    dispatch(changeInput(payload))
  }

  return (
    <AuthContent title="로그인">
      <InputWithLabel 
        label='아이디' 
        name='userId' 
        placeholder='example'
        onChange={(e) => {handleChange(e)}}
        />
      <InputWithLabel
        label='Password'
        name='password'
        placeholder='password'
        type='password'
        onChange={(e) => {handleChange(e)}}/>
      <AuthButton>로그인</AuthButton>
      <RightAlignedLink to='/join'>회원가입</RightAlignedLink>
    </AuthContent>
  )
}

export default Login
