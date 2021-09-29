import express from "express";

import { getAppointments } from "../controllers/appointment.js";
import { addAppointment } from "../controllers/appointment.js";

const router = express.Router();

router.get("/", getAppointments);
router.post("/add", addAppointment);

export default router;