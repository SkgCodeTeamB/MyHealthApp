import mongoose from "mongoose";
const Schema = mongoose.Schema;

const appointmentSchema = new Schema({
  user: {
    type: Schema.Types.ObjectId,
    ref: "User"
  },
  doctor: {
    type: Schema.Types.ObjectId,
    ref: "Doctor"
  },
  date: {
    type: String,
    required: true,
    unique: true
  },
  time: {
    type: String,
    required: true,
    unique: true
  }
});

const Appointment = mongoose.model("Appointment", appointmentSchema);

export default Appointment;
