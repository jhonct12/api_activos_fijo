package apiActivosFijo.example.service;

import apiActivosFijo.example.model.EstadoActual;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;


@Stateless
public class EstadoActualService {

    @PersistenceContext
    EntityManager entityManager;

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


    public EstadoActual getEstadoActualByCodigo(String codigo) {
        return entityManager.find(EstadoActual.class, codigo);
    }

    public EstadoActual createEstadoActual(EstadoActual estadoActual) {
        entityManager.persist(estadoActual);
        return estadoActual;
    }

    public EstadoActual updateEstadoActual(String code, EstadoActual estadoActual) {
        EstadoActual estadoActualToUpdate = entityManager.find(EstadoActual.class, code);

        if (estadoActual.getDescripcion() != null) {
            estadoActualToUpdate.setDescripcion(estadoActual.getDescripcion());
        }

        return entityManager.merge(estadoActual);

    }

    public EstadoActual deleteEstadoActual(String codigo) {
        EstadoActual estadoActual = entityManager.find(EstadoActual.class, codigo);
        entityManager.remove(estadoActual);
        return estadoActual;
    }
}
