package apiActivosFijo.example.service;

import apiActivosFijo.example.model.Areas;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;


@Stateless
public class AreasService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Areas> getAllAreas(Integer firstResult, Integer maxResult){
        Query q = entityManager.createNamedQuery(Areas.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }
}
