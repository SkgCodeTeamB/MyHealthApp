import express from "express";

import { getAppointments } from "../controllers/appointment.js";
import { addAppointment } from "../controllers/appointment.js";

const router = express.Router();

router.get("/", getAppointments);
router.post("/add", addAppointment);

//changes
import { test } from "../controllers/appointment.js";
router.get("/test", test)

export default router;