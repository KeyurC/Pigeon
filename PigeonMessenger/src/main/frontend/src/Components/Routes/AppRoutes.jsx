import React, { Component } from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Login from '../Login/Login'
import Messages from '../Messenger/Messenger'

class AppRoutes extends Component {
  state = {};
  render() {
    return (
      <Switch>
          <Route component={Login} exact path="/"/>
          <Route component={Messages} path="/Messages"/>
      </Switch>
    );
  }
}

export default AppRoutes;
