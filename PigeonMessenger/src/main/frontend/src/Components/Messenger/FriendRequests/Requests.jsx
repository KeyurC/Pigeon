import React, { Component } from "react";
import Request from "./Request";

class Requests extends Component {
  state = {
    requests: [{ id: 1, name: "test" }]
  };

  componentDidMount() {}

  requestDivHandler() {
    if (this.props.requestOpen) {
      return (
        <React.Fragment>
          {this.state.requests.map(requests => (
            <Request key={requests.id} name={requests.name} id={requests.id} />
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
