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

    public AsociacionAreas getAsociacionAreasByCodigo(Long codigo) {
        return entityManager.find(AsociacionAreas.class, codigo);
    }

    public AsociacionAreas createAsociacionAreas(AsociacionAreas asociacionAreas) {
        entityManager.persist(asociacionAreas);
        return asociacionAreas;
    }

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

    public AsociacionAreas deletAsociacionAreas(Long codigo) {
        AsociacionAreas asociacionAreas = entityManager.find(AsociacionAreas.class, codigo);
        entityManager.remove(asociacionAreas);
        return asociacionAreas;
    }
}
