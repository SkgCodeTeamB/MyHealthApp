import express from "express";

import {
  addDiagnose,
  getDiagnoses,
  getUsersDiagnoses,
  getUsersDiagnosesCount,
  deleteDiagnose,
  updateDiagnose,
} from "../controllers/diagnoses.js";

const router = express.Router();

router.get("/", getDiagnoses);
router.post("/add", addDiagnose);
router.get("/:id", getUsersDiagnoses);
router.get("/count/:id", getUsersDiagnosesCount);
router.delete("/delete/:id", deleteDiagnose);
router.patch("/update", updateDiagnose);

export default router;
