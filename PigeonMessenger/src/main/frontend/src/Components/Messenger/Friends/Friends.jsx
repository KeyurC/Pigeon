import React, { Component } from "react";
import Friend from "./Friend";

class Friends extends Component {
  

  componentDidMount() {
    this.props.reloadFriends();
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
