package com.gruptd.medicPet.models;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author pmorante
 */
@Data
@Entity
@Table(name = "LiniaFactura")
@IdClass(LiniaFacturaId.class)
public class LiniaFactura implements Serializable {
    private static final long serialVersionUID = 1L;

    private int quantitat;
    private Float total;
    
    @Id
    private int numeroDeLinea;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "tractament_id")
    private Tractament tractament;
}
