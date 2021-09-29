import express from "express";

import { getUser } from "../controllers/user.js";
import { addUser } from "../controllers/user.js";
import { getVaccinations } from "../controllers/vaccination.js";
import { getDiagnoses } from "../controllers/diagnoses.js";
import { getPrescriptions } from "../controllers/prescriptions.js";

const router = express.Router();

router.get("/", getUser);
router.post("/add", addUser);
router.get("/vaccinations", getVaccinations);
router.get("/diagnoses", getDiagnoses);
router.get("/prescriptions", getPrescriptions);



export default router;
