import { configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import auth from "./modules/auth";

const nonSerializableCheckMiddleWare = getDefaultMiddleware({
  serializableCheck: false
})

export default configureStore({
  reducer: {
    auth: auth
  },
  middleware: nonSerializableCheckMiddleWare
})