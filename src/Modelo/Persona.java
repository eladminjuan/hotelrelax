
package Modelo;

import java.sql.Date;

/**
 *
 * @author jdgom
 */
public class Persona {
    private int idPersona;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String fNacimiento;
    private String tiDocumento;
    private String nuDocumento;
    private String direccion;
    private String telefono;
    private String email;
    private Date fecharegistro;

    public Persona() {
    }

    public Persona(int idPersona, String nombre, String aPaterno, String aMaterno, String fNacimiento, String tiDocumento, String nuDocumento, String direccion, String telefono, String email, Date fecharegistro) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.fNacimiento = fNacimiento;
        this.tiDocumento = tiDocumento;
        this.nuDocumento = nuDocumento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fecharegistro = fecharegistro;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public String getTiDocumento() {
        return tiDocumento;
    }

    public void setTiDocumento(String tiDocumento) {
        this.tiDocumento = tiDocumento;
    }

    public String getNuDocumento() {
        return nuDocumento;
    }

    public void setNuDocumento(String nuDocumento) {
        this.nuDocumento = nuDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

   
    
    
    
}
