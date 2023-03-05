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
public class Cliente extends Persona {
    private String codCliente;
    private String login;
    private String password;
    private String estado;

    public Cliente() {
    }

    public Cliente(String codCliente, String login, String password, String estado) {
        this.codCliente = codCliente;
        this.login = login;
        this.password = password;
        this.estado = estado;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
