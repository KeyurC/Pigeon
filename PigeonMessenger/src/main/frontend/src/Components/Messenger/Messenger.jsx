import React, { Component } from "react";
import Friends from "./Friends";

import "./Messenger.css";

class Messenger extends Component {
  state = {};
  render() {
    return (
      <React.Fragment>
        <div className="container-fluid p-0 m-0">
          <Friends user={this.props.history.location.state.user_name}/>
        </div>
      </React.Fragment>
    );
  }
}

export default Messenger;
