import express from "express";

import {
  getDoctors,
  addDoctor,
  getDoctorsOfField,
} from "../controllers/doctor.js";

const router = express.Router();

router.get("/", getDoctors);
router.post("/add", addDoctor);
//get Doctors of selected Field
router.get("/:field", getDoctorsOfField);

export default router;
