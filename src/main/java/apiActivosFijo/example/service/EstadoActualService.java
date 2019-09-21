package apiActivosFijo.example.service;

import apiActivosFijo.example.model.EstadoActual;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;


@Stateless
public class EstadoActualService {

    @PersistenceContext
    EntityManager entityManager;

    public List<EstadoActual> getAllEstadoActual(Integer firstResult, Integer maxResult){
        Query q = entityManager.createNamedQuery(EstadoActual.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }
}
