import React, { Component } from "react";
import Axios from "axios";

class FriendSearch extends Component {
  state = {};

  handleFriendsSearch = () => {
    let friend = document.getElementById("search").value;
    if (friend === "") {
      this.props.revert();
    } else {
      Axios.get("http://localhost:8080/searchUsers", {
        params: {
          username: this.props.user,
          friend: friend
        }
      }).then(
        res => {
          let searchList = [];

          for (let i = 0; i < res.data.length; i++) {
            let searchResult = res.data[i];
            searchList.push(JSON.parse(searchResult));
          }
          this.props.onSearch(searchList);
        },
        err => {
          console.log(JSON.stringify(err));
        }
      );
    }
  };

  render() {
    return (
      <React.Fragment>
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
      </React.Fragment>
    );
  }
}

export default FriendSearch;
