import VaccinationSchema from "../models/vaccination.js";

export const getVaccinations = async (req, res) => {
    try {
        const vaccinations = await VaccinationSchema.find().populate('user').populate('vaccine');

        res.status(200).json(vaccinations);
    } catch (err) {
        res.status(404).json({ message: err.message });
    }
};

export const addVaccination = async (req, res) => {
    try {
        const vaccination = new VaccinationSchema({
            date: req.body.date,
            user: req.body.user,
            vaccine: req.body.vaccine
        });

        const savedVaccination = await vaccination.save();
        res.status(200).json(savedVaccination);
    } catch (err) {
        res.status(404).json({ message: err.message });
    }
};

export const doneVaccinations = async (req, res) => {
    try {
        const dv = await VaccinationSchema.find({ user: req.body.user }).populate('vaccine');

        res.status(200).json(dv);
    } catch (err) {
        res.status(404).json({ message: err.message });
    }
};
