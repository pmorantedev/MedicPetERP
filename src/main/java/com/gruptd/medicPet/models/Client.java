/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gruptd.medicPet.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

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

    @Column()
    private String nomComplert;
    private String dni;
    private String telefon;
    private String email;
    private String adreca;
    
    @OneToMany(mappedBy = "client_id", cascade = CascadeType.ALL)
    private List<Mascota> mascotes;
}
