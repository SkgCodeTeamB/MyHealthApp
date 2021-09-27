import PrescriptionsSchema from "../models/prescriptions.js";

export const getPrescriptions = async (req, res) => {
    try {
        const users = await PrescriptionsSchema.find();

        res.status(200).json(users);
    } catch (err) {
        res.status(404).json({ message: error.message });
    }
};