import React, { useEffect, useState } from "react";
import "./test.css";

function Test() {
  const [data, setData] = useState(null);
  useEffect(() => {
    console.log("Hey, I've loaded up ");
    console.log(data);
    if (!data) {
      setData([JSON.parse(sessionStorage.getItem("mydata"))]);
      console.log("reponse", data);
    }
  }, [data]);

  return (
    <div className="toto">
      {data !== null
        ? data.map((DT) => {
            return <div>lotfi</div>;
          })
        : "loading data..."}
    </div>
  );
}

export default Test;
