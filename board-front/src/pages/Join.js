import { useState } from 'react'
import { AuthWrapper, Register } from '../components/Auth';

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
    <AuthWrapper>
      <Register/>
    </AuthWrapper>
  )
}

export default Join