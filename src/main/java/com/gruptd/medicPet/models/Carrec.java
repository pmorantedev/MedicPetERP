package com.gruptd.medicPet.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author pmorante
 */
@Data
@Entity
@Table(name = "Carrec")
public class Carrec implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    
//    @OneToMany(mappedBy="carrec")
//    private List<Treballador> treballadors;
}
