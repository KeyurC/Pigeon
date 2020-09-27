import React, { Component } from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Login from '../Login/Login'

class AppRoutes extends Component {
  state = {};
  render() {
    return (
      <Switch>
          <Route component={Login} exact path="/"/>
      </Switch>
    );
  }
}

export default AppRoutes;
