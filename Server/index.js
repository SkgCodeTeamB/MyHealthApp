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
import hospitalsRoute from "./routes/hospitals.js";
import vaccinesRoute from "./routes/vaccines.js";
import prescriptionsRoute from "./routes/prescriptions.js";
import vaccinationRoute from "./routes/vaccinations.js";

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
app.use("/hospital", hospitalsRoute);
app.use("/vaccine", vaccinesRoute);
app.use("/prescription", prescriptionsRoute);
app.use("/vaccination", vaccinationRoute);

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
