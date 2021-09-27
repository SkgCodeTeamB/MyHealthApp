import mongoose from "mongoose";

const bloodTypesArray = ["O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"];

const userSchema = mongoose.Schema({
  name: {
    type: String,
    required: true
  },
  surname: {
    type: String,
    required: true
  },
  email: {
    type: String,
    required: true,
    unique: true
  },
  phone: {
    type: String,
    required: true,
    unique: true
  },
  birthday: {
    type: Date,
    required: true
  },
  bloodtype: {
    type: String,
    uppercase: true,
    required: true,
    enum: bloodTypesArray
  },
  amka: {
    type: String,
    required: true,
    unique: true
  },
  familydoctor: {
    type: Schema.Types.ObjectId,
    ref: "Doctor"
  },
  address: {
    type: String
  },
  city: {
    type: String
  },
  postalcode: {
    type: String
  }
});

const User = mongoose.model("User", userSchema);

export default User;
