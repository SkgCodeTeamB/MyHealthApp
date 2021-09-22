import mongoose from "mongoose";

const userSchema = mongoose.Schema({
  name: String,
});

const UserSchema = mongoose.model("UserSchema", userSchema);

export default UserSchema;
