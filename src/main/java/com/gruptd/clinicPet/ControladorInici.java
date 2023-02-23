/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gruptd.clinicPet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mllanas
 */
//sobre @Slf4j: https://stackabuse.com/guide-to-logging-in-spring-boot/
/*Anotació @RestController fa refèrencia a la classe RestController que accepta les
peticions HTTP
*que es fan des del navegador.
*/
@RestController
@Slf4j
public class ControladorInici {
/*Anotació @GetMapping indica al sistem que el tipus de petició que farem és de
tipus GET.
*Per paràmetre passarem la ruta on es troba el fitxer al qual li fem la
petició, que en aquest
*cas és aquest fitxer i es troba en el mateix lloc en que es troba el fitxer
que executa l'aplicació.
*/
@GetMapping("/")
public String inici(){
log.info("Executant el controlador d'inici"); //Afegeix al log el missatge passat com a paràmetre.
return "<h1>Aquest és l'inici del primer projecte Spring Boot</h1>";
//Retorna la cadena que es mostrarà per pantalla
}
@GetMapping("/prova")
public String prova(){
log.info("Executant prova");
return "<h2>execució de la prova</h2>";
}
int valor = 0;
@GetMapping("/provaIncrement")
public String provaIncrement(){
valor++;
log.info("Executant provaIncrement");
return "<h2>valor:" + valor + "</h2>";
}
}

