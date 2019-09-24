package apiActivosFijo.example.service;


import apiActivosFijo.example.model.AsociacionPersonas;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


/**
 * The type Asociacion personas service.
 */
@Stateless
public class AsociacionPersonasService {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;


    /**
     * Gets all asociacion personas.
     *
     * @param firstResult the first result
     * @param maxResult   the max result
     * @return the all asociacion personas
     */
    public List<AsociacionPersonas> getAllAsociacionPersonas(Integer firstResult, Integer maxResult) {
        Query q = entityManager.createNamedQuery(AsociacionPersonas.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }

    /**
     * Gets asociacion personas by codigo.
     *
     * @param codigo the codigo
     * @return the asociacion personas by codigo
     */
    public AsociacionPersonas getAsociacionPersonasByCodigo(Long codigo) {
        return entityManager.find(AsociacionPersonas.class, codigo);
    }

    /**
     * Create asociacion personas asociacion personas.
     *
     * @param asociacionPersonas the asociacion personas
     * @return the asociacion personas
     */
    public AsociacionPersonas createAsociacionPersonas(AsociacionPersonas asociacionPersonas) {
        entityManager.persist(asociacionPersonas);
        return asociacionPersonas;
    }

    /**
     * Update asociacion personas asociacion personas.
     *
     * @param codigo             the codigo
     * @param asociacionPersonas the asociacion personas
     * @return the asociacion personas
     */
    public AsociacionPersonas updateAsociacionPersonas(Long codigo, AsociacionPersonas asociacionPersonas) {
        AsociacionPersonas asociacionPersonasToUpdate = entityManager.find(AsociacionPersonas.class, codigo);

        if (asociacionPersonas.getPersonas() != null) {
            asociacionPersonasToUpdate.setPersonas(asociacionPersonas.getPersonas());
        }

        if (asociacionPersonas.getActivos() != null) {
            asociacionPersonasToUpdate.setActivos(asociacionPersonas.getActivos());
        }

        if (asociacionPersonas.getDescripcion() != null) {
            asociacionPersonasToUpdate.setDescripcion(asociacionPersonas.getDescripcion());
        }

        if (asociacionPersonas.getFechaRetiro() != null) {
            asociacionPersonasToUpdate.setFechaRetiro(asociacionPersonas.getFechaRetiro());
        }

        if (asociacionPersonas.getFechaAsignacion() != null) {
            asociacionPersonasToUpdate.setFechaAsignacion(asociacionPersonas.getFechaAsignacion());
        }

        return entityManager.merge(asociacionPersonasToUpdate);
    }

    /**
     * Delet asociacion personas asociacion personas.
     *
     * @param codigo the codigo
     * @return the asociacion personas
     */
    public AsociacionPersonas deletAsociacionPersonas(Long codigo) {
        AsociacionPersonas asociacionPersonas = entityManager.find(AsociacionPersonas.class, codigo);
        entityManager.remove(asociacionPersonas);
        return asociacionPersonas;
    }
}
