package com.example.demandeprop.Controller;

import com.example.demandeprop.Data.*;
import com.example.demandeprop.Repository.*;
import com.example.demandeprop.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Demande")

public class RestDemande {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    ChambreRepository chambreRepository;

    @Autowired
    ChambreService chambreService;

    @Autowired
    HebergementReository hebergementReository;

    @Autowired
    HebergementService hebergementService;

    @Autowired
    PropRepository propRepository;

    @Autowired
    ProptService proptService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping(value = "/Hebergement/{idProp}")
    public Hebergement saveDemande(
            @PathVariable(name = "idProp") Long idProp,
            @RequestBody Hebergement hebergement) {

        Proprietaire proprietaire = proptService.getbyid(idProp);
        hebergement.setProprietaire(proprietaire);
        hebergement.setEtat("non");
        hebergement.setDispo(false);

        // Enregistrer directement dans hebergementReository
        return hebergementReository.save(hebergement);
    }

    @GetMapping("/fullHebergement")
    public List<Hebergement> getAll(){

        return hebergementReository.findAll();
    }

    @GetMapping("/fullHebergementNonAccepter")
    public List<Hebergement> getAllHebergment(){

        return hebergementReository.findByEtat("non");
    }
    @GetMapping("HebergementById/{id}")
    public Hebergement getHebergementbyid(@PathVariable Long id){
        return hebergementReository.findById(id).get();
    }

    @GetMapping("/Hebergement/{idProp}")
    public Hebergement getHebergement(@PathVariable(name = "idProp") Long idProp){

        Proprietaire proprietaire=proptService.getbyid(idProp);
        Hebergement hebergement = hebergementReository.findByProprietaire(proprietaire);

        return hebergement;
    }
    @GetMapping(value = "/accepterHebergement/{id}")
    public Hebergement AccepterHebergement(@PathVariable Long id) {
        Hebergement hebergement = hebergementService.getbyid(id);

        hebergement.setEtat("oui");
        hebergement.setDispo(true);

        String to = hebergement.getEmail();

        String subject = "Félicitations !";
        String text = "Félicitations ! Vous êtes accepter.";
        emailService.sendEmail(to, subject, text);

        return hebergementReository.save(hebergement);
    }
    @DeleteMapping(value = "/refuserHebergement/{id}")
      void refuserHebergement(@PathVariable Long id) {

        Hebergement hebergement = hebergementService.getbyid(id);

        String to = hebergement.getEmail();
        String subject = "Dommage !";
        String text = "Dommage ! Vous êtes refuser.";
        emailService.sendEmail(to, subject, text);

        hebergementReository.deleteById(id);
    }
/*
    @PostMapping(value = "/HebergementWithImages/{idProp}")
    public Hebergement saveDemandeWithImages(@PathVariable(name = "idProp") Long idProp,
                                             @RequestBody HebergementRequest request) {
        Hebergement hebergement = request.getHebergement();
        List<Image> images = request.getImages();

        // Associez les images à l'hébergement
        for (Image image : images) {
            image.setHebergementImg(hebergement);
        }

        hebergement.setImages(images);

        // Sauvegarde des entités
        hebergement.setProprietaire(proptService.getbyid(idProp));
        hebergement.setEtat("non");

        return hebergementReository.save(hebergement);
    }
*/
@PostMapping(value = "/HebergementWithImages/{idProp}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public Hebergement saveDemandeWithImages(
        @PathVariable(name = "idProp") Long idProp,
        @RequestPart("hebergement") Hebergement hebergement,
        @RequestPart("images") List<MultipartFile> images) throws IOException {

    // Convertir les MultipartFiles en entités Image
    List<Image> imageEntities = new ArrayList<>();
    for (MultipartFile file : images) {
        Image image = new Image();
        image.setImage(file.getBytes());
        image.setHebergementImg(hebergement);
        imageEntities.add(image);
    }

    // Associer les images à l'hébergement
    hebergement.setImages(imageEntities);

    // Définir le propriétaire et l'état
    hebergement.setProprietaire(proptService.getbyid(idProp));

    hebergement.setEtat("non");
    hebergement.setDispo(false);

    // Sauvegarder l'hébergement avec les images
    return hebergementReository.save(hebergement);
}

    @GetMapping("/hebergementsDisponibles")
    public List<Hebergement> getHebergementsDisponibles() {
        return hebergementReository.findByDispo(true);
    }

    @GetMapping("/hebergementById/{id}")
    public ResponseEntity<Hebergement> getHebergementById(@PathVariable Long id) {
        return hebergementReository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   /* @GetMapping("/hebergement/{id}/images")
    public ResponseEntity<List<byte[]>> getImagesByHebergementId(@PathVariable Long id) {
        // Récupérer l'hébergement par ID
        Hebergement hebergement = hebergementReository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hébergement non trouvé avec l'ID : " + id));

        // Récupérer les images associées
        List<byte[]> images = hebergement.getImages()
                .stream()
                .map(Image::getImage)
                .toList();

        return ResponseEntity.ok(images);
    }
*/
   @GetMapping("/hebergement/{id}/images")
   public ResponseEntity<List<String>> getImagesByHebergementId(@PathVariable Long id) {
       // Récupérer l'hébergement par ID
       Hebergement hebergement = hebergementReository.findById(id)
               .orElseThrow(() -> new RuntimeException("Hébergement non trouvé avec l'ID : " + id));

       // Récupérer les images associées et les encoder en Base64
       List<String> imagesBase64 = hebergement.getImages()
               .stream()
               .map(image -> Base64.getEncoder().encodeToString(image.getImage()))
               .toList();

       return ResponseEntity.ok(imagesBase64);
   }

    @GetMapping("/hebergements/{id}/chambres")
    public ResponseEntity<List<Chambre>> getChambresByHebergementId(@PathVariable Long id)
 {
        // Call the service to fetch chambres by hebergement ID
        List<Chambre> chambres = hebergementService.getChambresByHebergementId(id);

        // If no chambres are found, return a not found status
        if (chambres == null || chambres.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Return the list of chambres
        return ResponseEntity.ok(chambres);
    }




    ////chambre
  @GetMapping("/fullChambres")
   public List<Chambre> getAllChambre(){

    return chambreRepository.findAll();
    }

    @PostMapping(value = "/Chambre/{idProp}")
    public Chambre saveChambre(
            @PathVariable(name = "idProp") Long idProp,
            @RequestBody Chambre chambre) {

        Proprietaire proprietaire = proptService.getbyid(idProp);
        Hebergement hebergement = hebergementReository.findByProprietaire(proprietaire);

        chambre.setHebergement(hebergement);
        chambre.setDisponibilite(true);

        // Enregistrer directement dans chambre
        return chambreRepository.save(chambre);
    }

    @DeleteMapping("Chambre/{id}")
    void deleteChambre(@PathVariable Long id){
        chambreRepository.deleteById(id);

    }

    @PutMapping("Chambre/{id}")
    public  Chambre updateChambre(@PathVariable Long id,@RequestBody Chambre newchambre){

        Chambre chambre= chambreRepository.findById(id).get();

        newchambre.setId(id);
        chambre.setDisponibilite(true);

        // Sauvegarder chambre
        return chambreRepository.save(newchambre);
    }
    @GetMapping("/ChambreById/{id}")
    public ResponseEntity<Chambre> getChambreById(@PathVariable Long id) {
        return chambreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
/*
    ///liste des reservation proprietaire
    @GetMapping("/proprietaire/{proprietaireId}")
    public ResponseEntity<List<Reservation>> getReservationsByProprietaireId(@PathVariable Long proprietaireId) {
        List<Hebergement> hebergements = hebergementReository.findByProprietaireId(proprietaireId);
        if (hebergements.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<Long> chambreIds = hebergements.stream()
                .flatMap(hebergement -> Optional.ofNullable(hebergement.getChambres()).orElse(new ArrayList<>()).stream())
                .map(Chambre::getId)
                .toList();

        if (chambreIds.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<Reservation> reservations = reservationRepository.findByChambresIn(chambreIds);
        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reservations);
    }
*/

    //reservation
    @GetMapping("/fullReservation")
    public List<Reservation> getAllReservation(){

        return reservationRepository.findAll();
    }

    @GetMapping("/Reservation/{idp}")
    public List<Reservation> getReservationByProprietaireId(@PathVariable Long idp) {
        // Récupérer tous les hébergements du propriétaire
        List<Hebergement> hebergements = hebergementReository.findByProprietaireId(idp);

        // Extraire les IDs des hébergements
        List<Long> hebergementIds = hebergements.stream()
                .map(Hebergement::getId)
                .collect(Collectors.toList());

        // Récupérer les réservations liées à ces hébergements
        return reservationRepository.findByHebergementIdIn(hebergementIds);
    }
    @GetMapping("ClientById/{id}")
    public Client getClientbyid(@PathVariable Integer id){
        return clientRepository.findById(id).get();
    }

    @GetMapping("ChambreByIdreservation/{id}")
        public Chambre getChambreByIdreservation(@PathVariable Long id){
        return chambreRepository.findById(id).get();
    }

    @GetMapping(value = "/accepterReservation/{id}")
    public Reservation AccepterReservation(@PathVariable Integer id) {

        Reservation reservation = reservationRepository.getById(id);

        reservation.setStatut("accepter");

        Client client = clientRepository.getById(reservation.getClientId());
        String to = client.getEmail();

        String subject = "Félicitations !";
        String text = "Félicitations ! Votre reservation est accepter.";
        emailService.sendEmail(to, subject, text);

        return reservationRepository.save(reservation);
    }

    @DeleteMapping(value = "/refuserReservation/{id}")
    void refuserReservation(@PathVariable Integer id) {
        // Récupérer la réservation par son ID
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable"));

        // Libérer les chambres associées en réinitialisant reservationId et en les rendant disponibles
        for (Long chambreId : reservation.getChambres()) {
            Chambre chambre = chambreRepository.findById(chambreId)
                    .orElseThrow(() -> new RuntimeException("Chambre introuvable"));

            // Réinitialiser reservationId et rendre la chambre disponible
            chambre.setReservationId(null); // Supprimer la référence à la réservation
            chambre.setDisponibilite(true); // Rendre la chambre disponible
            chambreRepository.save(chambre); // Sauvegarder la chambre
        }

        // Notifier le client par email
        Client client = clientRepository.findById(reservation.getClientId())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        String to = client.getEmail();
        String subject = "Dommage !";
        String text = "Dommage ! Votre réservation a été refusée.";
        emailService.sendEmail(to, subject, text);

        // Supprimer la réservation
        reservationRepository.deleteById(id);
    }
    @GetMapping("/hebergements/search")
    public List<Hebergement> searchHebergementsByLocalisation(@RequestParam String localisation) {
        return hebergementReository.findByLocalisationContainingIgnoreCase(localisation);
    }

    @GetMapping("/ListeDeschambresByHebergementId/{hebergementId}")
    public ResponseEntity<List<Chambre>> getChambresByHebergement(@PathVariable Long hebergementId) {
        // Vérifie si l'hébergement existe
        if (!hebergementReository.existsById(hebergementId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList()); // Retourne une liste vide si l'hébergement n'existe pas
        }

        // Récupère les chambres associées à cet hébergement
        List<Chambre> chambres = chambreRepository.findByHebergementId(hebergementId);
        return ResponseEntity.ok(chambres);
    }
    @GetMapping("/ListeDesChambresDisponibles/{hebergementId}")
    public ResponseEntity<List<Chambre>> getChambresDisponiblesByHebergement(@PathVariable Long hebergementId) {
        // Vérifie si l'hébergement existe
        if (!hebergementReository.existsById(hebergementId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList()); // Retourne une liste vide si l'hébergement n'existe pas
        }

        // Récupère les chambres disponibles associées à cet hébergement
        List<Chambre> chambresDisponibles = chambreRepository.findByHebergementIdAndDisponibiliteTrue(hebergementId);

        if (chambresDisponibles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        }

        return ResponseEntity.ok(chambresDisponibles);
    }
}
