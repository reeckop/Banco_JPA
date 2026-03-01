package com.mycompany.bancojpa.entity;

import com.empresa.modelo.Estatus;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 *
 * @author Ricardo
 */
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Double salario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estatus estatus;

    @Column(nullable = false)
    private LocalDate fechaContratacion;

    public Empleado() {}

    public Empleado(String nombre, String email, Double salario,
                    Estatus estatus, LocalDate fechaContratacion) {
        if (salario <= 0) {
            throw new IllegalArgumentException("El salario debe ser mayor a 0");
        }
        this.nombre = nombre;
        this.email = email;
        this.salario = salario;
        this.estatus = estatus;
        this.fechaContratacion = fechaContratacion;
    }

    public Long getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) {
        if (salario <= 0) {
            throw new IllegalArgumentException("El salario debe ser mayor a 0");
        }
        this.salario = salario;
    }

    public Estatus getEstatus() { return estatus; }
    public void setEstatus(Estatus estatus) { this.estatus = estatus; }

    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", salario=" + salario +
                ", estatus=" + estatus +
                ", fechaContratacion=" + fechaContratacion +
                '}';
    }
}