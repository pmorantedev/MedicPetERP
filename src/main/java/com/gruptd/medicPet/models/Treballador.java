package com.gruptd.medicPet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    
    @NotEmpty
    private String nomComplet;
    
    @NotEmpty
    private String telefon;
    
    @NotEmpty
    private String email;
    
    @NotEmpty
    private String adreca;
    
    private String carrecAux;
    
    @ManyToOne
    @JoinColumn(name = "carrec_id")
    private Carrec carrec;
    
    @OneToOne
    @JoinColumn(name = "usuari_id")
    private Usuari usuari;
}
