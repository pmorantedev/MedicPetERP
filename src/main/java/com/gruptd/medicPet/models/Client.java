/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gruptd.medicPet.models;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 *
 * @author izan
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
    @Size(max = 14)
    private String telefon;
    @NotEmpty
    private String email;
    @NotEmpty
    private String adreca;

}
