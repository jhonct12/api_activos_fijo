package apiActivosFijo.example.service;

import apiActivosFijo.example.model.Tipo;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;

@Stateless
public class TipoSerialService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Tipo> getAllTipoSerial(Integer firstResult, Integer maxResult) {
        Query q = entityManager.createNamedQuery(Tipo.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }

    public Tipo getTipoSerialByCode(String code) {
        return entityManager.find(Tipo.class, code);
    }

    public Tipo createTipoSerial(Tipo tipoSerial) {
        entityManager.persist(tipoSerial);
        return tipoSerial;
    }

    public Tipo updateTipoSerial(String code, Tipo tipoSerial) {
        Tipo tipoSerialToUpdate = entityManager.find(Tipo.class, code);
        if (tipoSerial.getDescripcion() != null) {
            tipoSerialToUpdate.setDescripcion(tipoSerial.getDescripcion());
        }

        return entityManager.merge(tipoSerialToUpdate);
    }

    public Tipo deleteTipoSerial(String code) {
        Tipo tipoSerial = entityManager.find(Tipo.class, code);
        entityManager.remove(tipoSerial);
        return tipoSerial;
    }
}
