import express from "express";

import {addDiagnose} from "../controllers/diagnoses.js";
import {getDiagnoses} from "../controllers/diagnoses.js";
import {getUsersDiagnoses} from "../controllers/diagnoses.js";
import {getUsersDiagnosesCount} from "../controllers/diagnoses.js";
import {deleteDiagnose} from "../controllers/diagnoses.js";
import {updateDiagnose} from "../controllers/diagnoses.js";


const router = express.Router();


router.get("/", getDiagnoses);
router.post("/add", addDiagnose);
router.get("/:amka", getUsersDiagnoses);
router.get("/count/:amka", getUsersDiagnosesCount);
router.delete("/delete/:id", deleteDiagnose);
router.patch("/update", updateDiagnose);

export default router;