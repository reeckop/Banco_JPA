/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancojpa.service;

import com.mycompany.bancojpa.dao.CuentaDAO;
import com.mycompany.bancojpa.dao.ICuentaDAO;
import com.mycompany.bancojpa.entity.Cuenta;
import com.mycompany.bancojpa.util.JpaUtil;
import jakarta.persistence.EntityManager;

/**
 *
 * @author martinbl
 */
public class BancoService {
    private final CuentaDAO cuentaDAO = new CuentaDAO();

    public void transferir(Long idOrigen, Long idDestino, Double monto) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Cuenta origen = cuentaDAO.buscar(em, idOrigen);
            Cuenta destino = cuentaDAO.buscar(em, idDestino);

            if (origen == null || destino == null)
                throw new RuntimeException("Cuenta inexistente");

            if (monto == null || monto <= 0)
                throw new RuntimeException("Monto inválido");

            if (origen.getSaldo() < monto)
                throw new RuntimeException("Fondos insuficientes");

            origen.setSaldo(origen.getSaldo() - monto);
            destino.setSaldo(destino.getSaldo() + monto);

            cuentaDAO.actualizar(em, origen);
            cuentaDAO.actualizar(em, destino);

            em.getTransaction().commit();
            System.out.println("Transferencia realizada con éxito.");

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Error en la transferencia: " + e.getMessage());

        } finally {
            em.close();
        }
    }
}
