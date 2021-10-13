import express from "express";

import {getPrescriptions} from "../controllers/prescriptions.js";
import {addPrescription} from "../controllers/prescriptions.js";
import {getUsersPrescriptions} from "../controllers/prescriptions.js";
import {getUsersPrescriptionCount} from "../controllers/prescriptions.js";
import {deletePrescription} from "../controllers/prescriptions.js";
import {updatePrescription} from "../controllers/prescriptions.js";

const router = express.Router();

router.get("/", getPrescriptions);
router.post("/add", addPrescription);
router.get("/:id", getUsersPrescriptions);
router.get("/count/:id", getUsersPrescriptionCount);
router.delete("/delete/:id", deletePrescription);
router.patch("/update", updatePrescription);



export default router;


