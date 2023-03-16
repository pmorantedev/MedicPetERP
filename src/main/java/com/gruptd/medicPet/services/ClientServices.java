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
    public Client getOne(Client client) {
        return clientDao.findById(client.getIdclient()).orElse(null);
    }

    @Transactional
    @Override
    public void update(Client c) {
        Client clientBD = getOne(c);
        if (clientBD != null) {
            clientBD.setEmail(c.getEmail());

            save(clientBD);
        } else {
            System.out.println("El client no existeix.");
        }
    }
}
