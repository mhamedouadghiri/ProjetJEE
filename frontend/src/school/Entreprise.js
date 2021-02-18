import React, { useEffect, useState } from "react";
import Post from "../student/Post";
import "./Entreprise.css";
import { properties } from "../resources/properties";
function Entreprise() {
  const [posts, setPosts] = useState(null);
  useEffect(() => {
    if (!posts) {
      fetch(`${properties.url}${properties.company}`)
        .then((res) => res.json())
        .then((data) => {
          setPosts(data);
          console.log(data);
        });
    }
  }, [posts]);
  return (
    <div className="entrepriseS">
      {posts
        ? posts.map((post) => (
            <Post
              key={post.id}
              name={post.name}
              email={post.email}
              description={"L'offre de l'entreprise qui va être afficher ici "}
              message="Offre de Stage"
            />
          ))
        : "Loading data.."}
    </div>
  );
}

export default Entreprise;
