import express from "express";
import jwt from "jsonwebtoken";

import {
  getAppointments,
  addAppointment,
  getBookedSlots,
  getUsersAppointments,
  deleteAppointment,
  updateAppointment,
  getUsersAppointmentsCount,
} from "../controllers/appointment.js";

import { isLoggedIn } from "../middleware/index.js";

const router = express.Router();

//get all the Appointment (for TEST)
router.get("/", isLoggedIn, getAppointments);

//add an Appointment (body info)
router.post("/add", addAppointment);

//get booked hour slots of given doctor and given date
//(front-end filtering of already booked timeslots)
router.get("/bookedslots", getBookedSlots);

//get all the Appointments of given (user's amka on url)
router.get("/:id", getUsersAppointments);

//get the number of given user's appointments (user's amka on url)
router.get("/count/:id", getUsersAppointmentsCount);

//delete an Appointment (appointment's id on url)
router.delete("/delete/:id", deleteAppointment);

//update an Appointment (appointment's id, changed and unchanged info are on body)
router.patch("/update", updateAppointment);

export default router;
