import React, { Component, useState } from "react";
import SearchItem from "./SearchItem";

import "../Messenger.css";

class Search extends Component {
  state = {
    searchList: []
  };

  componentDidMount() {
      const searchList = this.props.searchList;
      this.setState({searchList});
  }

  handleAddFriend = id => {
    console.log(id);
    console.log(this.props.user);
  };

  returnFriendComponents() {
    return (
      <React.Fragment>
        {this.state.searchList.map(search => (
          <SearchItem
            key={search.id}
            onAddFriend={this.handleAddFriend}
            name={search.name}
            isFriend={search.isFriend}
            id={search.id}
          />
        ))}
      </React.Fragment>
    );
  }

  render() {
    return <React.Fragment>{this.returnFriendComponents()}</React.Fragment>;
  }
}

export default Search;
