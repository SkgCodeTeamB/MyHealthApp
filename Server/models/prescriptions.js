import mongoose from "mongoose";
const Schema = mongoose.Schema;


const prescriptionsSchema = mongoose.Schema({
    date: {
        type: Date,
        required: false
    },
    text: {
        type: String,
        required: true
    },
    user: {
        type: Schema.Types.ObjectId,
        ref: "User"
    },
    doctor: {
        type: Schema.Types.ObjectId,
        ref: "Doctor"
    }
});

const PrescriptionsSchema = mongoose.model("prescriptionsSchema", prescriptionsSchema);

export default PrescriptionsSchema;