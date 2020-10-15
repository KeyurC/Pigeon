import React, { Component } from "react";
import SearchItem from "./SearchItem";
import Axios from 'axios';

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
    //this.props.revert();
    Axios.post("http://localhost:8080/addFriend", {
      params: {
        username: this.props.user,
        friendID: id
      }
    }).then(
      res => {},
      err => {
        console.log(JSON.stringify(err));
      }
    );
    }
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
