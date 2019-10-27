package Manager;

import Entities.Marcas;
import Manager.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.table.DefaultTableModel;

public class MarcasJpaController implements Serializable {

    public MarcasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Marcas marcas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(marcas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Marcas marcas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            marcas = em.merge(marcas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = marcas.getIdMarca();
                if (findMarcas(id) == null) {
                    throw new NonexistentEntityException("The marcas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marcas marcas;
            try {
                marcas = em.getReference(Marcas.class, id);
                marcas.getIdMarca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The marcas with id " + id + " no longer exists.", enfe);
            }
            em.remove(marcas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Marcas> findMarcasEntities() {
        return findMarcasEntities(true, -1, -1);
    }

    public List<Marcas> findMarcasEntities(int maxResults, int firstResult) {
        return findMarcasEntities(false, maxResults, firstResult);
    }

    private List<Marcas> findMarcasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Marcas.class));
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

    public Marcas findMarcas(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Marcas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarcasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Marcas> rt = cq.from(Marcas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public DefaultTableModel VerMarcas(){
        DefaultTableModel tblModelo=(DefaultTableModel) this.findMarcasEntities();
        return tblModelo;
    }
    
}
