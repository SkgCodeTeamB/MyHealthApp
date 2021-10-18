import express from "express";

import {
  getPrescriptions,
  addPrescription,
  getUsersPrescriptions,
  getUsersPrescriptionCount,
  deletePrescription,
  updatePrescription,
} from "../controllers/prescriptions.js";

const router = express.Router();

router.get("/", getPrescriptions);
router.post("/add", addPrescription);
router.get("/:id", getUsersPrescriptions);
router.get("/count/:id", getUsersPrescriptionCount);
router.delete("/delete/:id", deletePrescription);
router.patch("/update", updatePrescription);

export default router;
