function Register() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">

      <div className="bg-white p-8 rounded-lg shadow-md w-96">

        <h2 className="text-3xl font-bold text-center mb-6">
          Register
        </h2>

        <form>

          <div className="mb-4">
            <label className="block mb-2">
              Name
            </label>

            <input
              type="text"
              className="w-full border rounded px-3 py-2"
              placeholder="Enter name"
            />
          </div>

          <div className="mb-4">
            <label className="block mb-2">
              Username
            </label>

            <input
              type="text"
              className="w-full border rounded px-3 py-2"
              placeholder="Enter username"
            />
          </div>

          <div className="mb-4">
            <label className="block mb-2">
              Password
            </label>

            <input
              type="password"
              className="w-full border rounded px-3 py-2"
              placeholder="Enter password"
            />
          </div>

          <button
            type="submit"
            className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700"
          >
            Register
          </button>

        </form>

      </div>

    </div>
  );
}

export default Register;