/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author jdgom
 */
public class Pago {
    private int idPago;
    private int idReserva;
    private String tipoComprobante;
    private String numeroComprobante;
    private Date fechaEmision;
    private String horaEmision;
    private Date fechaPago;
    private String horaPago;
    private Double impuesto;
    private Double totalPago;
    private Double totalPagado;
    private Double saldoActual;

    public Pago() {
    }

    public Pago(int idPago, int idReserva, String tipoComprobante, String numeroComprobante, Date fechaEmision, String horaEmision, Date fechaPago, String horaPago, Double impuesto, Double totalPago, Double totalPagado, Double saldoActual) {
        this.idPago = idPago;
        this.idReserva = idReserva;
        this.tipoComprobante = tipoComprobante;
        this.numeroComprobante = numeroComprobante;
        this.fechaEmision = fechaEmision;
        this.horaEmision = horaEmision;
        this.fechaPago = fechaPago;
        this.horaPago = horaPago;
        this.impuesto = impuesto;
        this.totalPago = totalPago;
        this.totalPagado = totalPagado;
        this.saldoActual = saldoActual;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(String horaEmision) {
        this.horaEmision = horaEmision;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getHoraPago() {
        return horaPago;
    }

    public void setHoraPago(String horaPago) {
        this.horaPago = horaPago;
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    public Double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public Double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(Double totalPagado) {
        this.totalPagado = totalPagado;
    }
    

   
    
    
    
}
