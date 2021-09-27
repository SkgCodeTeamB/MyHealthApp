import mongoose from "mongoose";

const doctorSchema = mongoose.Schema({
  id: {
    type: String,
    required: true,
    unique: true
  },
  name: {
    type: String,
    required: true
  },
  surname: {
    type: String,
    required: true
  },
  phone: {
    type: String,
    required: true,
    unique: true
  },
  field: {
    type: Schema.Types.ObjectId,
    ref: "Field"
  }
});

const Doctor = mongoose.model("Doctor", doctorSchema);

export default Doctor;
