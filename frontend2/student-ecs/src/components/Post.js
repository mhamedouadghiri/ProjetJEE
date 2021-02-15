import { Avatar } from "@material-ui/core";
import React from "react";
import "./Post.css";
import EcoIcon from "@material-ui/icons/Eco";
import InputOption from "./InputOption";
function Post({ key, name, email, description, message }) {
  return (
    <div className="post">
      <div className="post_header">
        <Avatar />
        <div className="post_info">
          <h2>{name}</h2>
          <p>{email}</p>
        </div>
      </div>
      <div className="post_body">
        <p>{message}</p>
        <p className="description">{description}</p>
      </div>
      <div className="post_buttons">
        <InputOption Icon={EcoIcon} title="Envoyer" color="gray" />
      </div>
    </div>
  );
}

export default Post;
