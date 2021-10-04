import express from "express";

import { getVaccinations } from "../controllers/vaccination.js";
import { addVaccination } from "../controllers/vaccination.js";
import { doneVaccinations } from "../controllers/vaccination.js";

const router = express.Router();

//get all the vaccinations
router.get("/", getVaccinations);
//add a vaccination
router.post("/add", addVaccination);
//get all the Vaccinations of given (user's amka on url)
router.get("/:amka", doneVaccinations);

export default router;