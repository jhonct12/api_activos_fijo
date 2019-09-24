package apiActivosFijo.example.service;

import apiActivosFijo.example.model.Color;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;


/**
 * The type Color service.
 */
@Stateless
public class ColorService {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Gets all color.
     *
     * @param firstResult the first result
     * @param maxResult   the max result
     * @return the all color
     */
    public List<Color> getAllColor(Integer firstResult, Integer maxResult) {
        Query q = entityManager.createNamedQuery(Color.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }

    /**
     * Gets color by codigo.
     *
     * @param codigo the codigo
     * @return the color by codigo
     */
    public Color getColorByCodigo(String codigo) {
        return entityManager.find(Color.class, codigo);
    }

    /**
     * Create color color.
     *
     * @param color the color
     * @return the color
     */
    public Color createColor(Color color) {
        entityManager.persist(color);
        return color;
    }

    /**
     * Update color color.
     *
     * @param code  the code
     * @param color the color
     * @return the color
     */
    public Color updateColor(String code, Color color) {
        Color colorToUpdate = entityManager.find(Color.class, code);

        if (color.getDescripcion() != null) {
            colorToUpdate.setDescripcion(color.getDescripcion());
        }

        return entityManager.merge(colorToUpdate);

    }

    /**
     * Delete color color.
     *
     * @param codigo the codigo
     * @return the color
     */
    public Color deleteColor(String codigo) {
        Color color = entityManager.find(Color.class, codigo);
        entityManager.remove(color);
        return color;
    }
}
