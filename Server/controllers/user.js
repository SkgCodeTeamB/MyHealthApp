import UserSchema from "../models/user.js";

export const getUser = async (req, res) => {
  try {
    const users = await UserSchema.find();

    res.status(200).json(users);
  } catch (err) {
    res.status(404).json({ message: error.message });
  }
};
