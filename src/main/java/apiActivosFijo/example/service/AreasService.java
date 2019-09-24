package apiActivosFijo.example.service;

import apiActivosFijo.example.model.Areas;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;


/**
 * The type Areas service.
 */
@Stateless
public class AreasService {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Gets all areas.
     *
     * @param firstResult the first result
     * @param maxResult   the max result
     * @return the all areas
     */
    public List<Areas> getAllAreas(Integer firstResult, Integer maxResult) {
        Query q = entityManager.createNamedQuery(Areas.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }

    /**
     * Gets areas by codigo.
     *
     * @param codigo the codigo
     * @return the areas by codigo
     */
    public Areas getAreasByCodigo(String codigo) {
        return entityManager.find(Areas.class, codigo);
    }

    /**
     * Create areas areas.
     *
     * @param areas the areas
     * @return the areas
     */
    public Areas createAreas(Areas areas) {
        entityManager.persist(areas);
        return areas;
    }

    /**
     * Update areas areas.
     *
     * @param code  the code
     * @param areas the areas
     * @return the areas
     */
    public Areas updateAreas(String code, Areas areas) {
        Areas areasToUpdate = entityManager.find(Areas.class, code);

        if (areas.getDescripcion() != null) {
            areasToUpdate.setDescripcion(areas.getDescripcion());
        }

        if (areas.getNombre() != null) {
            areasToUpdate.setNombre(areas.getNombre());
        }

        if (areas.getCiudad() != null) {
            areasToUpdate.setCiudad(areas.getCiudad());
        }

        return entityManager.merge(areasToUpdate);

    }

    /**
     * Delete areas areas.
     *
     * @param codigo the codigo
     * @return the areas
     */
    public Areas deleteAreas(String codigo) {
        Areas areas = entityManager.find(Areas.class, codigo);
        entityManager.remove(areas);
        return areas;
    }

}
