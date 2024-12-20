const express = require("express");
const Client = require("../models/Client"); // Import du modèle Client

const router = express.Router();

// Récupérer tous les clients
router.get("/all", async (req, res) => {
  try {
    const clients = await Client.findAll(); // Utilisation correcte du modèle
    res.json(clients);
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la récupération des clients.");
  }
});

// Ajouter un nouveau client
router.post("/", async (req, res) => {
  try {
    const client = await Client.create(req.body);
    res.status(201).json(client);
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la création du client.");
  }
});

// Récupérer un client par ID
router.get("/:id", async (req, res) => {
  try {
    const client = await Client.findByPk(req.params.id);
    if (!client) {
      return res.status(404).send("Client non trouvé.");
    }
    res.json(client);
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la récupération du client.");
  }
});

// Mettre à jour un client
router.put("/:id", async (req, res) => {
  try {
    const client = await Client.findByPk(req.params.id);
    if (!client) {
      return res.status(404).send("Client non trouvé.");
    }
    await client.update(req.body);
    res.json(client);
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la mise à jour du client.");
  }
});

// Supprimer un client
router.delete("/:id", async (req, res) => {
  try {
    const client = await Client.findByPk(req.params.id);
    if (!client) {
      return res.status(404).send("Client non trouvé.");
    }
    await client.destroy();
    res.status(204).send();
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la suppression du client.");
  }
});
router.get("/email/:email", async (req, res) => {
  try {
    const client = await Client.findOne({ where: { email: req.params.email } });
    if (!client) {
      return res.status(404).send("Client non trouvé.");
    }
    res.json(client);
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la récupération du client.");
  }
});

module.exports = router;
