import express from "express";

import { getAppointments } from "../controllers/appointment.js";
import { addAppointment } from "../controllers/appointment.js";
import { getBookedSlots } from "../controllers/appointment.js";
import { getUsersAppointments } from "../controllers/appointment.js";
import { getUsersAppointmentsCount } from "../controllers/appointment.js";
import { deleteAppointment } from "../controllers/appointment.js";
import { updateAppointment } from "../controllers/appointment.js";

const router = express.Router();

//get all the Appointment (for TEST)
router.get("/", getAppointments);

//add an Appointment (body info)
router.post("/add", addAppointment);

//get booked hour slots of given doctor and given date 
//(front-end filtering of already booked timeslots)
router.get("/bookedslots", getBookedSlots)

//get all the Appointments of given (user's amka on url)
router.get("/:amka", getUsersAppointments);

//get the number of given user's appointments (user's amka on url)
router.get("/count/:amka", getUsersAppointmentsCount);

//delete an Appointment (appointment's id on url)
router.delete("/delete/:id", deleteAppointment);

//update an Appointment (appointment's id, changed and unchanged info are on body)
router.patch("/update", updateAppointment)

export default router;