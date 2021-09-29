import UserSchema from "../models/user.js";

export const getUser = async (req, res) => {
  try {
    res.send("User page")
    const users = await UserSchema.find();

    res.status(200).json(users);
  } catch (err) {
    res.status(404).json({ message: error.message });
  }
};

export const postUser = async (req, res) => {
  try {
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
      postalcode: req.body.postalcode
    });

    const savedUser = await user.save();
    res.status(200).json(savedUser);
  } catch (err) {
    res.status(404).json({ message: error.message });
  }
};
