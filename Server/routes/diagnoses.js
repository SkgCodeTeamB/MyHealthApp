import express from "express";

import {
  addDiagnose,
  getDiagnoses,
  getUsersDiagnoses,
  getUsersDiagnosesCount
} from "../controllers/diagnoses.js";

import { isLoggedIn } from "../middleware/index.js";

const router = express.Router();

router.get("/", getDiagnoses);
router.post("/add", addDiagnose);
router.get("/:id", getUsersDiagnoses);
router.get("/count/:id", getUsersDiagnosesCount);

export default router;
