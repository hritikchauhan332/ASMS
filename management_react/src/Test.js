import React, { useEffect, useState } from "react";
import Button from "@mui/material/Button";
import axiosInstance from "./utils/axios";

function Test() {
  const [data, setData] = useState(null);

  axiosInstance
    .get("/teacher/all")
    .then((res) => {
      console.log(res);
    })
    .catch((err) => {
      console.log(err);
    });

  return (
    <>
      <p>{data == null ? "Nothing to show" : data}</p>
      <Button variant="contained">GetData</Button>
    </>
  );
}

export default Test;
