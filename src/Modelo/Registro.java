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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Registro {
    private String termino, documento;
    
    
    
     public void getDocumento(String tipo){
        documento = tipo;
    }
    @SuppressWarnings("SillyAssignment")
    public String mostrarDocu(){
        documento=documento;
        return (documento);
    }
    
    public void getTermino(String termi){
        termino = termi;
    }
    @SuppressWarnings("SillyAssignment")
    public String mostrarTermino(){
        termino=termino;
        return (termino);
    }
    
    public static String  fechaActual(){
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        return formato.format(fecha);
        
    }
    public static boolean correo(String correo){
        Pattern patron = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        Matcher comparador = patron.matcher(correo);
        return comparador.find();
    }
    
}

