import VaccinationsSchema from "../models/vaccinations.js";

export const getVaccinations = async (req, res) => {
    try {
        const users = await VaccinationsSchema.find().populate('user').populate('vaccine');

        res.status(200).json(users);
    } catch (err) {
        res.status(404).json({ message: error.message });
    }
};