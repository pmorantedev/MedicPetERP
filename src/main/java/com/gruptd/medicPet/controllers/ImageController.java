package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.ImageData;
import com.gruptd.medicPet.services.ImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author pablomorante
 */
@RestController
public class ImageController {

    @Autowired
    public ImageDataService imageDataService;

    @PostMapping("/medicpet/perfil/guardarImatge")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String response = imageDataService.uploadImage(file, username);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("image/info/{name}")
    public ResponseEntity<?> getImageInfoByName(@PathVariable("name") String name) {
        ImageData image = imageDataService.getInfoByImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping("/image/{name}")
    public ResponseEntity<?> getImageByName(@PathVariable("name") String name) {
        byte[] image = imageDataService.getImage(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

}
