import express from "express";

import { getVaccinations } from "../controllers/vaccination.js";
import { addVaccination } from "../controllers/vaccination.js";
import { doneVaccinations } from "../controllers/vaccination.js";

import { isLoggedIn } from "../middleware/index.js";

const router = express.Router();

//get all the vaccinations
router.get("/", isLoggedIn, getVaccinations);
//add a vaccination
router.post("/add", addVaccination);
//get all the Vaccinations of given (user's id on url)
router.get("/:id", doneVaccinations);

export default router;