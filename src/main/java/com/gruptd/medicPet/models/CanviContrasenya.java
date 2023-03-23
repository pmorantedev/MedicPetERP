package com.gruptd.medicPet.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author pablomorante
 */
@Data
public class CanviContrasenya {
    private String contraActual;
    private String contraNova;
    private String contraNovaConf;
}
