import express from "express";

import { getVaccinations } from "../controllers/vaccination.js";

const router = express.Router();

router.get("/", getVaccinations);

export default router;
