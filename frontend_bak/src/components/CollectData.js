import React, { useEffect, useState } from "react";

function CollectData() {
  const [collections, settata] = useState(null);

  useEffect(() => {
    console.log("Hey, I've loaded up");
    if (!collections) {
      fetch(
        "http://localhost:8080/ProjetJEE-1.0-SNAPSHOT/api/users/user/3/?user-type=student"
      ).then((response) => {
        response = response.json();
        response.then((data) => {
          settata([data]);

          console.log("my first experience", [data]);
        });
      });
    }
  }, [collections]);

  return (
    <div>
      <h1>c'est ma premiere version</h1>
      <div>
        {collections
          ? collections.map((collection) => {
              return <div key={collection.email}>{collection.email}</div>;
            })
          : "Loading..."}
      </div>
    </div>
  );
}

export default CollectData;
