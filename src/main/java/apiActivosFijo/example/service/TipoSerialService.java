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

    public TipoSerial getTipoSerialByCode(String code){
        return entityManager.find(TipoSerial.class, code);
    }

    public TipoSerial createTipoSerial(TipoSerial tipoSerial){
        entityManager.persist(tipoSerial);
        return tipoSerial;
    }

    public TipoSerial updateTipoSerial(String code, TipoSerial tipoSerial){
        TipoSerial tipoSerialToUpdate = entityManager.find(TipoSerial.class, code);
        if (tipoSerial.getTipoSerial() != null){
            tipoSerialToUpdate.setTipoSerial(tipoSerial.getTipoSerial());
        }

        return entityManager.merge(tipoSerialToUpdate);
    }

    public TipoSerial deleteTipoSerial(String code){
        TipoSerial tipoSerial = entityManager.find(TipoSerial.class, code);
        entityManager.remove(tipoSerial);
        return tipoSerial;
    }
}
