function MyAppointments() {

  const appointments = [
    {
      id: 1,
      bookingId: "BK001",
      service: "Haircut",
      date: "2026-08-20",
      time: "10:00",
      status: "Booked"
    },
    {
      id: 2,
      bookingId: "BK002",
      service: "Facial",
      date: "2026-08-25",
      time: "15:00",
      status: "Completed"
    }
  ];

  return (
    <div className="p-8">

      <h2 className="text-3xl font-bold mb-8">
        My Appointments
      </h2>

      <div className="overflow-x-auto">

        <table className="w-full bg-white shadow rounded">

          <thead>
            <tr className="bg-gray-200">

              <th className="p-3 text-left">
                Booking ID
              </th>

              <th className="p-3 text-left">
                Service
              </th>

              <th className="p-3 text-left">
                Date
              </th>

              <th className="p-3 text-left">
                Time
              </th>

              <th className="p-3 text-left">
                Status
              </th>

            </tr>
          </thead>

          <tbody>

            {appointments.map((appointment) => (

              <tr key={appointment.id} className="border-t">

                <td className="p-3">
                  {appointment.bookingId}
                </td>

                <td className="p-3">
                  {appointment.service}
                </td>

                <td className="p-3">
                  {appointment.date}
                </td>

                <td className="p-3">
                  {appointment.time}
                </td>

                <td className="p-3">
                  {appointment.status}
                </td>

              </tr>

            ))}

          </tbody>

        </table>

      </div>

    </div>
  );
}

export default MyAppointments;