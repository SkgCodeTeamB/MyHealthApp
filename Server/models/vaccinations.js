import mongoose from "mongoose";
const Schema = mongoose.Schema;

const vaccinationsSchema = mongoose.Schema({
    date: {
        type: Date,
        required: false
    },
    user: {
        type: Schema.Types.ObjectId,
        ref: "User"
    },
    vaccine: {
        type: Schema.Types.ObjectId,
        ref: "Vaccine"
    },
    name: {
        type: String,
        required: true
    }
});

const VaccinationsSchema = mongoose.model("VaccinationsSchema", vaccinationsSchema);

export default VaccinationsSchema;