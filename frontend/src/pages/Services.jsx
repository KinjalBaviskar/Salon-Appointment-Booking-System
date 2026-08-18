import { useEffect, useState } from "react";
import api from "../services/api";
import { Link } from "react-router-dom";
function Services() {
  const [services, setServices] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchServices();
  }, []);

  const fetchServices = async () => {
    try {
      const response = await api.get("/services");
      setServices(response.data);
    } catch (error) {
      console.error(error);
      setError("Failed to load services.");
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="text-center mt-10">
        Loading services...
      </div>
    );
  }

  if (error) {
    return (
      <div className="text-center mt-10 text-red-600">
        {error}
      </div>
    );
  }

  return (
    <div className="p-8">

      <h2 className="text-3xl font-bold text-center mb-8">
        Our Services
      </h2>

      {services.length === 0 ? (
        <p className="text-center">
          No services available.
        </p>
      ) : (
        <div className="grid md:grid-cols-3 gap-6">

          {services.map((service) => (
  <div
    key={service.id}
    className="bg-white p-6 rounded-lg shadow-md"
  >

    <h3 className="text-xl font-bold mb-2">
      {service.serviceName}
    </h3>

    <p className="text-gray-600 mb-3">
      {service.description}
    </p>

    <p>
      Duration: {service.duration} minutes
    </p>

    <p className="font-bold mt-2">
      ₹{service.price}
    </p>

    <Link
      to="/book-appointment"
      className="block text-center mt-4 bg-blue-600 text-white py-2 rounded hover:bg-blue-700"
    >
      Book Now
    </Link>

  </div>
))}

        </div>
      )}

    </div>
  );
}

export default Services;