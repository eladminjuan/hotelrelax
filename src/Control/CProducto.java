/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Habitacion;
import Modelo.IO;
import Modelo.Lista;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jdgom
 */
public class CProducto {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    
    private String sSQL = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Producto", "Descripción", "Unidad Medida", "Precio Venta"};

        String[] registro = new String[5];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from producto where concat(Nombre,' ',Unidad_Medida,' ',Precio_Venta) like '%" + buscar + "%' order by Cod_Producto desc";

        try {
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Cod_Producto");
                registro[1] = rs.getString("Nombre");
                registro[2] = rs.getString("Descripcion");
                registro[3] = rs.getString("Unidad_Medida");
                registro[4] = rs.getString("Precio_Venta");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            rs.close();
            return modelo;
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    

    public boolean insertar(Producto dts) {
        sSQL = "insert into producto (Nombre,Descripcion,Unidad_Medida,Precio_Venta)"
                + "values (?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidadMedida());
            pst.setDouble(4, dts.getPrecioVenta());

            int n = pst.executeUpdate();
            
            pst.close();

            if (n != 0) {
                return true;
            } else {
                return false;
            }
            

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(Producto dts) {
        sSQL = "update producto set Nombre=?,Descripcion=?,Unidad_Medida=?,Precio_Venta=?"
                + " where Cod_Producto=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidadMedida());
            pst.setDouble(4, dts.getPrecioVenta());

            pst.setInt(5, dts.getIdProducto());

            int n = pst.executeUpdate();
            
            pst.close();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(Producto dts) {
        sSQL = "delete from producto where Cod_Producto=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdProducto());

            int n = pst.executeUpdate();
            
            pst.close();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

}
