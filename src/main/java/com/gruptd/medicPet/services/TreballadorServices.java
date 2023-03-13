package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.TreballadorDAO;
import com.gruptd.medicPet.models.Treballador;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pmorante
 */
@Service
public class TreballadorServices {
    @Autowired
    private TreballadorDAO treballadorDao;
    
    @Transactional(readOnly = true)
    public List<Treballador> findAllTreballadors() {
        return (List<Treballador>) treballadorDao.findAll();
    }
    
    @Transactional
    public void saveTreballador(Treballador t) {
        treballadorDao.save(t);
    }
    
    @Transactional
    public void deleteTreballador(Treballador t) {
        treballadorDao.delete(t);
    }
    
    @Transactional(readOnly = true)
    public Treballador getTreballador(Treballador treballador) {
        return treballadorDao.findById(treballador.getId()).orElse(null);
    }
    
    @Transactional
    public void updateTreballador(Treballador t) {
        Treballador treballadorBD = getTreballador(t);
        if (treballadorBD != null) {
            treballadorBD.setAdreca(t.getAdreca());
            treballadorBD.setCarrec(t.getCarrec());
            treballadorBD.setCarrecAux(t.getCarrecAux());
            treballadorBD.setEmail(t.getEmail());
            treballadorBD.setNomComplet(t.getNomComplet());
            treballadorBD.setTelefon(t.getTelefon());
            
            saveTreballador(treballadorBD);
        } else {
            System.out.println("El treballador no existeix.");
        }
    }
}
