import React, { Component } from "react";
import Friend from "./Friend";

import "./Messenger.css";

class Friends extends Component {
  state = {
    friendList: [
      { id: 1, name: "test", lastMessage: "text" },
      { id: 2, name: "tes2t_name", lastMessage: "text" }
    ]
  };

  componentDidMount() {
    
  }

  render() {
    return (
      <React.Fragment>
        <div className="messaging">
          <div className="inbox_msg">
            <div className="inbox_people">
              <div className="headind_srch"></div>
              <div className="inbox_chat">
                  {this.state.friendList.map(friend => (
                    <Friend key={friend.id} name={friend.name} msg={friend.lastMessage}/>
                  ))}
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default Friends;
