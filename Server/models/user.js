import mongoose from "mongoose";
const Schema = mongoose.Schema;

const bloodTypesArray = ["O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"];

const userSchema = new Schema({
  name: {
    type: String,
    required: true,
  },
  surname: {
    type: String,
    required: true,
  },
  email: {
    type: String,
    required: true,
    unique: true,
  },
  phone: {
    type: String,
    required: true,
    unique: true,
  },
  birthday: {
    type: String,
    required: true,
  },
  bloodtype: {
    type: String,
    uppercase: true,
    required: true,
    enum: bloodTypesArray,
  },
  amka: {
    type: String,
    required: true,
    unique: true,
  },
  familydoctor: {
    type: String,
  },
  address: {
    type: String,
  },
  city: {
    type: String,
  },
  postalcode: {
    type: String,
  },
});

const User = mongoose.model("User", userSchema);

export default User;
