package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.dao.CarrecDAO;
import com.gruptd.medicPet.dao.TreballadorDAO;
import com.gruptd.medicPet.models.Carrec;
import com.gruptd.medicPet.models.Treballador;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class WorkersController {
    @Autowired
    private TreballadorDAO treballadorDao;
    
    @Autowired
    private CarrecDAO carrecDao;
    
    @GetMapping("/treballadors")
    public String principalTreballadors() {
        log.info("Executant el controlador de treballador");
        Iterable<Treballador> treballadors = treballadorDao.findAll();
        log.info(">>> Treballadors de la BBDD:");
        treballadors.forEach((t) -> {
            log.info(t.getNomComplet());
        });
        
        Iterable<Carrec> carrecs = carrecDao.findAll();
        log.info(">>> Carrcs de la BBDD:");
        carrecs.forEach((t) -> {
            log.info(t.getNom());
        });
        return "login";
    }
}
