import express from "express";

import {
  getVaccinations,
  addVaccination,
  doneVaccinations,
} from "../controllers/vaccination.js";

import { isLoggedIn } from "../middleware/index.js";

const router = express.Router();

//get all the vaccinations
router.get("/", getVaccinations);
//add a vaccination
router.post("/add", addVaccination);
//get all the Vaccinations of given (user's id on url)
router.get("/:id", doneVaccinations);

export default router;
