package apiActivosFijo.example.service;


import apiActivosFijo.example.model.Personas;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;

/**
 * The type Personas service.
 */
@Stateless
public class PersonasService {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Gets all personas.
     *
     * @param firstResult the first result
     * @param maxResult   the max result
     * @return the all personas
     */
    public List<Personas> getAllPersonas(Integer firstResult, Integer maxResult) {
        Query q = entityManager.createNamedQuery(Personas.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }

    /**
     * Gets personas by documento.
     *
     * @param documento the documento
     * @return the personas by documento
     */
    public Personas getPersonasByDocumento(String documento) {
        return entityManager.find(Personas.class, documento);
    }

    /**
     * Create personas personas.
     *
     * @param personas the personas
     * @return the personas
     */
    public Personas createPersonas(Personas personas) {
        entityManager.persist(personas);
        return personas;
    }

    /**
     * Update personas personas.
     *
     * @param documento the documento
     * @param personas  the personas
     * @return the personas
     */
    public Personas updatePersonas(String documento, Personas personas) {
        Personas personasToUpdate = entityManager.find(Personas.class, documento);

        if (personas.getNombres() != null) {
            personasToUpdate.setNombres(personas.getNombres());
        }

        if (personas.getApellidos() != null) {
            personasToUpdate.setApellidos(personas.getApellidos());
        }

        if (personas.getCorreo() != null) {
            personasToUpdate.setCorreo(personas.getCorreo());
        }

        if (personas.getDireccion() != null) {
            personasToUpdate.setDireccion(personas.getDireccion());
        }

        if (personas.getTelefono() != null) {
            personasToUpdate.setTelefono(personas.getTelefono());
        }

        return entityManager.merge(personasToUpdate);
    }

    /**
     * Delete personas personas.
     *
     * @param documento the documento
     * @return the personas
     */
    public Personas deletePersonas(String documento) {
        Personas personas = entityManager.find(Personas.class, documento);
        entityManager.remove(personas);
        return personas;
    }
}
