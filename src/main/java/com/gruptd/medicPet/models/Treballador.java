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
@Table(name="Treballador")
public class Treballador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomComplet;
    private String telefon;
    private String email;
    private String adreca;
    private String carrecAux;
    @ManyToOne
    @JoinColumn(name = "carrec_id")
    private Carrec carrec;
}
