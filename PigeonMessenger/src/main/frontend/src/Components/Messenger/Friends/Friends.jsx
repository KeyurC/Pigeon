import React, { Component } from "react";
import Friend from "./Friend";

import Axios from "axios";

class Friends extends Component {
  

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
          let friendJson = {
            id: friend[0],
            name: friend[1]
          };
          friendsList.push(friendJson);
        }
        this.props.updateFriendsList(friendsList)
      },
      err => {
        console.log(err);
      }
    );
  }

  returnFriendComponents() {
    if (this.props.friendOpen) {
      return (
        <React.Fragment>
          {this.props.friendsList.map(friend => (
            <Friend
              key={friend.id}
              name={friend.name}
              id={friend.id}
            />
          ))}
        </React.Fragment>
      );
    } else {
      return "";
    }
  }

  render() {
    return <React.Fragment>{this.returnFriendComponents()}</React.Fragment>;
  }
}

export default Friends;
