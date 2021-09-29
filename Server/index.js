import express from "express";
import cors from "cors";
import bodyParser from "body-parser";
import mongoose from "mongoose";
import dotenv from "dotenv";

//Import Routes
import userRoutes from "./routes/user.js";
import appointmentRoutes from "./routes/appointment.js";
import doctorRoutes from "./routes/doctor.js";
import fieldRoutes from "./routes/field.js";
import diagnosesRoutes from "./routes/diagnoses.js";
import hospitalsRoutes from "./routes/hospitals.js";
import vaccinesRoutes from "./routes/vaccines.js";
import prescriptionsRoutes from "./routes/prescriptions.js";
import vaccinationRoutes from "./routes/vaccination.js";

const app = express();
dotenv.config();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded());
app.use(cors());

app.use("/user", userRoutes);
app.use("/appointment", appointmentRoutes);
app.use("/doctor", doctorRoutes);
app.use("/field", fieldRoutes);
app.use("/diagnose", diagnosesRoutes);
app.use("/hospital", hospitalsRoutes);
app.use("/vaccine", vaccinesRoutes);
app.use("/prescription", prescriptionsRoutes);
app.use("/vaccination", vaccinationRoutes);

const PORT = process.env.PORT;

mongoose
  .connect(process.env.CONNECTION_URL, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(() =>
    app.listen(PORT, () => console.log(`Server running on port: ${PORT}`))
  )
  .catch((error) => console.log(error.message));

// mongoose.set("useFindAndModify", false);
