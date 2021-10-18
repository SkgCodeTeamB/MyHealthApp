import express from "express";

import {
  getPrescriptions,
  addPrescription,
  getUsersPrescriptions,
} from "../controllers/prescriptions.js";

import { isLoggedIn } from "../middleware/index.js";

const router = express.Router();

router.get("/", isLoggedIn, getPrescriptions);
router.post("/add", isLoggedIn, addPrescription);
router.get("/:id", isLoggedIn, getUsersPrescriptions);

export default router;
