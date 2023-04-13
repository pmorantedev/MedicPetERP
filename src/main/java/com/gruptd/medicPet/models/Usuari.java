package com.gruptd.medicPet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author izan
 */
@Data
@Entity
@Table(name = "usuari")
public class Usuari implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @NotEmpty
    private String nom;
    
    private String contrasenya;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol_id;

}
