package com.mycompany.bancojpa;

import com.mycompany.bancojpa.dao.EmpleadoDAO;
import com.mycompany.bancojpa.entity.Empleado;
import java.time.LocalDate;

public class BancoJPA {
    
    public static void main(String[] args) {
        EmpleadoDAO dao = new EmpleadoDAO();

        Empleado emp = new Empleado();
        emp.setNombre("Ricardo");
        emp.setEmail("ricardo@itson.com");
        emp.setSalario(1500.0);
        emp.setEstatus(Empleado.EstatusEmpleado.ACTIVO);
        emp.setFechaContratacion(LocalDate.now());
        dao.insertar(emp);

        System.out.println("Lista:");
        dao.listar().forEach(e -> System.out.println(e.getNombre() + " - $" + e.getSalario()));

        dao.aumentarSalario(emp.getId(), 10.0);

        System.out.println("Aumento:");
        Empleado actualizado = dao.buscar(emp.getId());
        System.out.println(actualizado.getNombre() + " - Nuevo Salario: $" + actualizado.getSalario());
    }
}
