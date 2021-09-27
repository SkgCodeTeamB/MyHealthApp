import AppointmentSchema from "../models/appointment.js";

export const getAppointments = async (req, res) => {
  try {
    const appointments = await AppointmentSchema.find();

    res.status(200).json(appointments);
  } catch (err) {
    res.status(404).json({ message: error.message });
  }
};

export const addAppointment = async (req, res) => {
  try {
    const appointment = new AppointmentSchema({
      user: req.body.user,
      doctor: req.body.doctor,
      date: req.body.date,
      time: req.body.time
    });

    const savedAppointment = await appointment.save();
    res.status(200).json(savedAppointment);
  } catch (err) {
    res.status(404).json({ message: error.message });
  }
};
