const { Sequelize } = require("sequelize");

// Configuration de la connexion Sequelize
const sequelize = new Sequelize("Booking", "root", "", {
  host: "localhost",
  dialect: "mysql", // Vous utilisez MySQL
  dialectOptions: {
    connectTimeout: 10000, // Temps d'attente pour la connexion (optionnel)
  },
  logging: false, // Désactive les logs Sequelize (optionnel)
});

module.exports = sequelize;
