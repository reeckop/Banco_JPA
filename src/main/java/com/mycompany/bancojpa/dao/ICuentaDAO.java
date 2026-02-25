/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.bancojpa.dao;

import com.mycompany.bancojpa.entity.Cuenta;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author martinbl
 */
public interface ICuentaDAO {
    void guardar(EntityManager em, Cuenta cuenta);
    Cuenta buscar(EntityManager em,Long id);
    List<Cuenta> buscarTodas(EntityManager em);
    void actualizar(EntityManager em,Cuenta cuenta);
    void eliminar(EntityManager em,Cuenta cuenta);  // con instancia
    void eliminarPorId(EntityManager em,Long id);   // sin instancia
}
