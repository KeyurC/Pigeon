import React, { Component } from "react";
import Friends from "./Friends";

import "./Messenger.css";

class Messenger extends Component {
  state = {};
  render() {
    return (
      <React.Fragment>
        <div className="container-fluid p-0 m-0">
          <Friends />
        </div>
      </React.Fragment>
    );
  }
}

export default Messenger;
