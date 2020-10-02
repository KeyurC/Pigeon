import React, { Component } from "react";
import { FaTimes, FaCheck } from "react-icons/fa";
import Axios from "axios";
class Request extends Component {
  state = {};

  acceptRequest = () => {
    console.log("acceppt");
    Axios.post("http://localhost:8080/acceptRequest", {
      params: {
        username: this.props.user,
        friendID: this.props.id
      }
    }).then(
      res => {
        this.props.updateComponents();
      },
      err => {
        console.log(JSON.stringify(err));
      }
    );
  };

  declineRequest = () => {
    console.log("decline");
    Axios.post("http://localhost:8080/declineRequest", {
      params: {
        username: this.props.user,
        friendID: this.props.id
      }
    }).then(
      res => {
      
      },
      err => {
        console.log(JSON.stringify(err));
      }
    );
  };

  render() {
    return (
      <React.Fragment>
        <div className="chat_list">
          <div className="chat_people">
            <div className="chat_ib">
              <h5>
                {this.props.name}
                <span onClick={this.acceptRequest} className="chat_date">
                  <FaCheck />
                </span>
                <span onClick={this.declineRequest} className="chat_date">
                  <FaTimes />
                </span>
              </h5>
              {/* <p>{this.props.msg}</p> */}
              <p>Add user as friend.</p>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default Request;
