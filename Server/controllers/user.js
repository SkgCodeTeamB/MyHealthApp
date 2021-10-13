import UserSchema from "../models/user.js";
import { registerValidation, loginValidation } from "../middleware/index.js";
import User from "../models/user.js";
import jwt from "jsonwebtoken";

export const loginUser = async (req, res) => {
  //Validate Data BEFORE loging user
  const { error } = loginValidation(req.body);
  if (error) return res.status(400).send(error.details[0].message);

  //Check if Users Exists in DB
  const validAmka = await User.findOne({ amka: req.body.amka });
  if (!validAmka) return res.status(400).send("Amka does not exist");

  //Create and assign Token
  const token = jwt.sign({ _id: validAmka._id }, process.env.TOKEN_SECRET);
  res.header("auth-token", token).send(token);
};

export const addUser = async (req, res) => {
  //Validate Data BEFORE registering user
  const { error } = registerValidation(req.body);
  if (error) return res.status(400).send(error.details[0].message);

  //Check if user already exists
  const emailExist = await User.findOne({ email: req.body.email });
  const phoneExist = await User.findOne({ phone: req.body.phone });
  const amkaExist = await User.findOne({ amka: req.body.amka });

  if (emailExist) return res.status(400).send("Email Already Exists");
  if (phoneExist) return res.status(400).send("Phone Already Exists");
  if (amkaExist) return res.status(400).send("Amka Already Exists");

  const user = new UserSchema({
    name: req.body.name,
    surname: req.body.surname,
    email: req.body.email,
    phone: req.body.phone,
    birthday: req.body.birthday,
    bloodtype: req.body.bloodtype,
    amka: req.body.amka,
    familydoctor: req.body.familydoctor,
    address: req.body.address,
    city: req.body.city,
    postalcode: req.body.postalcode,
  });
  try {
    const savedUser = await user.save();
    res.status(200).json(savedUser);
  } catch (err) {
    res.status(404).json({ message: err.message });
  }
};

//send all the information of given user' amka (req.body.amka)
export const getInfo = async (req, res) => {
  try {
    const users = await UserSchema.find({ _id: req.params.id }).populate(
      "familydoctor"
    );

    res.status(200).json(users);
  } catch (err) {
    res.status(404).json({ message: err.message });
  }
};

export const deleteUser = async (req, res) => {
  try {
    const deleteUser = await UserSchema.deleteOne({ _id: req.params.id });

    res.status(200).json(deleteUser);
  } catch (err) {
    res.status(404).json({ message: err.message });
  }
};

export const updateUser = async (req, res) => {
  try {
    const updateUser = await UserSchema.updateOne(
      { amka: req.body.amka },
      {
        $set: {
          name: req.body.name,
          surname: req.body.surname,
          email: req.body.email,
          phone: req.body.phone,
          birthday: req.body.birthday,
          bloodtype: req.body.bloodtype,
          amka: req.body.amka,
          address: req.body.address,
          city: req.body.city,
          postacode: req.body.postalcode,
        },
      }
    );

    res.status(200).json(updateUser);
  } catch (err) {
    res.status(404).json({ message: err.message });
  }
};
