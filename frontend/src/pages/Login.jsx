import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

function Login() {

  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleLogin = async (event) => {

    event.preventDefault();

    setError("");

    try {

      setLoading(true);

      const response = await api.post(
        "/customers/login",
        {
          username: username,
          password: password
        }
      );

      console.log("Login response:", response.data);

      // Save customer information
      localStorage.setItem(
        "customerId",
        response.data.customerId
      );

      localStorage.setItem(
        "username",
        response.data.username
      );

      localStorage.setItem(
        "role",
        response.data.role
      );

      // Save credentials for HTTP Basic
      localStorage.setItem(
        "authUsername",
        username
      );

      localStorage.setItem(
        "authPassword",
        password
      );

      alert("Login successful!");

      navigate("/services");

    } catch (error) {

      console.error("Login error:", error);

      if (error.response) {

        setError(
          error.response.data?.message ||
          "Invalid username or password"
        );

      } else {

        setError("Unable to connect to server.");

      }

    } finally {

      setLoading(false);

    }
  };


  return (

    <div className="min-h-screen flex items-center justify-center bg-gray-100">

      <div className="bg-white p-8 rounded-lg shadow-md w-96">

        <h2 className="text-3xl font-bold text-center mb-6">
          Login
        </h2>


        {error && (
          <div className="bg-red-100 text-red-700 p-3 rounded mb-4">
            {error}
          </div>
        )}


        <form onSubmit={handleLogin}>

          <div className="mb-4">

            <label className="block mb-2">
              Username
            </label>

            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="w-full border rounded px-3 py-2"
              placeholder="Enter username"
              required
            />

          </div>


          <div className="mb-4">

            <label className="block mb-2">
              Password
            </label>

            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full border rounded px-3 py-2"
              placeholder="Enter password"
              required
            />

          </div>


          <button
            type="submit"
            disabled={loading}
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 disabled:bg-gray-400"
          >

            {loading ? "Logging in..." : "Login"}

          </button>

        </form>

      </div>

    </div>
  );
}

export default Login;