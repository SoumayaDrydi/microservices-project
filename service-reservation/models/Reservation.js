const { DataTypes } = require("sequelize");
const sequelize = require("../config/db");

const Reservation = sequelize.define(
  "Reservation",
  {
    id: {
      type: DataTypes.INTEGER,
      primaryKey: true,
      autoIncrement: true,
    },
    client_id: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: "client", // Nom de la table Client
        key: "id",
      },
    },
    chambres: {
      type: DataTypes.JSON, // Liste d'IDs des chambres
      allowNull: false,
    },
    date_arrivee: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    date_depart: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    statut: {
      type: DataTypes.STRING,
      defaultValue: "En attente",
    },
    montant_total: {
      type: DataTypes.FLOAT,
      allowNull: true,
    },
    hebergement_id: {
      type: DataTypes.BIGINT, // Au lieu de INTEGER
      references: {
        model: "hebergement", // Nom de la table référencée
        key: "id",
      },
    },
  },
  {
    tableName: "reservation",
    timestamps: false,
  }
);

module.exports = Reservation;
