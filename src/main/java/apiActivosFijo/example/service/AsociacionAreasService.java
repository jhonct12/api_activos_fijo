package apiActivosFijo.example.service;

import apiActivosFijo.example.model.AsociacionAreas;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


/**
 * The type Asociacion areas service.
 */
@Stateless
public class AsociacionAreasService {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Gets all asociacion areas.
     *
     * @param firstResult the first result
     * @param maxResult   the max result
     * @return the all asociacion areas
     */
    public List<AsociacionAreas> getAllAsociacionAreas(Integer firstResult, Integer maxResult) {
        Query q = entityManager.createNamedQuery(AsociacionAreas.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }

    /**
     * Gets asociacion areas by codigo.
     *
     * @param codigo the codigo
     * @return the asociacion areas by codigo
     */
    public AsociacionAreas getAsociacionAreasByCodigo(Long codigo) {
        return entityManager.find(AsociacionAreas.class, codigo);
    }

    /**
     * Create asociacion areas asociacion areas.
     *
     * @param asociacionAreas the asociacion areas
     * @return the asociacion areas
     */
    public AsociacionAreas createAsociacionAreas(AsociacionAreas asociacionAreas) {
        entityManager.persist(asociacionAreas);
        return asociacionAreas;
    }

    /**
     * Update asociacion areas asociacion areas.
     *
     * @param codigo          the codigo
     * @param asociacionAreas the asociacion areas
     * @return the asociacion areas
     */
    public AsociacionAreas updateAsociacionAreas(Long codigo, AsociacionAreas asociacionAreas) {
        AsociacionAreas asociacionAreasToUpdate = entityManager.find(AsociacionAreas.class, codigo);

        if (asociacionAreas.getAreas() != null) {
            asociacionAreasToUpdate.setAreas(asociacionAreas.getAreas());
        }

        if (asociacionAreas.getActivos() != null) {
            asociacionAreasToUpdate.setActivos(asociacionAreas.getActivos());
        }

        if (asociacionAreas.getFechaAsignacion() != null) {
            asociacionAreasToUpdate.setFechaAsignacion(asociacionAreas.getFechaAsignacion());
        }

        if (asociacionAreas.getFechaRetiro() != null) {
            asociacionAreasToUpdate.setFechaRetiro(asociacionAreas.getFechaRetiro());
        }

        if (asociacionAreas.getDescripcion() != null) {
            asociacionAreasToUpdate.setDescripcion(asociacionAreas.getDescripcion());
        }
        return entityManager.merge(asociacionAreasToUpdate);
    }

    /**
     * Delet asociacion areas asociacion areas.
     *
     * @param codigo the codigo
     * @return the asociacion areas
     */
    public AsociacionAreas deletAsociacionAreas(Long codigo) {
        AsociacionAreas asociacionAreas = entityManager.find(AsociacionAreas.class, codigo);
        entityManager.remove(asociacionAreas);
        return asociacionAreas;
    }
}
