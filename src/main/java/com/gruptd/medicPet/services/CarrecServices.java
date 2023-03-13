package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.CarrecDAO;
import com.gruptd.medicPet.models.Carrec;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author pmorante
 */
@Service
public class CarrecServices {
    
    @Autowired
    private CarrecDAO carrecDao;
    
    @Transactional(readOnly = true)
    public List<Carrec> findAllCarrecs() {
        return (List<Carrec>) carrecDao.findAll();
    }
    
//    @Transactional
//    public void saveCarrec(Carrec c) {
//        carrecDao.save(c);
//    }
//    
//    @Transactional
//    public void deleteCarrec(Carrec c) {
//        carrecDao.delete(c);
//    }
    
    @Transactional(readOnly = true)
    public Carrec getCarrec(Carrec c) {
        return carrecDao.findById(c.getId()).orElse(null);
    }
    
}
