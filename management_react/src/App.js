import "./App.css";
import React from "react";
import Test from "./Test";
import Navbar from "./Components/Navbar";
import Login from "./Components/Login"
import { BrowserRouter, Route, Switch} from 'react-router-dom';
function App() {
  return (
    <BrowserRouter>
      <Navbar/>
      <Switch>
        <Route exact path="/">
          <Test/>
        </Route>
        <Route exact path="/Login">
          <Login/>
        </Route>
      </Switch>
    </BrowserRouter>
  );
}

export default App;
