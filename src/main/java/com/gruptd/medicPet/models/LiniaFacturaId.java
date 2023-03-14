package com.gruptd.medicPet.models;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author pmorante
 */
@Data
public class LiniaFacturaId implements Serializable {
    private int numeroDeLinea;
    private Factura factura;
}
