import express from "express";

import { getFields, addField } from "../controllers/field.js";

const router = express.Router();

router.get("/", getFields);
router.post("/add", addField);

export default router;
