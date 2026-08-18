import { Link } from "react-router-dom";

function Home() {
  return (
    <div className="min-h-screen bg-gray-100">

      <section className="text-center py-20 px-6">

        <h1 className="text-5xl font-bold mb-6">
          Welcome to Salon Management System
        </h1>

        <p className="text-gray-600 text-lg mb-8">
          Book your salon appointments easily and manage your services.
        </p>

        <div className="flex justify-center gap-4">

          <Link
            to="/services"
            className="bg-blue-600 text-white px-6 py-3 rounded hover:bg-blue-700"
          >
            View Services
          </Link>

          <Link
            to="/login"
            className="bg-gray-800 text-white px-6 py-3 rounded hover:bg-gray-900"
          >
            Login
          </Link>

        </div>

      </section>

    </div>
  );
}

export default Home;