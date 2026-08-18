import { useEffect, useState } from "react";
import api from "../services/api";

function BookAppointment() {

  const [services, setServices] = useState([]);
  const [employees, setEmployees] = useState([]);
  const [mappings, setMappings] = useState([]);

  const [selectedService, setSelectedService] = useState("");
  const [selectedEmployee, setSelectedEmployee] = useState("");

  const [date, setDate] = useState("");
  const [time, setTime] = useState("");

  const [loading, setLoading] = useState(true);
  const [booking, setBooking] = useState(false);

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  useEffect(() => {
    fetchBookingData();
  }, []);

  const fetchBookingData = async () => {

    try {

      const [
        servicesResponse,
        employeesResponse,
        mappingsResponse
      ] = await Promise.all([
        api.get("/services"),
        api.get("/employees"),
        api.get("/employee-service-mappings")
      ]);

      setServices(servicesResponse.data);
      setEmployees(employeesResponse.data);
      setMappings(mappingsResponse.data);

    } catch (error) {

      console.error("Error loading booking data:", error);

      setError("Unable to load booking information.");

    } finally {

      setLoading(false);

    }
  };


  const handleSubmit = async (event) => {

    event.preventDefault();

    setError("");
    setSuccess("");

    // Find the mapping for selected employee + selected service
    const mapping = mappings.find(
      (item) =>
        item.employeeId === Number(selectedEmployee) &&
        item.serviceId === Number(selectedService)
    );

    // No mapping means employee doesn't provide that service
    if (!mapping) {

      setError(
        "This employee does not provide the selected service."
      );

      return;
    }

    try {

      setBooking(true);

     const customerId = localStorage.getItem("customerId");

      if (!customerId) {

          setError("Please login before booking an appointment.");

          return;
      }

      const appointmentData = {
          customerId: Number(customerId),
          employeeServiceMappingId: mapping.id,
          appointmentDate: date,
          appointmentTime: time
      };

      console.log("Sending appointment:", appointmentData);

      const response = await api.post(
        "/appointments",
        appointmentData
      );

      console.log("Appointment response:", response.data);

      setSuccess("Appointment booked successfully!");

      // Clear form
      setSelectedService("");
      setSelectedEmployee("");
      setDate("");
      setTime("");

    } catch (error) {

      console.error("Booking error:", error);

      if (error.response) {

        setError(
          error.response.data?.message ||
          "Failed to book appointment."
        );

      } else {

        setError("Unable to connect to the server.");

      }

    } finally {

      setBooking(false);

    }
  };


  if (loading) {

    return (
      <div className="text-center mt-10">
        Loading booking information...
      </div>
    );

  }


  return (

    <div className="min-h-screen bg-gray-100 p-8">

      <div className="max-w-lg mx-auto bg-white p-8 rounded-lg shadow">

        <h2 className="text-3xl font-bold mb-6 text-center">
          Book Appointment
        </h2>


        {/* ERROR */}

        {error && (

          <div className="bg-red-100 text-red-700 p-3 rounded mb-4">
            {error}
          </div>

        )}


        {/* SUCCESS */}

        {success && (

          <div className="bg-green-100 text-green-700 p-3 rounded mb-4">
            {success}
          </div>

        )}


        <form onSubmit={handleSubmit}>


          {/* SERVICE */}

          <div className="mb-4">

            <label className="block mb-2 font-medium">
              Select Service
            </label>

            <select
              value={selectedService}
              onChange={(e) => setSelectedService(e.target.value)}
              className="w-full border rounded px-3 py-2"
              required
            >

              <option value="">
                -- Select Service --
              </option>

              {services.map((service) => (

                <option
                  key={service.id}
                  value={service.id}
                >
                  {service.serviceName} - ₹{service.price}
                </option>

              ))}

            </select>

          </div>


          {/* EMPLOYEE */}

          <div className="mb-4">

            <label className="block mb-2 font-medium">
              Select Employee
            </label>

            <select
              value={selectedEmployee}
              onChange={(e) => setSelectedEmployee(e.target.value)}
              className="w-full border rounded px-3 py-2"
              required
            >

              <option value="">
                -- Select Employee --
              </option>

              {employees.map((employee) => (

                <option
                  key={employee.id}
                  value={employee.id}
                >
                  {employee.name}
                </option>

              ))}

            </select>

          </div>


          {/* DATE */}

          <div className="mb-4">

            <label className="block mb-2 font-medium">
              Appointment Date
            </label>

            <input
              type="date"
              value={date}
              min={new Date().toISOString().split("T")[0]}
              onChange={(e) => setDate(e.target.value)}
              className="w-full border rounded px-3 py-2"
              required
            />

          </div>


          {/* TIME */}

          <div className="mb-6">

            <label className="block mb-2 font-medium">
              Appointment Time
            </label>

            <input
              type="time"
              value={time}
              onChange={(e) => setTime(e.target.value)}
              className="w-full border rounded px-3 py-2"
              required
            />

          </div>


          {/* BUTTON */}

          <button
            type="submit"
            disabled={booking}
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 disabled:bg-gray-400"
          >

            {booking ? "Booking..." : "Book Appointment"}

          </button>

        </form>

      </div>

    </div>

  );
}

export default BookAppointment;