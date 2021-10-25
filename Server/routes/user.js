import express from "express";
import jwt from "jsonwebtoken";

import {
  loginUser,
  addUser,
  getInfo,
  updateUser,
  // getInfoCount,
} from "../controllers/user.js";

const router = express.Router();

router.post("/login", loginUser);
router.post("/register", addUser);
// router.get("/home/:id", getInfoCount);
router.patch("/updateUser", updateUser);
router.get("/:id", getInfo);

export default router;
