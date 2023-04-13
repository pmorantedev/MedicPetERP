package com.gruptd.medicPet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import lombok.Data;
import java.util.List;

/**
 *
 * @author izan
 */
@Data
@Entity
@Table(name = "Visita")
public class Visita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnostic;
    private String medicacio;
    @NotEmpty
    private String data_visita;
    
    @OneToOne
    @JoinColumn(name = "treballador_id")
    private Treballador treballador_id;
    
    @OneToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "visita_has_tractament",
        joinColumns = {@JoinColumn(name = "visita_id")},
        inverseJoinColumns = {@JoinColumn(name = "tractament_id")}
    )   
    private List<Tractament> tractament_id;

}
