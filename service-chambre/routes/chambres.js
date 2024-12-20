const express = require("express");
const Chambre = require("../models/chambres");

const router = express.Router();

// Route pour récupérer tous les chambres
router.get("/", async (req, res) => {
  try {
    const chambres = await Chambre.findAll();
    res.json(chambres);
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la récupération des chambres.");
  }
});

// Route pour ajouter un nouvel chambres
router.post("/", async (req, res) => {
  try {
    const chambres = await Chambre.create(req.body);
    res.status(201).json(chambres);
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la création de chambres.");
  }
});

// Route pour récupérer un chambres par ID
router.get("/:id", async (req, res) => {
  try {
    const chambres = await Chambre.findByPk(req.params.id);
    if (!chambres) {
      return res.status(404).send("chambres non trouvé.");
    }
    res.json(chambres);
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la récupération de chambres.");
  }
});

// Route pour mettre à jour un chambres
router.put("/:id", async (req, res) => {
  try {
    const chambres = await Chambre.findByPk(req.params.id);
    if (!chambres) {
      return res.status(404).send("chambres non trouvé.");
    }
    await chambres.update(req.body);
    res.json(chambres);
    const express = require("express");
    const Chambre = require("../models/chambres");

    const router = express.Router();

    // Route pour récupérer tous les chambres
    router.get("/", async (req, res) => {
      try {
        const chambres = await Chambre.findAll();
        res.json(chambres);
      } catch (error) {
        console.error(error);
        res.status(500).send("Erreur lors de la récupération des chambres.");
      }
    });

    // Route pour ajouter un nouvel chambres
    router.post("/", async (req, res) => {
      try {
        const chambres = await Chambre.create(req.body);
        res.status(201).json(chambres);
      } catch (error) {
        console.error(error);
        res.status(500).send("Erreur lors de la création de chambres.");
      }
    });

    // Route pour récupérer un chambres par ID
    router.get("/:id", async (req, res) => {
      try {
        const chambres = await Chambre.findByPk(req.params.id);
        if (!chambres) {
          return res.status(404).send("chambres non trouvé.");
        }
        res.json(chambres);
      } catch (error) {
        console.error(error);
        res.status(500).send("Erreur lors de la récupération de chambres.");
      }
    });

    // Route pour mettre à jour un chambres
    router.put("/:id", async (req, res) => {
      try {
        const chambres = await Chambre.findByPk(req.params.id);
        if (!chambres) {
          return res.status(404).send("chambres non trouvé.");
        }
        await chambres.update(req.body);
        res.json(chambres);
      } catch (error) {
        console.error(error);
        res.status(500).send("Erreur lors de la mise à jour de chambres.");
      }
    });

    // Route pour supprimer un chambres
    router.delete("/:id", async (req, res) => {
      try {
        const chambres = await Chambre.findByPk(req.params.id);
        if (!chambres) {
          return res.status(404).send("chambres non trouvé.");
        }
        await chambres.destroy();
        res.status(204).send();
      } catch (error) {
        console.error(error);
        res.status(500).send("Erreur lors de la suppression de chambres.");
      }
    });

    module.exports = router;
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la mise à jour de chambres.");
  }
});

// Route pour supprimer un chambres
router.delete("/:id", async (req, res) => {
  try {
    const chambres = await Chambre.findByPk(req.params.id);
    if (!chambres) {
      return res.status(404).send("chambres non trouvé.");
    }
    await chambres.destroy();
    res.status(204).send();
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la suppression de chambres.");
  }
});
// Vérifier et réserver une chambre
router.put("/reserver/:id", async (req, res) => {
  try {
    const { reservation_id } = req.body;
    const chambre = await Chambre.findByPk(req.params.id);

    if (!chambre) {
      return res.status(404).send("Chambre non trouvée.");
    }

    if (!chambre.disponibilite) {
      return res.status(400).send("Chambre déjà réservée.");
    }

    // Mettre à jour la disponibilité et associer la réservation
    chambre.disponibilite = false;
    chambre.reservation_id = reservation_id;
    await chambre.save();

    res.json(chambre);
  } catch (error) {
    console.error(error);
    res.status(500).send("Erreur lors de la réservation de la chambre.");
  }
});

module.exports = router;
