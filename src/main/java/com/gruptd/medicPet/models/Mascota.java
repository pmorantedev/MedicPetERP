package com.gruptd.medicPet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author izan, txell
 */
@Data
@Entity
@Table(name = "Mascota")
public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_mascota;

    @NotEmpty
    private String nom;
    @NotEmpty
    private String especie;
    @NotEmpty
    private String raca;
    @NotEmpty
    private String data_naixement;
    @NotEmpty
    private String sexe;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client_id;
    
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    private List<Visita> visites;
}
