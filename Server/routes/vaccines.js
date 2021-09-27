import express from "express";

import { getVaccines } from "../controllers/vaccines.js";

const router = express.Router();

router.get("/", getVaccines);

export default router;