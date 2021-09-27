import VaccinesSchema from "../models/vaccines.js";

export const getVaccines = async (req, res) => {
    try {
        const users = await VaccinesSchema.find();

        res.status(200).json(users);
    } catch (err) {
        res.status(404).json({ message: error.message });
    }
};
