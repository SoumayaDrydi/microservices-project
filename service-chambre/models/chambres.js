const { DataTypes } = require("sequelize");
const sequelize = require("../config/db");

const Chambre = sequelize.define(
  "Chambre",
  {
    id: {
      type: DataTypes.INTEGER,
      primaryKey: true,
      autoIncrement: true,
    },
    nb_personne: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    espace: {
      type: DataTypes.FLOAT,
      allowNull: false,
    },
    bed: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    disponibilite: {
      type: DataTypes.BOOLEAN,
      allowNull: false,
    },
    climatisation: {
      type: DataTypes.BOOLEAN,
      allowNull: false,
    },
    balcon: {
      type: DataTypes.BOOLEAN,
      allowNull: false,
    },
    televesion: {
      type: DataTypes.BOOLEAN,
      allowNull: false,
    },
    wifi: {
      type: DataTypes.BOOLEAN,
      allowNull: false,
    },
    salledebains: {
      type: DataTypes.BOOLEAN,
      allowNull: false,
    },
    prix: {
      type: DataTypes.FLOAT,
      allowNull: false,
    },
    reservation_id: {
      type: DataTypes.INTEGER, // Clé étrangère vers le service réservation
      allowNull: true,
      references: {
        model: "reservation", // Nom de la table Hébergement
        key: "id",
      },
    },
  },
  {
    tableName: "chambre",
    timestamps: false, // Pas de champs createdAt ou updatedAt
  }
);

module.exports = Chambre;
