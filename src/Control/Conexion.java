/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jdgom
 */
public class Conexion {

    Connection cn;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            cn = DriverManager.getConnection("jdbc:mysql://localhost/hotelrelax", "root", "123456789");
            System.out.println("conectado");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return cn;
    }

    
}
