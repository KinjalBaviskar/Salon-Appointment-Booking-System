import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="bg-blue-600 text-white px-6 py-4 flex justify-between items-center">

      <Link to="/" className="text-2xl font-bold">
        Salon Management
      </Link>

      <div className="flex gap-6">

        <Link to="/">
          Home
        </Link>

        <Link to="/services">
          Services
        </Link>

        <Link to="/book-appointment">
          Book Appointment
        </Link>

        <Link to="/my-appointments">
          My Appointments
        </Link>

        <Link to="/login">
          Login
        </Link>

        <Link to="/register">
          Register
        </Link>

      </div>

    </nav>
  );
}

export default Navbar;