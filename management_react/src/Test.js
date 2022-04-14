import React, { useEffect, useState } from "react";
import Button from "@mui/material/Button";
import axios from "axios";

function Test() {
  const [data, setData] = useState(null);

  const loadData = () => {
    try {
      axios.get("http://localhost:8080/teacher/all").then((res) => {
        console.log(res);
        setData("res");
      });
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <p>{data == null ? "Nothing to show" : data}</p>
      <Button variant="contained" onClick={loadData}>
        GetData
      </Button>
    </>
  );
}

export default Test;
