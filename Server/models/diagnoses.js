import mongoose from "mongoose";
const Schema = mongoose.Schema;

const diagnoseSchema = mongoose.Schema({
    Date: {
        type: String,
        required: true
    },
    text: {
        type: String,
        required: true
    },
    user: {
        type: Schema.Types.ObjectId,
        ref: 'User'
    },
    doctor: {
        type: Schema.Types.ObjectId,
        ref: 'Doctor'
    }
});

const DiagnoseSchema = mongoose.model("DiagnoseSchema", diagnoseSchema);

export default DiagnoseSchema;