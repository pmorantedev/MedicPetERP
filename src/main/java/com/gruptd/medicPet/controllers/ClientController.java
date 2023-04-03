package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Client;
import com.gruptd.medicPet.models.Mascota;
import com.gruptd.medicPet.models.Usuari;
import com.gruptd.medicPet.services.ClientServices;
import com.gruptd.medicPet.services.MascotaServices;
import com.gruptd.medicPet.services.UsuariServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class ClientController {

    @Autowired
    private UsuariServices usuariService;
    
    @Autowired
    private ClientServices clientService;

    @Autowired
    private MascotaServices mascotaService;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/medicpet/clients")                                            // URL 'READ' clients (LIST)
    public String principalClients(Model model, 
            @Param("paraulaClau") String paraulaClau,
            @Param("nomRegistreEliminat") String nomRegistreEliminat,
            @RequestParam(name = "registreEliminat", required = false) Boolean registreEliminat) {
        
        log.info("Executant controlador clients: LLISTAT");
        
        // Mostra alerta per informar a l'usuari que un client s'ha eliminat
        if (registreEliminat != null) {
            log.info("[info] Mostrar alerta a l'usuari: CLIENT ELIMINAT");
            model.addAttribute("nomRegistreEliminat", nomRegistreEliminat);
        } 
        
        // Definir/Inicialitzar variables necessàries per la vista
        Iterable<Client> clients;
        Iterable<Mascota> mascotes = mascotaService.findAll();
        
        // Recuperar el nom de l'usuari actual
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Recuperar l'objecte Usuari corresponent a l'usuari actual
        Usuari usuari = usuariService.getByUsername(username);
        // Accedir a l'atribut 'Nom' per mostrar-lo al header
        String nomUsuariComplert = usuari.getNom();
        
        // Codi pel cercador
        if (paraulaClau != null) {
            String sql = "SELECT * FROM client c WHERE CONCAT(c.idclient, c.nom_complert, c.dni, c.telefon, c.email, c.adreca) LIKE '%" + paraulaClau + "%'";
            clients = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Client.class));
        } else {
            clients = clientService.findAll();
        }        

        // Passar variables a la vista
        model.addAttribute("clients", clients);
        model.addAttribute("mascotes", mascotes);
        model.addAttribute("userName", username);
        model.addAttribute("nomUsuariComplert", nomUsuariComplert);
        model.addAttribute("pagina", "Clients");

        return "clientsMain";
    }

    @GetMapping("/medicpet/clients/fitxa")                                      // URL mostrar fitxa client (FORM)
    public String crearClient(Client client, Model model) {

        // Recuperar el nom de l'usuari actual
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Recuperar l'objecte Usuari corresponent a l'usuari actual
        Usuari usuari = usuariService.getByUsername(username);
        // Accedir a l'atribut 'Nom' per mostrar-lo al header
        String nomUsuariComplert = usuari.getNom();
        model.addAttribute("userName", username);
        model.addAttribute("nomUsuariComplert", nomUsuariComplert);
        model.addAttribute("pagina", "Clients");

        log.info("Executant controlador clients: FITXA NOU CLIENT...");

        return "clientForm";
    }

    @PostMapping("/medicpet/clients/desar")                                     // URL 'CREATE' / 'UPDATE' client (FORM)
    public String desarClient(@Valid Client client, Errors errors) {

        log.info("Executant controlador clients: VALIDANT DADES CLIENT...");
        if (errors.hasErrors()) { // Si s'han produït errors...
            return "clientForm"; // Mantenim l'usuari a la pàgina del formulari
        }

        if (client.getIdclient() == null) {
            clientService.save(client);
            log.info("Executant controlador clients: NOU CLIENT DESAT ( ID:" + client.getIdclient() + ", " + client.getNomComplert() + " )...");
        } else {
            clientService.update(client);
            log.info("Executant controlador clients: CLIENT ACTUALITZAT ( ID:" + client.getIdclient() + ", " + client.getNomComplert() + " )...");
        }

        return "redirect:/medicpet/clients";
    }

    @GetMapping("/medicpet/clients/fitxa/{idclient}")                           // URL 'EDIT' client (FORM) ****
    public String modificarClient(Client client, Model model, Mascota mascota,
            @Param("nomRegistreEliminat") String nomRegistreEliminat,
            @RequestParam(name = "registreEliminat", required = false) Boolean registreEliminat) {

        // Mostra alerta per informar a l'usuari que una mascota d'un client s'ha eliminat
        if (registreEliminat != null) {
            log.info("[info] Mostrar alerta a l'usuari: MASCOTA ELIMINADA");
            model.addAttribute("nomRegistreEliminat", nomRegistreEliminat);
        } 
        
        client = clientService.getOne(client.getIdclient());
        model.addAttribute("client", client);
        log.info("Executant controlador clients: MOSTRAR FITXA CLIENT EXISTENT ( ID:" + client.getIdclient() + ", " + client.getNomComplert() + " )...");

        // Recuperar el nom de l'usuari actual
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Recuperar l'objecte Usuari corresponent a l'usuari actual
        Usuari usuari = usuariService.getByUsername(username);
        // Accedir a l'atribut 'Nom' per mostrar-lo al header
        String nomUsuariComplert = usuari.getNom();
        model.addAttribute("userName", username);
        model.addAttribute("nomUsuariComplert", nomUsuariComplert);
        model.addAttribute("pagina", "Clients");

        return "clientForm";
    }

    @PostMapping("/medicpet/clients/eliminar/{idclient}")                       // URL 'DELETE' client (FORM)
    public String eliminar(Client client, RedirectAttributes redirectAtr) {

        // Recupero client per mostrar el nom per consola i passar-lo a la vista
        client = clientService.getOne(client.getIdclient());
        log.info("Executant controlador clients: CLIENT ELIMINAT ( ID:" + client.getIdclient() + ", " + client.getNomComplert() + " )...");
        redirectAtr.addAttribute("nomRegistreEliminat", client.getNomComplert());        
        
        // Executo l'acció d'eliminar
        clientService.delete(client);
        redirectAtr.addAttribute("registreEliminat", true);
        
        return "redirect:/medicpet/clients";
    }

}
