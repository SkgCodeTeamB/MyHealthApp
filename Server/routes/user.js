import express from "express";
import jwt from "jsonwebtoken";

import { loginUser } from "../controllers/user.js";
import { addUser } from "../controllers/user.js";
import { getInfo } from "../controllers/user.js";
import { getVaccinations } from "../controllers/vaccination.js";
import { getUsersDiagnoses } from "../controllers/diagnoses.js";
import { getUsersDiagnosesCount } from "../controllers/diagnoses.js";
import { getUsersPrescriptions } from "../controllers/prescriptions.js";
import { getUsersPrescriptionCount } from "../controllers/prescriptions.js";
import { updateUser } from "../controllers/user.js";
import { deleteUser } from "../controllers/user.js";

const router = express.Router();

router.post("/login", loginUser);
router.post("/add", addUser);
router.patch("/updateUser", updateUser);
router.get("/vaccinations", getVaccinations);
router.get("/diagnoses/:amka", getUsersDiagnoses);
router.get("/diagnoses/count/:amka", getUsersDiagnosesCount);
router.get("/prescriptions/:amka", getUsersPrescriptions);
router.get("/prescriptions/count/:amka", getUsersPrescriptionCount);
router.get("/:amka", getInfo);
router.delete("/delete/:id", deleteUser);

export default router;
