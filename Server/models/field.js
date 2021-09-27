import mongoose from "mongoose";
const Schema = mongoose.Schema;

const fieldSchema = new Schema({
  id: {
    type: String,
    required: true,
    unique: true
  },
  name: {
    type: String,
    required: true,
    unique: true
  }
});

const Field = mongoose.model("Field", fieldSchema);

export default Field;
