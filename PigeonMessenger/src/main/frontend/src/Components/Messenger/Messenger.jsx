import React, { Component } from "react";
import AcitvityHub from "./AcitvityHub";
import SockJS from "sockjs-client";
import Axios from "axios";
import Stomp from "stompjs";

import "./Messenger.css";

class Messenger extends Component {
  state = {
    username: this.props.history.location.state.user_name,
    friendsList: [],
    requests: []
  };

  getFriends = () => {
    Axios.get("http://localhost:8080/getAllFriends", {
      params: {
        username: this.state.username
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
        this.setState({ friendsList });
      },
      err => {
        console.log(err);
      }
    );
  };

  getRequests = () => {
    console.log(this.state.username);
    Axios.get("http://localhost:8080/getFriendRequests", {
      params: {
        username: this.state.username
      }
    }).then(
      res => {
        let requests = [];
        const postRequests = res.data;

        console.log(res.data);
        for (let i = 0; i < postRequests.length; i++) {
          requests.push(postRequests[i]);
        }
        this.setState({ requests });
      },
      err => {
        console.log(JSON.stringify(err));
      }
    );
  };

  updateComponents = () => {
    let self = this;
    setTimeout(function() {
      self.getRequests();
    }, 50);

    setTimeout(function() {
      self.getFriends();
    }, 50);
  };

  componentDidMount() {
    const sock = new SockJS("http://localhost:8080/ws");
    const stompClient = Stomp.over(sock);
    stompClient.debug = null;

    //Cant access this from inside websocket
    let self = this;

    stompClient.connect({}, function(frame) {
      const url = sock._transport.url;
      const sessionID = url.split("/")[5];

      const attr = {
        session: sessionID,
        username: self.state.username
      };

      self.state.stompClient.send("/app/register", {}, JSON.stringify(attr));

      stompClient.subscribe("/friend/greetings", greeting => {
        console.log("Test: " + greeting);
      });

      stompClient.subscribe("/friend/user", message => {
        console.log("Test: " + message);
      });

      stompClient.subscribe("/friend/friendsList-" + sessionID, () => {
        self.updateComponents();
      });
    });
    this.setState({ stompClient, sock });
  }

  render() {
    return (
      <React.Fragment>
        <div className="container-fluid p-0 m-0">
          <AcitvityHub
            onUpdate={this.updateComponents}
            friendsList={this.state.friendsList}
            requests={this.state.requests}
            onRequests={this.getRequests}
            getFriends={this.getFriends}
            user={this.state.username}
          />
        </div>
      </React.Fragment>
    );
  }
}

export default Messenger;
