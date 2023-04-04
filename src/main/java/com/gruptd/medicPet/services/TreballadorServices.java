package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.TreballadorDAO;
import com.gruptd.medicPet.models.Carrec;
import com.gruptd.medicPet.models.Treballador;
import com.gruptd.medicPet.models.Usuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/* 
 * Aquesta clase crea totes les funcions del CRUD amb la BBDD sobre la clase
 * Treballador. S'ha d'importar i es pot utilitzar per fer peticions i modificacions
 * a la BBDD a la taula de Treballador *
 *
 * @author pmorante
 */
@Service
public class TreballadorServices implements ServicesInterface<Treballador> {

    @Autowired
    private TreballadorDAO treballadorDao;

    @Transactional(readOnly = true)
    @Override
    public Iterable<Treballador> findAll() {
        return treballadorDao.findAll();
    }

    @Transactional
    @Override
    public void save(Treballador t) {
        treballadorDao.save(t);
    }

    @Transactional
    @Override
    public void delete(Treballador t) {
        treballadorDao.delete(t);
    }

    @Transactional(readOnly = true)
    @Override
    public Treballador getOne(Long id) {
        return treballadorDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void update(Treballador t) {
        Treballador treballadorBD = getOne(t.getId());
        if (treballadorBD != null) {
            treballadorBD.setAdreca(t.getAdreca());
            //treballadorBD.setCarrec(t.getCarrec());
            treballadorBD.setCarrecAux(t.getCarrecAux());
            treballadorBD.setEmail(t.getEmail());
            treballadorBD.setNomComplet(t.getNomComplet());
            treballadorBD.setTelefon(t.getTelefon());

            save(treballadorBD);
        } else {
            System.out.println("El treballador no existeix.");
        }
    }
    
    @Transactional
    public Iterable<Treballador> getByCarrec(Carrec carrec) {
        Iterable<Treballador> treballadorBD = treballadorDao.findByCarrec(carrec);
        
        return treballadorBD;
    }
}
