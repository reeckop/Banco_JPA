/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancojpa.dao;

import com.mycompany.bancojpa.entity.Cuenta;
import com.mycompany.bancojpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author martinbl
 */
public class CuentaDAO implements ICuentaDAO {

    @Override
    public void guardar(EntityManager em, Cuenta cuenta) {
        em.persist(cuenta);
    }

    @Override
    public Cuenta buscar(EntityManager em, Long id) {
        return em.find(Cuenta.class, id);
    }

    @Override
    public List<Cuenta> buscarTodas(EntityManager em) {
        return em.createQuery("SELECT c FROM Cuenta c", Cuenta.class)
                 .getResultList();
    }

    @Override
    public void actualizar(EntityManager em, Cuenta cuenta) {
        em.merge(cuenta);
    }

    @Override
    public void eliminar(EntityManager em, Cuenta cuenta) {
        em.remove(em.contains(cuenta) ? cuenta : em.merge(cuenta));
    }

    @Override
    public void eliminarPorId(EntityManager em, Long id) {
        Cuenta cuenta = em.find(Cuenta.class, id);
        if (cuenta != null) {
            em.remove(cuenta);
        }
    }
}
