import express from "express";

import { getVaccinations } from "../controllers/vaccination";

const router = express.Router();

router.get("/", getVaccinations);

export default router;