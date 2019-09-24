package apiActivosFijo.example.service;

import apiActivosFijo.example.model.EstadoActual;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;


/**
 * The type Estado actual service.
 */
@Stateless
public class EstadoActualService {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Gets all estado actual.
     *
     * @param firstResult the first result
     * @param maxResult   the max result
     * @return the all estado actual
     */
    public List<EstadoActual> getAllEstadoActual(Integer firstResult, Integer maxResult) {
        Query q = entityManager.createNamedQuery(EstadoActual.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }


    /**
     * Gets estado actual by codigo.
     *
     * @param codigo the codigo
     * @return the estado actual by codigo
     */
    public EstadoActual getEstadoActualByCodigo(String codigo) {
        return entityManager.find(EstadoActual.class, codigo);
    }

    /**
     * Create estado actual estado actual.
     *
     * @param estadoActual the estado actual
     * @return the estado actual
     */
    public EstadoActual createEstadoActual(EstadoActual estadoActual) {
        entityManager.persist(estadoActual);
        return estadoActual;
    }

    /**
     * Update estado actual estado actual.
     *
     * @param code         the code
     * @param estadoActual the estado actual
     * @return the estado actual
     */
    public EstadoActual updateEstadoActual(String code, EstadoActual estadoActual) {
        EstadoActual estadoActualToUpdate = entityManager.find(EstadoActual.class, code);

        if (estadoActual.getDescripcion() != null) {
            estadoActualToUpdate.setDescripcion(estadoActual.getDescripcion());
        }

        return entityManager.merge(estadoActual);

    }

    /**
     * Delete estado actual estado actual.
     *
     * @param codigo the codigo
     * @return the estado actual
     */
    public EstadoActual deleteEstadoActual(String codigo) {
        EstadoActual estadoActual = entityManager.find(EstadoActual.class, codigo);
        entityManager.remove(estadoActual);
        return estadoActual;
    }
}
