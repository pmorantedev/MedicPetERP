package com.gruptd.medicPet.services;

import com.gruptd.medicPet.config.ImageUtils;
import com.gruptd.medicPet.dao.ImageDataDAO;
import com.gruptd.medicPet.models.ImageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

/**
 * Aquesta clase crea les funcions necessàries per crear o actualitzar una imatge
 * a la BBDD i per retornar una imatge de perfil segons el nom de l'usuari
 * identificat.
 *
 * @author pablomorante
 */
@Service
public class ImageDataService {

    @Autowired
    private ImageDataDAO imageDataDAO;

    @Transactional
    public String uploadImage(MultipartFile file, String username) throws IOException {
        Optional<ImageData> existingImageData = imageDataDAO.findByName(username);

        if (existingImageData.isPresent()) {
            // Actualiza una imatge de la BD
            ImageData imageData = existingImageData.get();
            imageData.setType(file.getContentType());
            imageData.setImageData(ImageUtils.compressImage(file.getBytes()));
            imageDataDAO.save(imageData);

            return "Imatge actualitzada satisfactoriament: " + file.getOriginalFilename();
        } else {
            // Crea una nova imatge
            imageDataDAO.save(ImageData.builder()
                    .name(username)
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .build());

            return "Imatge pujada satisfactoriament: " + file.getOriginalFilename();
        }
    }

    @Transactional
    public ImageData getInfoByImageByName(String name) {
        Optional<ImageData> dbImage = imageDataDAO.findByName(name);

        return ImageData.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageUtils.decompressImage(dbImage.get().getImageData())).build();

    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<ImageData> dbImage = imageDataDAO.findByName(name);
        byte[] image = ImageUtils.decompressImage(dbImage.get().getImageData());
        return image;
    }

}
