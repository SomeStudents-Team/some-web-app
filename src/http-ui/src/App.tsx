import React, {Component} from 'react';
import {AppProps} from "./models/app/AppProps";
import {AppStates} from "./models/app/AppStates";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Navbar from "./components/Navbar";
import {History} from "./pages/History";
import Main from "./pages/Main";
import Container from "@mui/material/Container";

class App extends Component<AppProps, AppStates> {

  render() {
    return <BrowserRouter>

      <Navbar />

      <Container fixed sx={{
        marginTop: '5%'
      }}>

        <Routes>
          <Route element={<Main />} path="/" />
          <Route element={<History />} path="/history" />
        </Routes>

      </Container>

    </BrowserRouter>
  }
}

export default App;
