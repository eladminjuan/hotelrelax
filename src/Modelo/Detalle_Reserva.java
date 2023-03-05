/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author jdgom
 */
public class Detalle_Reserva {
    private int idDetalle;
    private int idReserva;
    private int idHabitacion;
    private String persona;
    private Double precio;

    public Detalle_Reserva() {
    }

    public Detalle_Reserva(int idDetalle, int idReserva, int idHabitacion, String persona, Double precio) {
        this.idDetalle = idDetalle;
        this.idReserva = idReserva;
        this.idHabitacion = idHabitacion;
        this.persona = persona;
        this.precio = precio;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    
}
