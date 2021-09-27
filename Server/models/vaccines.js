import mongoose from "mongoose";

const vaccinesSchema = mongoose.Schema({
    name: {
        type: String,
        required: true
    },
    id: {
        type: String,
        required: true,
        unique: true
    },
});

const VaccinesSchema = mongoose.model("VaccinesSchema", vaccinesSchema);

export default VaccinesSchema;