import PrescriptionsSchema from "../models/prescriptions.js";
import UserSchema from "../models/user.js";

//Returns all the Prescriptions from the database
export const getPrescriptions = async (req, res) => {
    try {
        const prescriptions = await PrescriptionsSchema.find().populate('user').populate('doctor');

        res.status(200).json(prescriptions);
    } catch (err) {
        res.status(404).json({message: err.message});
    }
};

// Adds a prescription in the database
export const addPrescription = async (req, res) => {
    try {
        const prescription = new PrescriptionsSchema({

            date: req.body.date,
            text: req.body.text,
            user: req.body.user,
            doctor: req.body.doctor
        });

        const savedPrescription = await prescription.save();
        res.status(200).json(savedPrescription);
    } catch (err) {
        res.status(404).json({message: err.message});
    }
};

// Returns all the users prescriptions
export const getUsersPrescriptions = async (req, res) => {
    try {
        const prescriptions = await PrescriptionsSchema.find({user: await UserSchema.find({_id: req.params.id})}).populate('user').populate('doctor');

        res.status(200).json(prescriptions);
    } catch (err) {
        res.status(404).json({message: err.message});
    }
};

// Returns the users prescription count
export const getUsersPrescriptionsCount = async (req, res) => {
    try {
        PrescriptionsSchema.find({user: await UserSchema.find({_id: req.params.id})}).count({}, function (err, count) {
            res.status(200).json(JSON.stringify(count));
        });
    } catch (err) {
        res.status(404).json({message: err.message});
    }
};

// Deletes a specific prescription
// export const deletePrescription = async (req, res) => {
//     try {
//         const deletedPrescription = await PrescriptionsSchema.deleteOne({_id: req.params.id});
//
//         res.status(200).json(deletedPrescription);
//     } catch (err) {
//         res.status(404).json({message: err.message});
//     }
// };

// Updates a specific Prescription
// export const updatePrescription = async (req, res) => {
//     try {
//         const updatePrescription = await PrescriptionsSchema.updateOne({_id: req.body.id}, {
//             $set: {
//                 date: req.body.date,
//                 text: req.body.text,
//                 user: req.body.user,
//                 doctor: req.body.doctor
//             }
//         });
//
//         res.status(200).json(updatePrescription);
//     } catch (err) {
//         res.status(404).json({message: err.message});
//     }
// };