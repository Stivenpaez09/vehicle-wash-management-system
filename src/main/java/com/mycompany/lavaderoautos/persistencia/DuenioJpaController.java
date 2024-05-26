/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lavaderoautos.persistencia;

import com.mycompany.lavaderoautos.logica.Duenio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.lavaderoautos.logica.Vehiculo;
import com.mycompany.lavaderoautos.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author USUARIO
 */
public class DuenioJpaController implements Serializable {

    public DuenioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public DuenioJpaController() {
        emf = Persistence.createEntityManagerFactory("LavaderoAutosPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Duenio duenio) {
        if (duenio.getListaVehiculos() == null) {
            duenio.setListaVehiculos(new ArrayList<Vehiculo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Vehiculo> attachedListaVehiculos = new ArrayList<Vehiculo>();
            for (Vehiculo listaVehiculosVehiculoToAttach : duenio.getListaVehiculos()) {
                listaVehiculosVehiculoToAttach = em.getReference(listaVehiculosVehiculoToAttach.getClass(), listaVehiculosVehiculoToAttach.getId());
                attachedListaVehiculos.add(listaVehiculosVehiculoToAttach);
            }
            duenio.setListaVehiculos(attachedListaVehiculos);
            em.persist(duenio);
            for (Vehiculo listaVehiculosVehiculo : duenio.getListaVehiculos()) {
                Duenio oldDuenioOfListaVehiculosVehiculo = listaVehiculosVehiculo.getDuenio();
                listaVehiculosVehiculo.setDuenio(duenio);
                listaVehiculosVehiculo = em.merge(listaVehiculosVehiculo);
                if (oldDuenioOfListaVehiculosVehiculo != null) {
                    oldDuenioOfListaVehiculosVehiculo.getListaVehiculos().remove(listaVehiculosVehiculo);
                    oldDuenioOfListaVehiculosVehiculo = em.merge(oldDuenioOfListaVehiculosVehiculo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Duenio duenio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Duenio persistentDuenio = em.find(Duenio.class, duenio.getId());
            List<Vehiculo> listaVehiculosOld = persistentDuenio.getListaVehiculos();
            List<Vehiculo> listaVehiculosNew = duenio.getListaVehiculos();
            List<Vehiculo> attachedListaVehiculosNew = new ArrayList<Vehiculo>();
            for (Vehiculo listaVehiculosNewVehiculoToAttach : listaVehiculosNew) {
                listaVehiculosNewVehiculoToAttach = em.getReference(listaVehiculosNewVehiculoToAttach.getClass(), listaVehiculosNewVehiculoToAttach.getId());
                attachedListaVehiculosNew.add(listaVehiculosNewVehiculoToAttach);
            }
            listaVehiculosNew = attachedListaVehiculosNew;
            duenio.setListaVehiculos(listaVehiculosNew);
            duenio = em.merge(duenio);
            for (Vehiculo listaVehiculosOldVehiculo : listaVehiculosOld) {
                if (!listaVehiculosNew.contains(listaVehiculosOldVehiculo)) {
                    listaVehiculosOldVehiculo.setDuenio(null);
                    listaVehiculosOldVehiculo = em.merge(listaVehiculosOldVehiculo);
                }
            }
            for (Vehiculo listaVehiculosNewVehiculo : listaVehiculosNew) {
                if (!listaVehiculosOld.contains(listaVehiculosNewVehiculo)) {
                    Duenio oldDuenioOfListaVehiculosNewVehiculo = listaVehiculosNewVehiculo.getDuenio();
                    listaVehiculosNewVehiculo.setDuenio(duenio);
                    listaVehiculosNewVehiculo = em.merge(listaVehiculosNewVehiculo);
                    if (oldDuenioOfListaVehiculosNewVehiculo != null && !oldDuenioOfListaVehiculosNewVehiculo.equals(duenio)) {
                        oldDuenioOfListaVehiculosNewVehiculo.getListaVehiculos().remove(listaVehiculosNewVehiculo);
                        oldDuenioOfListaVehiculosNewVehiculo = em.merge(oldDuenioOfListaVehiculosNewVehiculo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = duenio.getId();
                if (findDuenio(id) == null) {
                    throw new NonexistentEntityException("The duenio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Duenio duenio;
            try {
                duenio = em.getReference(Duenio.class, id);
                duenio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The duenio with id " + id + " no longer exists.", enfe);
            }
            List<Vehiculo> listaVehiculos = duenio.getListaVehiculos();
            for (Vehiculo listaVehiculosVehiculo : listaVehiculos) {
                listaVehiculosVehiculo.setDuenio(null);
                listaVehiculosVehiculo = em.merge(listaVehiculosVehiculo);
            }
            em.remove(duenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Duenio> findDuenioEntities() {
        return findDuenioEntities(true, -1, -1);
    }

    public List<Duenio> findDuenioEntities(int maxResults, int firstResult) {
        return findDuenioEntities(false, maxResults, firstResult);
    }

    private List<Duenio> findDuenioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Duenio.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Duenio findDuenio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Duenio.class, id);
        } finally {
            em.close();
        }
    }

    public int getDuenioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Duenio> rt = cq.from(Duenio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
