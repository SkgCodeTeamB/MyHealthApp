import express from "express";

import { getVaccines } from "../controllers/vaccines.js";
import { addVaccine } from "../controllers/vaccines.js";

const router = express.Router();

router.get("/", getVaccines);
router.post("/add", addVaccine);

export default router;