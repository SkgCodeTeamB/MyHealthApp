import express from "express";
import jwt from "jsonwebtoken";

import {
  loginUser,
  addUser,
  getInfo,
  updateUser,
} from "../controllers/user.js";

const router = express.Router();

router.post("/login", loginUser);
router.post("/register", addUser);
router.patch("/updateUser", updateUser);
router.get("/:id", getInfo);

export default router;
