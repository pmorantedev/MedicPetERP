/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gruptd.medicPet.models;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author izan
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

    private String nom;
    private String especie;
    private String raca;
    private String data_naixement;
    private String sexe;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client_id;
}
