import React, { Component } from "react";
import Request from "./Request";

class Requests extends Component {
  state = {};

  componentDidMount() {
    this.props.onRequests();
  }

  requestDivHandler() {
    if (this.props.requestOpen) {
      return (
        <React.Fragment>
          {this.props.requests.map(requests => (
            <Request
              key={requests.user_id}
              user={this.props.user}
              name={requests.user_name}
              id={requests.user_id}
              updateFriends={this.props.onFriends}
              updateRequests={this.props.onRequests}
              updateComponents={this.props.updateComponents}
            />
          ))}
        </React.Fragment>
      );
    }
    return "";
  }

  render() {
    return <React.Fragment> {this.requestDivHandler()}</React.Fragment>;
  }
}

export default Requests;
