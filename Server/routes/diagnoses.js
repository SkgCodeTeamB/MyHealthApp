import express from "express";

import {addDiagnose} from "../controllers/diagnoses.js";
import {getDiagnoses} from "../controllers/diagnoses.js";
import {getUsersDiagnoses} from "../controllers/diagnoses.js";
import {isLoggedIn} from "../middleware/index.js";

const router = express.Router();


router.get("/", isLoggedIn, getDiagnoses);
router.post("/add", isLoggedIn, addDiagnose);
router.get("/:id", isLoggedIn, getUsersDiagnoses);


export default router;