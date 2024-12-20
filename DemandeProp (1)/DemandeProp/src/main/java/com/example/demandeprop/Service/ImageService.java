package com.example.demandeprop.Service;

import com.example.demandeprop.Data.Admin;
import com.example.demandeprop.Data.Hebergement;
import com.example.demandeprop.Data.Image;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "IMAGE-SERVICE")
public interface ImageService {

    @GetMapping("Image/{id}")
    public Image getbyid(@PathVariable Long id);


    @PostMapping("/Image")
    public Image saveImage(Image image);

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Image uploadImage(@RequestParam("image") MultipartFile file);

    @GetMapping("/display/{id}")
    byte[] getImageById(@PathVariable Long id);
}
