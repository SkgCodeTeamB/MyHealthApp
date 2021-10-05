import DoctorSchema from "../models/doctor.js";
import FieldSchema from "../models/field.js";

export const getDoctors = async (req, res) => {
  try {
    const doctors = await DoctorSchema.find().populate('field');

    res.status(200).json(doctors);
  } catch (err) {
    res.status(404).json({ message: err.message });
  }
};

export const addDoctor = async (req, res) => {
  try {
    const doctor = new DoctorSchema({
        id: req.body.id,
        name: req.body.name,
        surname: req.body.surname,
        phone: req.body.phone,
        field: req.body.field
    });

    const savedDoctor = await doctor.save();
    res.status(200).json(savedDoctor);
  } catch (err) {
    res.status(404).json({ message: err.message });
  }
};

export const getDoctorsOfField = async (req, res) => {
  try {
    const doctorsOfField = await DoctorSchema.find({ field: await FieldSchema.find({ name: req.params.field }) });

    res.status(200).json(doctorsOfField);
  } catch (err) {
    res.status(404).json({ message: err.message });
  }
};