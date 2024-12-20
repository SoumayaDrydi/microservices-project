package com.example.image.controller;

import com.example.image.data.Image;
import com.example.image.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/Image")
@CrossOrigin("*")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    // Récupérer toutes les images
    @GetMapping
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    // Récupérer une image par ID
    @GetMapping("/{id}")
    public Image getById(@PathVariable Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image non trouvée"));
    }

    // Sauvegarder une image en Base64 (depuis un JSON)
    @PostMapping
    public Image saveImage(@RequestBody Image image) {
        return imageRepository.save(image);
    }

    // Supprimer une image par ID
    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        imageRepository.deleteById(id);
    }

    // Mettre à jour une image
    @PutMapping("/{id}")
    public Image updateImage(@PathVariable Long id, @RequestBody Image newImage) {
        newImage.setId(id);
        return imageRepository.save(newImage);
    }

    // Télécharger une image via MultipartFile
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Image uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes(); // Récupération des octets du fichier

        Image image = new Image();
        image.setImage(bytes); // Stockage en tant que byte[]
        return imageRepository.save(image);
    }

    // Afficher une image (pour les visualiser dans le navigateur)
    @GetMapping("/display/{id}")
    public ResponseEntity<byte[]> displayImage(@PathVariable Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image non trouvée"));

        // Retourne l'image sous forme de tableau d'octets
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Modifiez si nécessaire : IMAGE_PNG, etc.
                .body(image.getImage());
    }

}
