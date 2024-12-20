const express = require("express");
const sequelize = require("./config/db");
const clientRouter = require("./routes/clients"); // Import du routeur

const app = express();

app.use(express.json());

// Synchronisation avec la base de données
sequelize
  .sync({ alter: true }) // Crée ou modifie automatiquement les tables
  .then(() => {
    console.log("Base de données synchronisée !");
  })
  .catch((err) => {
    console.error("Erreur de synchronisation :", err);
  });

// Configuration des routes
app.use("/api/clients", clientRouter); // Utilisation du routeur

module.exports = app;
