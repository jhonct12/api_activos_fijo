package apiActivosFijo.example.service;

import apiActivosFijo.example.model.Activos;
import apiActivosFijo.example.model.Tipo;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;

@Stateless
public class ActivosService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Activos> getAllActivos(Integer firstResult, Integer maxResult) {
        Query q = entityManager.createNamedQuery(Activos.FIND_ALL);
        if (firstResult != null) {
            q.setFirstResult(firstResult - 1);
        }

        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        return q.getResultList();
    }

    public Activos getActivosByCodigo(String codigo) {
        return entityManager.find(Activos.class, codigo);
    }

    public List<Activos> getActivosByTipo(String codeTipo) {
        Tipo tipo = new Tipo();
        tipo.setCodigo(codeTipo);
        Query q = entityManager.createNamedQuery(Activos.FIND_TIPO).setParameter("tipo", tipo);
        return q.getResultList();
    }


    public List<Activos> getActivosByFechaCompra(Long fechaCompra) {
        Query q = entityManager.createNamedQuery(Activos.FIND_FECHA_COMPRA).setParameter("fechaCompra", fechaCompra);
        return q.getResultList();
    }

    public List<Activos> getActivosBySerial(String serial) {
        Query q = entityManager.createNamedQuery(Activos.FIND_SERIAL).setParameter("serial", serial);
        return q.getResultList();
    }

    public Activos createActivos(Activos activos) {
        entityManager.persist(activos);
        return activos;
    }

    public Activos updateActivos(String codigo, Activos activos) {
        Activos activosToUpdate = entityManager.find(Activos.class, codigo);

        if (activos.getNombre() != null) {
            activosToUpdate.setNombre(activos.getNombre());
        }

        if (activos.getDescripcion() != null) {
            activosToUpdate.setDescripcion(activos.getDescripcion());
        }

        if (activos.getTipo() != null) {
            activosToUpdate.setTipo(activos.getTipo());
        }

        if (activos.getNumeroInternoInventario() != null) {
            activosToUpdate.setNumeroInternoInventario(activos.getNumeroInternoInventario());
        }

        if (activos.getPeso() != null) {
            activosToUpdate.setPeso(activos.getPeso());
        }

        if (activos.getAlto() != null) {
            activosToUpdate.setAlto(activos.getAlto());
        }

        if (activos.getLargo() != null) {
            activosToUpdate.setLargo(activos.getLargo());
        }

        if (activos.getSerial() != null){
            activosToUpdate.setSerial(activos.getSerial());
        }

        if (activos.getValorCompra() != null) {
            activosToUpdate.setValorCompra(activos.getValorCompra());
        }

        if (activos.getFechaCompra() != null) {
            activosToUpdate.setFechaCompra(activos.getFechaCompra());
        }

        if (activos.getFechaBaja() != null) {
            activosToUpdate.setFechaBaja(activos.getFechaBaja());
        }
        return entityManager.merge(activosToUpdate);
    }

    public Activos deletActivos(String codigo) {
        Activos activos = entityManager.find(Activos.class, codigo);
        entityManager.remove(activos);
        return activos;
    }
}
