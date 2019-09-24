package apiActivosFijo.example.service;

import apiActivosFijo.example.model.Areas;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;


@Stateless
public class AreasService {

    @PersistenceContext
    EntityManager entityManager;

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

    public Areas getAreasByCodigo(String codigo) {
        return entityManager.find(Areas.class, codigo);
    }

    public Areas createAreas(Areas areas) {
        entityManager.persist(areas);
        return areas;
    }

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

    public Areas deleteAreas(String codigo) {
        Areas areas = entityManager.find(Areas.class, codigo);
        entityManager.remove(areas);
        return areas;
    }

}
