package com.gruptd.medicPet.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

/**
 *
 * @author pmorante
 */
@Data
@Entity
@Table(name = "Factura")
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date dataExpedicio;
    private Date dataPagament;
    private String metodePagament;
    private Float total;
    private Float iva;
}
