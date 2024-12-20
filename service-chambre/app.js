const express = require("express");
const sequelize = require("./config/db"); // Configuration de la base de données Sequelize
const chambreRouter = require("./routes/chambres"); // Routes pour le service hébergement

const app = express();

// Middleware pour analyser les requêtes JSON
app.use(express.json());

// Middleware pour la gestion des erreurs
app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(500).send("Something went wrong!");
});

// Configuration des routes
app.use("/api/chambres", chambreRouter);

// Test de la connexion à la base de données
sequelize
  .sync({ alter: true }) // Crée ou modifie automatiquement les tables
  .then(() => {
    console.log("Base de données synchronisée !");
  })
  .catch((err) => {
    console.error("Erreur de synchronisation :", err);
  });

// Export de l'application pour utilisation dans `server.js`
module.exports = app;
