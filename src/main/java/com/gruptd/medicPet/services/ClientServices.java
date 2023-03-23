package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.ClientDAO;
import com.gruptd.medicPet.models.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Aquesta clase crea totes les funcions del CRUD amb la BBDD sobre la clase
 * Client. S'ha d'importar i es pot utilitzar per fer peticions i modificacions
 * a la BBDD a la taula de Client
 *
 *
 * @author izan
 */
@Service
public class ClientServices implements ServicesInterface<Client> {

    @Autowired
    private ClientDAO clientDao;

    @Transactional(readOnly = true)
    @Override
    public Iterable<Client> findAll() {
        return clientDao.findAll();
    }

    @Transactional
    @Override
    public void save(Client c) {
        clientDao.save(c);
    }

    @Transactional
    @Override
    public void delete(Client c) {
        clientDao.delete(c);
    }

    @Transactional(readOnly = true)
    @Override
    public Client getOne(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void update(Client c) {
        Client clientBD = getOne(c.getIdclient());
        if (clientBD != null) {
            clientBD.setNomComplert(c.getNomComplert());
            clientBD.setDni(c.getDni());
            clientBD.setTelefon(c.getTelefon());
            clientBD.setEmail(c.getEmail());
            clientBD.setAdreca(c.getAdreca());

            save(clientBD);
        } else {
            System.out.println("El client no existeix.");
        }
    }
}
