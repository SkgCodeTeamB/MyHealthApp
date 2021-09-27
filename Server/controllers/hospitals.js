import HospitalsSchema from "../models/hospitals.js";

export const getHospitals = async (req, res) => {
    try {
        const hospital = await HospitalsSchema.find();
        console.log(req.body)



        res.status(200).json(hospital);
    } catch (err) {
        res.status(404).json({ message: error.message });
    }
};