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
router.post("/register", addUser);
router.patch("/updateUser", updateUser);
router.get("/:id", getInfo);

export default router;
