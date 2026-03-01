package com.mycompany.bancojpa.dao;

import com.mycompany.bancojpa.entity.Empleado;
import com.mycompany.bancojpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class EmpleadoDAO {

    public void insertar(Empleado e) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } finally { em.close(); }
    }

    public void actualizar(Empleado e) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(e);
            em.getTransaction().commit();
        } finally { em.close(); }
    }

    public void eliminar(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Empleado e = em.find(Empleado.class, id);
            if (e != null) em.remove(e);
            em.getTransaction().commit();
        } finally { em.close(); }
    }

    public Empleado buscar(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally { em.close(); }
    }

    public List<Empleado> listar() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
        } finally { em.close(); }
    }

    public void aumentarSalario(Long id, Double porcentaje) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Empleado e = em.find(Empleado.class, id);
            if (e != null && porcentaje > 0) {
                Double nuevoSalario = e.getSalario() * (1 + (porcentaje / 100));
                e.setSalario(nuevoSalario);
                em.merge(e);
                em.getTransaction().commit();
                System.out.println("Salario actualizado con éxito.");
            } else {
                throw new RuntimeException("Empleado no encontrado o porcentaje inválido");
            }
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.out.println("Error: " + ex.getMessage());
        } finally { em.close(); }
    }
}