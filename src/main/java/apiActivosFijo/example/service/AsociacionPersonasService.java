package apiActivosFijo.example.service;


import apiActivosFijo.example.model.AsociacionPersonas;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Stateless
public class AsociacionPersonasService {

    @PersistenceContext
    EntityManager entityManager;


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

    public AsociacionPersonas getAsociacionPersonasByCodigo(Long codigo) {
        return entityManager.find(AsociacionPersonas.class, codigo);
    }

    public AsociacionPersonas createAsociacionPersonas(AsociacionPersonas asociacionPersonas) {
        entityManager.persist(asociacionPersonas);
        return asociacionPersonas;
    }

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

    public AsociacionPersonas deletAsociacionPersonas(Long codigo) {
        AsociacionPersonas asociacionPersonas = entityManager.find(AsociacionPersonas.class, codigo);
        entityManager.remove(asociacionPersonas);
        return asociacionPersonas;
    }
}
