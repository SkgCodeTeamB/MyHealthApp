import express from "express";

import {
  getPrescriptions,
  addPrescription,
  getUsersPrescriptions,
  getUsersPrescriptionsCount
} from "../controllers/prescriptions.js";

import { isLoggedIn } from "../middleware/index.js";

const router = express.Router();

router.get("/", getPrescriptions);
router.post("/add", addPrescription);
router.get("/:id", getUsersPrescriptions);
router.get("/count/:id",getUsersPrescriptionsCount);

export default router;
