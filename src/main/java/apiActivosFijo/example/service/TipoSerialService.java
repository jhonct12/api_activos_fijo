package apiActivosFijo.example.service;

import apiActivosFijo.example.model.TipoSerial;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;

@Stateless
public class TipoSerialService {

    @PersistenceContext
    EntityManager entityManager;

    public List<TipoSerial> getAllTipoSerial(Integer firstResult, Integer maxResult){
        Query q = entityManager.createNamedQuery(TipoSerial.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }
}
