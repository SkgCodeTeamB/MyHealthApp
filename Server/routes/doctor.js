import express from "express";

import { getDoctors } from "../controllers/doctor.js";
import { addDoctor } from "../controllers/doctor.js";

const router = express.Router();

router.get("/", getDoctors);
router.post("/add", addDoctor);

export default router;