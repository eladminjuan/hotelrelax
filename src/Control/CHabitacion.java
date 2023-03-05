/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CHabitacion {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Número", "Piso", "Descripción", "Caracteristicas", "Precio", "Estado", "Tipo habitación"};

        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from habitacion where concat(Numero,' ',Piso,' ',Estado,' ',Tipo_Habitacion) like '%" + buscar + "%' order by Cod_Habitacion";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Cod_Habitacion");
                registro[1] = rs.getString("Numero");
                registro[2] = rs.getString("Piso");
                registro[3] = rs.getString("Descripcion");
                registro[4] = rs.getString("Caracteristicas");
                registro[5] = rs.getString("Precio_Diario");
                registro[6] = rs.getString("Estado");
                registro[7] = rs.getString("Tipo_Habitacion");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (SQLException e) {
            System.err.println("Error en el llenado de la tabla... "+e);
            JOptionPane.showMessageDialog(null, "Error en el llenado de la tabla");
            return null;
        }
    }

    public DefaultTableModel mostrarvista(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Número", "Piso", "Descripción", "Caracteristicas", "Precio", "Estado", "Tipo habitación"};

        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from habitacion where piso like '%" + buscar + "%' and estado='Disponible' order by idhabitacion";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idhabitacion");
                registro[1] = rs.getString("numero");
                registro[2] = rs.getString("piso");
                registro[3] = rs.getString("descripcion");
                registro[4] = rs.getString("caracteristicas");
                registro[5] = rs.getString("precio_diario");
                registro[6] = rs.getString("estado");
                registro[7] = rs.getString("tipo_habitacion");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(Habitacion dts) {
        sSQL = "insert into habitacion (Numero,Piso,Descripcion,Caracteristicas,Precio_Diario,Estado,Tipo_Habitacion)"
                + "values (?,?,?,?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecioDiario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipoHabitacion());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar... "+e);
            JOptionPane.showMessageDialog(null, "Error al guardar");
            return false;
        }
    }

    public boolean editar(Habitacion dts) {
        sSQL = "update habitacion set Numero=?,Piso=?,Descripcion=?,Caracteristicas=?,Precio_Diario=?,Estado=?,Tipo_Habitacion=?"
                + " where Cod_Habitacion=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecioDiario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipoHabitacion());
            pst.setInt(8, dts.getIdHabitacion());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar... "+e);
            JOptionPane.showMessageDialog(null, "Error al actualizar");
            return false;
        }
    }

    public boolean desocupar(Habitacion dts) {
        sSQL = "update habitacion set Estado='Disponible'"
                + " where Cod_Habitacion=?";
           //alt + 39

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdHabitacion());

            int n = pst.executeUpdate();

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
     public boolean desocuparAll(Habitacion dts) {
        sSQL = "UPDATE habitacion h INNER JOIN detalle_reserva d ON h.Cod_Habitacion=d.Cod_Habitacion"
                + " SET h.Estado='Disponible' WHERE d.Cod_Reserva=?";
           //alt + 39

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdHabitacion());

            int n = pst.executeUpdate();

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

    public boolean ocupar(Habitacion dts) {
        sSQL = "update habitacion set Estado='Ocupado'"
                + " where Cod_Habitacion=?";
           //alt + 39

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdHabitacion());

            int n = pst.executeUpdate();

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
    public boolean eliminar (Habitacion dts){
       sSQL="delete from habitacion where Cod_Habitacion=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdHabitacion());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
    
    public DefaultTableModel mostrarVista(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Número", "Piso", "Descripción", "Caracteristicas", "Precio", "Estado", "Tipo habitación"};

        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from habitacion where concat(Numero,' ',Piso,' ',Estado,' ',Tipo_Habitacion) like '%" + buscar + "%' and Estado='Disponible' order by Cod_Habitacion";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Cod_Habitacion");
                registro[1] = rs.getString("Numero");
                registro[2] = rs.getString("Piso");
                registro[3] = rs.getString("Descripcion");
                registro[4] = rs.getString("Caracteristicas");
                registro[5] = rs.getString("Precio_Diario");
                registro[6] = rs.getString("Estado");
                registro[7] = rs.getString("Tipo_Habitacion");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (SQLException e) {
            System.err.println("Error en el llenado de la tabla... "+e);
            JOptionPane.showMessageDialog(null, "Error en el llenado de la tabla");
            return null;
        }
    }

    

}
