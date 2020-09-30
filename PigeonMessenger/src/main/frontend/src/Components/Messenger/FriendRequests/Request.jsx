import React, { Component } from "react";

class Request extends Component {
  state = {};

  render() {
    return (
      <React.Fragment>
        <div className="chat_list">
          <div className="chat_people">
            <div className="chat_ib">
              <h5>
                {this.props.name}
                <span
                  className="chat_date"
                >
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
