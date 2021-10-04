import AppointmentSchema from "../models/appointment.js";
import UserSchema from "../models/user.js";

export const getAppointments = async (req, res) => {
  try {
    const appointments = await AppointmentSchema.find().populate('user').populate('doctor');

    res.status(200).json(appointments);
  } catch (err) {
    res.status(404).json({ message: err.message });
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
    res.status(404).json({ message: err.message });
  }
};

//return booked hour slots of given doctor and given date
export const getBookedSlots = async (req, res) => {
  try {
    const appointments = await AppointmentSchema.find({ doctor: req.body.doctor, date: req.body.date }, 'time');

    res.status(200).json(appointments);
  } catch (err) {
    res.status(404).json({ message: err.message });
  }
};

export const getUsersAppointments = async (req, res) => {
  try {
    const appointments = await AppointmentSchema.find({ user: await UserSchema.find({ amka: req.params.amka }) }).populate('doctor');

    res.status(200).json(appointments);
  } catch (err) {
    res.status(404).json({ message: err.message });
  }
};

export const getUsersAppointmentsCount = async (req, res) => {
  try {
    AppointmentSchema.find({ user: await UserSchema.find({ amka: req.params.amka }) }).count({}, function(err, count){
      res.status(200).json(JSON.stringify(count));
    });
  } catch (err) {
    res.status(404).json({ message: err.message });
  }
};

export const deleteAppointment = async (req, res) => {
  try {
    const deletedAppointment = await AppointmentSchema.deleteOne({ _id: req.params.id });

    res.status(200).json(deletedAppointment);
  } catch (err) {
    res.status(404).json({ message: err.message });
  }
};

export const updateAppointment = async (req, res) => {
  try {
    const updatedAppointment = await AppointmentSchema.updateOne({ _id: req.body.id }, { $set: { doctor: req.body.doctor, date: req.body.date, time: req.body.time } });

    res.status(200).json(updatedAppointment);
  } catch (err) {
    res.status(404).json({ message: err.message });
  }
};