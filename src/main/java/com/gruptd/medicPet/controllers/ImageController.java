package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.services.ImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * Controlador per gestionar l'imatge de perfil de l'ususari
 * 
 * @author pablomorante
 */
@RestController
public class ImageController {

    @Autowired
    public ImageDataService imageDataService;

    @PostMapping("/medicpet/perfil/guardarImatge")
    public RedirectView uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String response = imageDataService.uploadImage(file, username);

        RedirectView redirectView = new RedirectView("/medicpet/perfil?imatge=true");
        redirectView.setContextRelative(true);
        return redirectView;
    }

    @GetMapping("/image/{name}")
    public byte[] getImageByName(@PathVariable("name") String name) throws IOException {
        try {
            byte[] image = imageDataService.getImage(name);
            return image;
        } catch (java.util.NoSuchElementException ex) {
            Resource resource = new ClassPathResource("static/avatar1-freepik.jpg");
            return resource.getContentAsByteArray();
        }
    }

}
