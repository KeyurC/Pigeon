import React, { Component } from "react";

import "./Messenger.css"

class Friend extends Component {
  state = {};
  render() {
    return (
      <React.Fragment>
        <div className="chat_list">
          <div className="chat_people">
              <div className="chat_ib">
                <h5>
                  {this.props.name}<span className="chat_date">Dec 25</span>
                </h5>
                <p>
                  {this.props.msg}
                </p>
              </div>
            </div>
          </div>
      </React.Fragment>
    );
  }
}

export default Friend;
