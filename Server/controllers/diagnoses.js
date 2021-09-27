import DiagnoseSchema from "../models/diagnoses.js";

export const getDiagnoses = async (req, res) => {
    try {
        const diagnose = await DiagnoseSchema.find();

        res.status(200).json(diagnose);
    } catch (err) {
        res.status(404).json({ message: error.message });
    }
};