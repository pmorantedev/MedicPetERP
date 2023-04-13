package com.gruptd.medicPet.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 *
 * @author izan, txell
 */
@Data
@Entity
@Table(name = "Client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idclient;

    @NotEmpty
    private String nomComplert;
    @NotEmpty
    private String dni;
    @NotEmpty
    @Size(max = 15)
    private String telefon;
    @NotEmpty
    private String email;
    @NotEmpty
    private String adreca;
    
    @OneToMany(mappedBy = "client_id", cascade = CascadeType.ALL)
    private List<Mascota> mascotes;
}
