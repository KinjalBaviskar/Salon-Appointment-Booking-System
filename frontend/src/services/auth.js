export const saveCredentials = (username, password) => {
  localStorage.setItem("username", username);
  localStorage.setItem("password", password);
};

export const getCredentials = () => {
  return {
    username: localStorage.getItem("username"),
    password: localStorage.getItem("password"),
  };
};

export const logout = () => {
  localStorage.removeItem("username");
  localStorage.removeItem("password");
};

export const isLoggedIn = () => {
  return !!localStorage.getItem("username");
};