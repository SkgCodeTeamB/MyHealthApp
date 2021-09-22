import express from "express";
import cors from "cors";
import bodyParser from "body-parser";
import mongoose from "mongoose";
import dotenv from "dotenv";

//Import Routes
import userRoutes from "./routes/user.js";

const app = express();
dotenv.config();

app.use("/user", userRoutes);

app.use(bodyParser.json());
app.use(bodyParser.urlencoded());
app.use(cors());

const PORT = process.env.PORT;

mongoose
  .connect(process.env.CONNECTION_URL, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(() =>
    app.listen(PORT, () => console.log(`Server running on port: ${PORT}`))
  )
  .catch((error) => console.log(error.message));

// mongoose.set("useFindAndModify", false);
