import { createSlice } from "@reduxjs/toolkit"
import { Map } from "immutable"

const initialState = Map({
  register: Map({
    form: Map({
      email: '',
      username: '',
      password: '',
      passwordConfirm: ''
    })
  }),
  login: Map({
    form: Map({
      email: '',
      password: ''
    })
  })
});

const auth = createSlice({
  name: 'auth',
  initialState,
  
  reducers: {
    changeInput(state, action) {
      const { form, name, value } = action.payload
      return state.setIn([form, 'form', name], value)
    },
    initializeForm(state, action) {
      const initialForm = initialState.get(action.payload)
      return state.set(action.payload, initialForm)
    }
  }
})

export const { changeInput, initializeForm } = auth.actions
export default auth.reducer