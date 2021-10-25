import FieldSchema from "../models/field.js";

export const getFields = async (req, res) => {
  try {
    const fields = await FieldSchema.find();

    res.status(200).json(fields);
  } catch (err) {
    res.status(404).json({ message: error.message });
  }
};

export const addField = async (req, res) => {
  try {
    const field = new FieldSchema({
        id: req.body.id,
        name: req.body.name
    });

    const savedField = await field.save();
    res.status(200).json(savedField);
  } catch (err) {
    res.status(404).json({ message: error.message });
  }
};
