import React, { Component } from 'react';
import Axios from 'axios';

import "./Login.css"

class Login extends Component {
  handleLogin = () => {
    let postdata = new URLSearchParams();

    const postURL = "http://localhost:8080/createUser";
    const username = document.getElementById("username").value;

    Axios.post(postURL,{
      user_name: username
    }).then(res => {
      console.log(res);
      console.log(res.data);
    })
    console.log(username);
  }

  render() { 
    return (
      <header className="App-header">
        <div className="form">
          <div className="box">
            <h2> Pigeon Messenger </h2>
          </div>

          <div
          >
            <input id = "username" field="" type="text" />
            <button onClick ={this.handleLogin}  value="Submit">
              {" "}
              Submit{" "}
            </button>
          </div>
        </div> 
      </header>
  );
  }
}
 
export default Login;
