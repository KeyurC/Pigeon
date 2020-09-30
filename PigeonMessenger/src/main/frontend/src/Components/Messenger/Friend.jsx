import React, { Component } from "react";
import { FaGripLinesVertical, FaUserPlus } from "react-icons/fa";
import "./Messenger.css";

class Friend extends Component {
  state = {};

  addFriendIconHandler() {
    if (this.props.isFriend !== 1) {
      return (
        <React.Fragment>
          <FaUserPlus />
        </React.Fragment>
      );
    }
  }



  render() {
    return (
      <React.Fragment>
        <div className="chat_list">
          <div className="chat_people">
            <div className="chat_ib">
              <h5>
                {this.props.name}
                <span onClick={() => this.props.onAddFriend(this.props.id)} className="chat_date">{this.addFriendIconHandler()}</span>
              </h5>
              {/* <p>{this.props.msg}</p> */}
              <p>Test</p>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default Friend;
