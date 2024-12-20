const express = require("express");
const axios = require("axios");
const Reservation = require("../models/Reservation");

const router = express.Router();

// Route pour récupérer tous les reservations
router.get("/", async (req, res) => {
  try {
    const reservations = await Reservation.findAll();
    res.json(reservations);
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la récupération des reservations.");
  }
});

router.post("/:idhebergement", async (req, res) => {
  try {
    const { idhebergement } = req.params;
    const { clientData, chambreIds, date_arrivee, date_depart } = req.body;

    // Vérifier l'existence de l'hébergement
    const hebergementResponse = await axios.get(
      `http://localhost:9003/Hebergement/${idhebergement}`
    );

    if (!hebergementResponse.data || !hebergementResponse.data.dispo) {
      return res.status(400).send("Hébergement non disponible ou inexistant.");
    }

    // Vérifier ou créer le client
    let clientResponse = await axios.get(
      `http://localhost:3002/api/clients/email/${clientData.email}`
    );
    let client_id;

    if (!clientResponse.data) {
      // Créer un nouveau client si non trouvé
      const newClient = await axios.post(
        "http://localhost:3002/api/clients",
        clientData
      );
      client_id = newClient.data.id;
    } else {
      client_id = clientResponse.data.id;
    }

    // Vérifier la disponibilité des chambres
    const chambresDisponibles = [];
    for (const chambreId of chambreIds) {
      const chambreResponse = await axios.get(
        `http://localhost:3001/api/chambres/${chambreId}`
      );

      const chambre = chambreResponse.data;
      if (chambre.disponibilite) {
        chambresDisponibles.push(chambreId);
      } else {
        return res.status(400).send(`Chambre ${chambreId} déjà réservée.`);
      }
    }

    // Créer la réservation
    const reservation = await Reservation.create({
      client_id,
      hebergement_id: parseInt(idhebergement), // Ajouter l'ID de l'hébergement
      chambres: chambresDisponibles,
      date_arrivee,
      date_depart,
      montant_total: chambresDisponibles.length * 100, // Exemple calcul montant
    });

    // Mettre à jour la disponibilité des chambres
    for (const chambreId of chambresDisponibles) {
      await axios.put(
        `http://localhost:3001/api/chambres/reserver/${chambreId}`,
        {
          reservation_id: reservation.id,
        }
      );
    }

    res.status(201).json(reservation);
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la création de la réservation.");
  }
});

module.exports = router;
