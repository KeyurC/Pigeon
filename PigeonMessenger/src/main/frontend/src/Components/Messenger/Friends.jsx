import React, { Component } from "react";
import Friend from "./Friend";

import "./Messenger.css";
import Axios from "axios";

class Friends extends Component {
  state = {
    friendsList: []
  };

  componentDidMount() {
    const user = this.props.user;
    Axios.get("http://localhost:8080/getAllFriends", {
      params: {
        username: user
      }
    }).then(
      res => {
        let friends = res.data;
        const friendsList = [];

        for (let i = 0; i < friends.length; i++) {
          let friend = friends[i].split(",");
          let friendJson = { id: friend[0], name: friend[1], lastMessage: "text" };
          friendsList.push(friendJson);
        }
        
        this.setState( { friendsList })
      },
      err => {
        console.log(err);
      }
    );
  }

  render() {
    return (
      <React.Fragment>
        <div className="messaging">
          <div className="inbox_msg">
            <div className="inbox_people">
              <div className="headind_srch"></div>
              <div className="inbox_chat">
                {this.state.friendsList.map(friend => (
                  <Friend
                    key={friend.id}
                    name={friend.name}
                    msg={friend.lastMessage}
                  />
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
