package apiActivosFijo.example.service;

import apiActivosFijo.example.model.Tipo;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;

/**
 * The type Tipo  service.
 */
@Stateless
public class TipoService {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Gets all tipo .
     *
     * @param firstResult the first result
     * @param maxResult   the max result
     * @return the all tipo
     */
    public List<Tipo> getAllTipo(Integer firstResult, Integer maxResult) {
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
     * Gets tipo  by code.
     *
     * @param code the code
     * @return the tipo  by code
     */
    public Tipo getTipoByCode(String code) {
        return entityManager.find(Tipo.class, code);
    }

    /**
     * Create tipo  tipo.
     *
     * @param tipo the tipo
     * @return the tipo
     */
    public Tipo createTipo(Tipo tipo) {
        entityManager.persist(tipo);
        return tipo;
    }

    /**
     * Update tipo  tipo.
     *
     * @param code       the code
     * @param tipo the tipo
     * @return the tipo
     */
    public Tipo updateTipo(String code, Tipo tipo) {
        Tipo tipoToUpdate = entityManager.find(Tipo.class, code);
        if (tipo.getDescripcion() != null) {
            tipoToUpdate.setDescripcion(tipo.getDescripcion());
        }

        return entityManager.merge(tipoToUpdate);
    }

    /**
     * Delete tipo  tipo.
     *
     * @param code the code
     * @return the tipo
     */
    public Tipo deleteTipo(String code) {
        Tipo tipo = entityManager.find(Tipo.class, code);
        entityManager.remove(tipo);
        return tipo;
    }
}
