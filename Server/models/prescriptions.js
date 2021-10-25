import mongoose from "mongoose";

const Schema = mongoose.Schema;

const prescriptionsSchema = new Schema({
    date: {
        type: String,
        required: false
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

const PrescriptionsSchema = mongoose.model("prescriptionsSchema", prescriptionsSchema);

export default PrescriptionsSchema;