package com.gruptd.medicPet.dao;

import com.gruptd.medicPet.models.ImageData;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pablomorante
 */
public interface ImageDataDAO extends CrudRepository<ImageData, Long> {

    Optional<ImageData> findByName(String name);
}
