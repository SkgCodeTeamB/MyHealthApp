// const User = require("../models/user");
// const jwt = require("jsonwebtoken");

// export const isLoggedIn = async (req, res, next) => {};

//Validation
import Joi from "@hapi/joi";
import jwt from "jsonwebtoken";

export const registerValidation = (data) => {
  const schema = Joi.object({
    name: Joi.string().min(6).required(),
    surname: Joi.string().min(6).required(),
    email: Joi.string().min(6).required().email(),
    phone: Joi.string().min(10).required(),
    birthday: Joi.string().min(1).required(),
    bloodtype: Joi.string()
      .min(10)
      .valid("O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"),
    amka: Joi.string().min(6).required(),
    familydoctor: Joi.string().allow("").optional(),
    address: Joi.string().allow("").optional(),
    city: Joi.string().allow("").optional(),
    postalcode: Joi.string().allow("").optional(),
  });

  return schema.validate(data);
};

export const loginValidation = (data) => {
  const schema = Joi.object({
    amka: Joi.string().min(6).required(),
  });
  return schema.validate(data);
};

export const isLoggedIn = async (req, res, next) => {
  const token = req.header("auth-token");
  if (!token) return res.status(401).send("Access Denied");

  try {
    const verified = jwt.verify(token, process.env.TOKEN_SECRET);
    req.user = verified;
    next();
  } catch (err) {
    res.status(400).send("Invalid Token");
  }
};
