
package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.ClientDAO;
import com.gruptd.medicPet.models.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

///**
// *
// * @author izan
// */
@Service
public class ClientServices {

    @Autowired
    private ClientDAO clientDao;

    @Transactional(readOnly = true)
    public List<Client> findAllClients() {
        return (List<Client>) clientDao.findAll();
    }

    @Transactional
    public void saveClients(Client c) {
        clientDao.save(c);
    }

    @Transactional
    public void deleteClients(Client c) {
        clientDao.delete(c);
    }

    @Transactional(readOnly = true)
    public Client getClients(Client client) {
        return clientDao.findById(client.getIdclient()).orElse(null);
    }

    @Transactional
    public void updateTreballador(Client c) {
        Client clientBD = getClients(c);
        if (clientBD != null) {
            clientBD.setEmail(c.getEmail());

            saveClients(clientBD);
        } else {
            System.out.println("El client no existeix.");
        }
    }
}
