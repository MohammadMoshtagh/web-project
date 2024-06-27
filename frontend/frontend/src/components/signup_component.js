import React, { Component, useState } from "react";
import "../index.css";

export default function SignUp() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {

      fetch("http://localhost:8080/users/register", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
          username,
          password
        }),
      })
        .then((res) => res.json())
        .then((data) => {
          alert("Registration Successful. contact with admin for activation.");
        }).catch((error) => {
          alert("Try with another username");
        });;

  };

  return (
    <div className="auth-wrapper">
      <div className="auth-inner">
        <form onSubmit={handleSubmit}>
          <h3>Register</h3>

          <div className="mb-3">
            <label>Username</label>
            <input
              className="form-control"
              placeholder="Enter Username"
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>

          <div className="mb-3">
            <label>Password</label>
            <input
              type="password"
              className="form-control"
              placeholder="Enter password"
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          <div className="d-grid">
            <button type="submit" className="btn btn-primary">
              Register
            </button>
          </div>
          <p className="forgot-password text-right">
            Already registered <a href="/">Login?</a>
          </p>
        </form>
      </div>
    </div>
  );
}