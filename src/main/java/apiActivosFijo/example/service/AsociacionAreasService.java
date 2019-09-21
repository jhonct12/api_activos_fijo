package apiActivosFijo.example.service;

import apiActivosFijo.example.model.AsociacionAreas;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Stateless
public class AsociacionAreasService {

    @PersistenceContext
    EntityManager entityManager;

    public List<AsociacionAreas> getAllAsociacionAreas(Integer firstResult, Integer maxResult){
        Query q = entityManager.createNamedQuery(AsociacionAreas.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }
}
