import React, { Component, useState } from "react";
import Friend from "./Friend";

import "./Messenger.css";
import Axios from "axios";

class Friends extends Component {
  state = {
    friendsList: [],
    divStatus: true
  };

  oldState = {
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
          let friendJson = {
            id: friend[0],
            name: friend[1],
            isFriend: 1
          };
          friendsList.push(friendJson);
        }

        this.setState({ friendsList });
        this.oldState.friendsList = this.state.friendsList;
      },
      err => {
        console.log(err);
      }
    );
  }

  handleFriendsSearch = () => {
    let friend = document.getElementById("search").value;
    if (friend == "") {
      this.setState({ friendsList: this.oldState.friendsList });
    } else {
      Axios.get("http://localhost:8080/searchUsers", {
        params: {
          username: this.props.user,
          friend: friend
        }
      }).then(
        res => {
          let test = [];

          for (let i = 0; i < res.data.length; i++) {
            let tmp = res.data[i];
            test.push(JSON.parse(tmp));
          }
          this.setState({ friendsList: test });
          console.log();
          console.log(this.state);
        },
        err => {
          console.log(err);
        }
      );
    }
  };

  handleAddFriend = id => {
    console.log(id);
    console.log(this.props.user);
  };

  friendDivToggle = () => {
    if (this.state.divStatus) {
      this.setState({divStatus:false})
    } else {
      this.setState({divStatus:true})
    }
  }

  returnFriendComponents() {

    if (this.state.divStatus) {
      return (
        <React.Fragment>
          {this.state.friendsList.map(friend => (
            <Friend
              key={friend.id}
              onAddFriend={this.handleAddFriend}
              name={friend.name}
              id={friend.id}
              isFriend={friend.isFriend}
            />
          ))}
        </React.Fragment>)
    } else {
      return "";
    }
  }

  render() {
    return (
      <React.Fragment>
        <div className="messaging">
          <div className="inbox_msg">
            <div className="inbox_people">
              <div className="headind_srch">
                <div className="input-group mb-3">
                  <input
                    type="text"
                    id="search"
                    className="form-control"
                    placeholder="username"
                    aria-describedby="basic-addon2"
                  />
                  <div className="input-group-append">
                    <button
                      onClick={this.handleFriendsSearch}
                      className="btn btn-outline-secondary"
                      type="button"
                    >
                      S
                    </button>
                  </div>
                </div>
              </div>

              <div className="headind_srch2">
                <p>Requests</p>
              </div>
              <div className="headind_srch2">
                <p onClick={this.friendDivToggle}>Friends </p>
              </div>
              <div className="inbox_chat">{this.returnFriendComponents()}</div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default Friends;
