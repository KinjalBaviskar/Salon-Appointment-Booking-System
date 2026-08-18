import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080"
});

api.interceptors.request.use(
  (config) => {

    const username = localStorage.getItem("authUsername");
    const password = localStorage.getItem("authPassword");

    if (username && password) {

      config.auth = {
        username: username,
        password: password
      };

    }

    return config;
  },

  (error) => {
    return Promise.reject(error);
  }
);

export default api;