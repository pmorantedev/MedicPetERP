/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gruptd.clinicPet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author mllanas
 */
@Controller
public class ControladorDinamic {
/*A l'anotació @GetMapping li passem per paràmetre l'URL del directori
controladorDinamic
*(localhost:8080/controladorDinamic), ja que volem que s'executi el mètode
iniciDinamic.
*/
@GetMapping("/paginaDinamica")
public String inici(){
return "iniciDinamic"; //Retorna la pàgina iniciDinamic
}
}

