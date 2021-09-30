import express from "express";

import { getAppointments } from "../controllers/appointment.js";
import { addAppointment } from "../controllers/appointment.js";
import { getBookedSlots } from "../controllers/appointment.js";
import { getUsersAppointments } from "../controllers/appointment.js";
import { getUsersAppointmentsCount } from "../controllers/appointment.js";

const router = express.Router();

//get all the Appointment
router.get("/", getAppointments);
//add an Appointment
router.post("/add", addAppointment);
//get booked hour slots of given doctor and given date
router.get("/bookedslots", getBookedSlots)
//get all the Appointments of given user through url
router.get("/:amka", getUsersAppointments);
//get the number of given user's appointments
router.get("/count/:amka", getUsersAppointmentsCount);

export default router;