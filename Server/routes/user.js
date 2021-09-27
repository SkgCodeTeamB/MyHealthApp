import express from "express";

import { getUser } from "../controllers/user.js";
import { postUser } from "../controllers/user.js";

const router = express.Router();

router.get("/", getUser);

router.post("/test", postUser);

export default router;
