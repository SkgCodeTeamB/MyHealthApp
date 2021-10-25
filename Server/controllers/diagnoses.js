import DiagnoseSchema from "../models/diagnoses.js";
import UserSchema from "../models/user.js";

//Returns all the diagnoses from the database
export const getDiagnoses = async (req, res) => {
    try {
        const diagnose = await DiagnoseSchema.find().populate('user').populate('doctor');

        res.status(200).json(diagnose);
    } catch (err) {
        res.status(404).json({message: err.message});
    }
};

// Adds a diagnose in the database
export const addDiagnose = async (req, res) => {
    try {
        const diagnose = new DiagnoseSchema({
            date: req.body.date,
            text: req.body.text,
            user: req.body.user,
            doctor: req.body.doctor
        });

        const savedDiagnose = await diagnose.save();
        res.status(200).json(savedDiagnose);
    } catch (err) {
        res.status(404).json({message: err.message});
    }
};

// Returns all the users diagnoses
export const getUsersDiagnoses = async (req, res) => {
    try {
        const diagnoses = await DiagnoseSchema.find({user: await UserSchema.find({_id: req.params.id})}).populate('doctor');

        res.status(200).json(diagnoses);
    } catch (err) {
        res.status(404).json({message: err.message});
    }
};

// Returns the users diagnoses count
export const getUsersDiagnosesCount = async (req, res) => {
    try {
        DiagnoseSchema.find({user: await UserSchema.find({_id: req.params.id})}).count({}, function (err, count) {
            res.status(200).json(JSON.stringify(count));
        });
    } catch (err) {
        res.status(404).json({message: err.message});
    }
};

// Deletes a specific diagnose
// export const deleteDiagnose = async (req, res) => {
//     try {
//         const deletedDiagnose = await DiagnoseSchema.deleteOne({_id: req.params.id});
//
//         res.status(200).json(deletedDiagnose);
//     } catch (err) {
//         res.status(404).json({message: err.message});
//     }
// };

// Updates a specific diagnose
// export const updateDiagnose = async (req, res) => {
//     try {
//         const updateDiagnose = await DiagnoseSchema.updateOne({_id: req.body.id}, {
//             $set: {
//                 date: req.body.date,
//                 text: req.body.text,
//                 user: req.body.user,
//                 doctor: req.body.doctor
//             }
//         });
//
//         res.status(200).json(updateDiagnose);
//     } catch (err) {
//         res.status(404).json({message: err.message});
//     }
// };