import React, { Fragment, useEffect, useState } from "react";

function CollectData() {
  const [data, setData] = useState(null);

  useEffect(() => {
    //do somthing on load
    console.log("Hey, I've loaded up");
    if (!data) {
      fetch(
        "http://localhost:8080/ProjetJEE-1.0-SNAPSHOT/api/users/user/1/?user-type=student"
      )
        .then((response) => response.json())
        .then((items) => {
          console.log("Resultat", items);
          setData([items]);
        });
    }
  }, [data]);

  return (
    <div>
      {data
        ? data.map((toto) => {
            return (
              <Fragment key={toto.id}>
                <div>Email : {toto.email}</div>
                <div>Password : {toto.password}</div>
                <div>Adress : {toto.address}</div>
                <div>Country : {toto.country}</div>
              </Fragment>
            );
          })
        : "Loading data..."}
    </div>
  );
}

export default CollectData;
