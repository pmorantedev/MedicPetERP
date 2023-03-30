package com.gruptd.medicPet;

import com.gruptd.medicPet.models.Tractament;
import com.gruptd.medicPet.services.TractamentServices;
import jakarta.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class CRUDTests {

    @Autowired
    private TractamentServices tractamentServices;

    @Test
    public void contextLoads() {
        assertNotNull(tractamentServices);
    }

    @Test
    @DirtiesContext
    @Transactional
    @Rollback(true)
    public void testSaveAndFindAll() {
        List<Tractament> tractamentsInici = (List<Tractament>) tractamentServices.findAll();
        Tractament tractament = new Tractament();
        tractament.setNom("Tractament 1");
        tractament.setPreu(10.0f);

        tractamentServices.save(tractament);

        Iterable<Tractament> tractaments = tractamentServices.findAll();
        assertEquals(tractamentsInici.size()+1, ((List<Tractament>) tractaments).size());
        Tractament savedTractament = ((List<Tractament>) tractaments).get(tractamentsInici.size());
        assertEquals("Tractament 1", savedTractament.getNom());
        assertEquals(10.0, savedTractament.getPreu(), 0.0);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetOne() {
        Tractament tractament = new Tractament();
        tractament.setNom("Tractament 2");
        tractament.setPreu(20.0f);

        tractamentServices.save(tractament);

        Tractament retrievedTractament = tractamentServices.getOne(tractament.getId());

        assertNotNull(retrievedTractament);
        assertEquals("Tractament 2", retrievedTractament.getNom());
        assertEquals(20.0, retrievedTractament.getPreu(), 0.0);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdate() {
        Tractament tractament = new Tractament();
        tractament.setNom("Tractament 3");
        tractament.setPreu(30.0f);

        tractamentServices.save(tractament);

        tractament.setNom("Tractament 3 Updated");
        tractament.setPreu(40.0f);
        tractamentServices.update(tractament);

        Tractament updatedTractament = tractamentServices.getOne(tractament.getId());
        assertEquals("Tractament 3 Updated", updatedTractament.getNom());
        assertEquals(40.0, updatedTractament.getPreu(), 0.0);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDelete() {
        Tractament tractament = new Tractament();
        tractament.setNom("Tractament 4");
        tractament.setPreu(50.0f);

        tractamentServices.save(tractament);

        tractamentServices.delete(tractament);

        Tractament deletedTractament = tractamentServices.getOne(tractament.getId());
        assertNull(deletedTractament);
    }

}
