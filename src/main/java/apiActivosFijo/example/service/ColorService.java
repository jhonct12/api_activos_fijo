package apiActivosFijo.example.service;

import apiActivosFijo.example.model.Color;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;


@Stateless
public class ColorService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Color> getAllColor(Integer firstResult, Integer maxResult){
        Query q = entityManager.createNamedQuery(Color.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }

    public Color getColorByCode(String code){
        return entityManager.find(Color.class, code);
    }

    public Color createColor(Color color){
        entityManager.persist(color);
        return color;
    }

    public Color updateColor(String code, Color color){
        Color colorToUpdate = entityManager.find(Color.class, code);

        if (color.getDescripcion() != null){
            colorToUpdate.setDescripcion(color.getDescripcion());
        }

        return entityManager.merge(colorToUpdate);

    }

    public Color deleteColor(String code){
        Color color = entityManager.find(Color.class, code);
        entityManager.remove(color);
        return color;
    }
}
