import express from "express";

import { getAppointments } from "../controllers/appointment.js";
import { addAppointment } from "../controllers/appointment.js";
import { getBookedSlots } from "../controllers/appointment.js";

const router = express.Router();

router.get("/", getAppointments);
router.post("/add", addAppointment);
router.get("/bookedslots", getBookedSlots)

export default router;