import axios from "axios";

export default axios.create({
  baseURL: "http://192.168.31.65/user",
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
});
