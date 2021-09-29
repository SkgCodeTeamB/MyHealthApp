import express from "express";

import { getDiagnoses } from "../controllers/diagnoses.js";

const router = express.Router();

router.get("/", getDiagnoses);

export default router;