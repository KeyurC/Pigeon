import React, { Component } from "react";
import Friends from "./Friends/Friends";
import FriendSearch from "./Search/SearchBar";
import Requests from "./FriendRequests/Requests";
import Search from "./Search/Search";
import Axios from 'axios';

import "./Messenger.css";

class FriendHub extends Component {
  state = {
    friendOpen: true,
    requestOpen: true,
    searchList: []
  };


  updateComponents = () => {
    this.props.onUpdate();
  }

  isSearching() {
    if (this.state.searchList.length === 0) {
      return (
        <React.Fragment>
          <div className="headind_srch2">
            <p onClick={this.requestDivToggle}>Requests </p>
          </div>
          <div className="inbox_chat">
            <Requests
              updateComponents={this.updateComponents}
              onRequests={this.props.onRequests}
              onFriends={this.props.getFriends}
              requests={this.props.requests}
              requestOpen={this.state.requestOpen}
              user={this.props.user}
            />
          </div>
          <div className="headind_srch2">
            <p onClick={this.friendDivToggle}>Friends </p>
          </div>
          <div className="inbox_chat">
            <Friends
              friendsList={this.props.friendsList}
              user={this.props.user}
              friendOpen={this.state.friendOpen}
              reloadFriends={this.props.getFriends}
            />
          </div>
        </React.Fragment>
      );
    } else {
      return (
        <Search 
        searchList={this.state.searchList} 
        user={this.props.user}
        revert={this.revertState} />
      );
    }
  }

  requestDivToggle = () => {
    if (this.state.requestOpen) {
      this.setState({ requestOpen: false });
    } else {
      this.setState({ requestOpen: true });
    }
  };

  friendDivToggle = () => {
    if (this.state.friendOpen) {
      this.setState({ friendOpen: false });
    } else {
      this.setState({ friendOpen: true });
    }
  };

  updateSearch = searchList => {
    if (this.state.searchList.length > 0) {
      this.setState({
        searchList: []
      });
    }
    this.setState({ searchList });
  };

  revertState = () => {
    this.setState({
      searchList: []
    });
  };


  render() {
    return (
      <React.Fragment>
        <div className="messaging">
          <div className="inbox_msg">
            <div className="inbox_people">
              <div className="headind_srch">
                <div className="input-group mb-3">
                  <FriendSearch
                    revert={this.revertState}
                    onSearch={this.updateSearch}
                    user={this.props.user}
                  />
                </div>
              </div>
              <React.Fragment>{this.isSearching()}</React.Fragment>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default FriendHub;
