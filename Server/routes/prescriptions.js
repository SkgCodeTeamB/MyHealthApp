import express from "express";

import { getPrescriptions } from "../controllers/prescriptions";

const router = express.Router();

router.get("/", getPrescriptions);

export default router;