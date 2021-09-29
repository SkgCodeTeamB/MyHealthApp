import mongoose from "mongoose";

const hospitalsSchema = mongoose.Schema({
    name: {
        type: String,
        required: true
    },
    city: {
        type: String,
        require: false
    },
    email: {
        type: String,
        required: true,
        unique: true
    },
    id: {
        type: String,
        required: true,
        unique: true
    }

});

const HospitalsSchema = mongoose.model("HospitalsSchema", hospitalsSchema);

export default HospitalsSchema;