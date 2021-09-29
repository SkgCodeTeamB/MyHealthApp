import VaccinesSchema from "../models/vaccines.js";

export const getVaccines = async (req, res) => {
    try {
        const users = await VaccinesSchema.find();

        res.status(200).json(users);
    } catch (err) {
        res.status(404).json({ message: error.message });
    }
};

export const addVaccine = async (req, res) => {
    try {
      const vaccine = new VaccinesSchema({
            name: req.body.name,
            id: req.body.id
      });
  
      const savedVaccine = await vaccine.save();
      res.status(200).json(savedVaccine);
    } catch (err) {
      res.status(404).json({ message: error.message });
    }
  };