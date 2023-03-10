/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gruptd.medicPet.models;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author pmorante
 */
@Data
public class LiniaFacturaId implements Serializable {
    private int numeroDeLinea;
    private Factura factura;
}
