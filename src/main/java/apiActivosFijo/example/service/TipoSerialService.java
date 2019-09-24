package apiActivosFijo.example.service;

import apiActivosFijo.example.model.Tipo;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;

/**
 * The type Tipo serial service.
 */
@Stateless
public class TipoSerialService {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Gets all tipo serial.
     *
     * @param firstResult the first result
     * @param maxResult   the max result
     * @return the all tipo serial
     */
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

    /**
     * Gets tipo serial by code.
     *
     * @param code the code
     * @return the tipo serial by code
     */
    public Tipo getTipoSerialByCode(String code) {
        return entityManager.find(Tipo.class, code);
    }

    /**
     * Create tipo serial tipo.
     *
     * @param tipoSerial the tipo serial
     * @return the tipo
     */
    public Tipo createTipoSerial(Tipo tipoSerial) {
        entityManager.persist(tipoSerial);
        return tipoSerial;
    }

    /**
     * Update tipo serial tipo.
     *
     * @param code       the code
     * @param tipoSerial the tipo serial
     * @return the tipo
     */
    public Tipo updateTipoSerial(String code, Tipo tipoSerial) {
        Tipo tipoSerialToUpdate = entityManager.find(Tipo.class, code);
        if (tipoSerial.getDescripcion() != null) {
            tipoSerialToUpdate.setDescripcion(tipoSerial.getDescripcion());
        }

        return entityManager.merge(tipoSerialToUpdate);
    }

    /**
     * Delete tipo serial tipo.
     *
     * @param code the code
     * @return the tipo
     */
    public Tipo deleteTipoSerial(String code) {
        Tipo tipoSerial = entityManager.find(Tipo.class, code);
        entityManager.remove(tipoSerial);
        return tipoSerial;
    }
}
