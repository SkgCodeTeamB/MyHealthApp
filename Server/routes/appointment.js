import express from "express";

import { getAppointments } from "../controllers/appointment.js";
import { addAppointment } from "../controllers/appointment.js";
import { getBookedSlots } from "../controllers/appointment.js";
import { getUsersAppointments } from "../controllers/appointment.js";

const router = express.Router();

//get all the Appointment
router.get("/", getAppointments);
//add an Appointment
router.post("/add", addAppointment);
//get booked hour slots of given doctor and given date
router.get("/bookedslots", getBookedSlots)
//get all the Appointments of given user
router.get("/my", getUsersAppointments);

export default router;