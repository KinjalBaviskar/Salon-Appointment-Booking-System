import { BrowserRouter, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";

import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Services from "./pages/Services";
import BookAppointment from "./pages/BookAppointment";
import MyAppointments from "./pages/MyAppointments";

function App() {
  return (
    <BrowserRouter>

      <Navbar />

      <Routes>

        <Route path="/" element={<Home />} />

        <Route path="/login" element={<Login />} />

        <Route path="/register" element={<Register />} />

        <Route path="/services" element={<Services />} />

        <Route
          path="/book-appointment"
          element={<BookAppointment />}
        />

        <Route
          path="/my-appointments"
          element={<MyAppointments />}
        />

      </Routes>

    </BrowserRouter>
  );
}

export default App;