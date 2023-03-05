/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author jdgom
 */
public class Reserva {
    private int idreserva;
    private int idHabitacion;
    private int idCliente;
    private int idEmpleado;
    private String tipoReserva;
    private String persona;
    private String descripcion;
    private Date fechaReserva;
    private String horaReserva;
    private Date fechaIngreso;
    private String horaIngreso;
    private Date fechaSalida;
    private String horaSalida;
    private Double costoAlojamiento;
    private String estado;
    private Double valorX;

    public Reserva() {
    }

    public Reserva(int idreserva, int idHabitacion, int idCliente, int idEmpleado, String tipoReserva, String persona, String descripcion, Date fechaReserva, String horaReserva, Date fechaIngreso, String horaIngreso, Date fechaSalida, String horaSalida, Double costoAlojamiento, String estado, Double valorX) {
        this.idreserva = idreserva;
        this.idHabitacion = idHabitacion;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.tipoReserva = tipoReserva;
        this.persona = persona;
        this.descripcion = descripcion;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.fechaIngreso = fechaIngreso;
        this.horaIngreso = horaIngreso;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.costoAlojamiento = costoAlojamiento;
        this.estado = estado;
        this.valorX = valorX;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Double getCostoAlojamiento() {
        return costoAlojamiento;
    }

    public void setCostoAlojamiento(Double costoAlojamiento) {
        this.costoAlojamiento = costoAlojamiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getValorX() {
        return valorX;
    }

    public void setValorX(Double valorX) {
        this.valorX = valorX;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

   

   
    
}
