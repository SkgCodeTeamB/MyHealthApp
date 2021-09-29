import express from "express";

import { getVaccinations } from "../controllers/vaccination.js";
import { addVaccination } from "../controllers/vaccination.js";
import { doneVaccinations } from "../controllers/vaccination.js";

const router = express.Router();

//get all the vaccinations
router.get("/", getVaccinations);
//add a vaccination
router.post("/add", addVaccination);
//get the vaccinations of a user
router.get("/done", doneVaccinations);

export default router;