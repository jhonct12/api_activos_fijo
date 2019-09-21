package apiActivosFijo.example.service;

import apiActivosFijo.example.model.Activos;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;

@Stateless
public class ActivosService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Activos> getAllActivos(Integer firstResult, Integer maxResult){
        Query q = entityManager.createNamedQuery(Activos.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }
}
