import mongoose from "mongoose";
const Schema = mongoose.Schema;

const vaccinationSchema = new Schema({
    date: {
        type: String
    },
    user: {
        type: Schema.Types.ObjectId,
        ref: 'User'
    },
    vaccine: {
        type: Schema.Types.ObjectId,
        ref: 'VaccinesSchema'
    }
});

const Vaccination = mongoose.model("Vaccination", vaccinationSchema);

export default Vaccination;