import UserSchema from "../models/user.js";

export const getUsers = async (req, res) => {
    try {
        const users = await UserSchema.find();

        res.status(200).json(users);
    } catch (err) {
        res.status(404).json({message: err.message});
    }
};

export const addUser = async (req, res) => {
    try {
        const user = new UserSchema({
            name: req.body.name,
            amka: req.body.amka
        });

        const savedUser = await user.save();
        res.status(200).json(savedUser);
    } catch (err) {
        res.status(404).json({message: err.message});
    }
};

//send all the information of given user' amka (req.body.amka)
export const getInfo = async (req, res) => {
    try {
        const users = await UserSchema.find({amka: req.params.amka});

        res.status(200).json(users);
    } catch (err) {
        res.status(404).json({message: err.message});
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
        const updateUser = await UserSchema.updateOne({  amka: req.body.amka }, { $set: { name: req.body.name, surname: req.body.surname, email: req.body.email, phone: req.body.phone, birthday: req.body.birthday, bloodtype: req.body.bloodtype , amka: req.body.amka, familydoctor: req.body.familydoctor, address: req.body.address, city: req.body.city, postacode: req.body.postalcode } });

        res.status(200).json(updateUser);
    } catch (err) {
        res.status(404).json({ message: err.message });
    }
};