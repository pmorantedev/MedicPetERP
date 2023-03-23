package com.gruptd.medicPet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author pmorante
 */
@Data
@Entity
@Table(name="Tractament")
public class Tractament implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String nom;
    @Positive
    private Float preu;
}
