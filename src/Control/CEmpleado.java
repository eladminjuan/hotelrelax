/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Empleado;
import Modelo.IO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CEmpleado {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Nombre", "Apaterno", "Amaterno", "Fecha Nacimiento", "Doc", "Númeo Documento", "Dirección", "Teléfono", "Email", "Fecha Registro", "Sueldo", "Acceso", "Login", "Clave", "Estado"};

        String[] registro = new String[16];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.Cod_Persona,p.Nombre,p.A_Paterno,p.A_Materno,p.Fecha_Nacimiento,p.Tipo_Documento,p.Num_Documento,"
                + "p.Direccion,p.Telefono,p.Email,p.Fecha_Registro,e.Sueldo,e.Acceso,e.Login,e.Clave,e.Estado from persona p inner join empleado e "
                + "on p.Cod_Persona=e.Cod_Persona where concat(Num_Documento,' ',Nombre,' ',A_Paterno,' ',A_Materno,' ',Tipo_Documento,' ',Sueldo,' ',Acceso,' ',Estado) like '%"
                + buscar + "%' order by Cod_Persona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Cod_Persona");
                registro[1] = rs.getString("Nombre");
                registro[2] = rs.getString("A_Paterno");
                registro[3] = rs.getString("A_Materno");
                registro[4] = rs.getString("Fecha_Nacimiento");
                registro[5] = rs.getString("Tipo_Documento");
                registro[6] = rs.getString("Num_Documento");
                registro[7] = rs.getString("Direccion");
                registro[8] = rs.getString("Telefono");
                registro[9] = rs.getString("Email");
                registro[10] = rs.getString("Fecha_Registro");
                registro[11] = rs.getString("Sueldo");
                registro[12] = rs.getString("Acceso");
                registro[13] = rs.getString("Login");
                registro[14] = rs.getString("Clave");
                registro[15] = rs.getString("Estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            if (rs != null) {
                rs.close();
                IO.escribir("Desconectado");
            }
            st.close();
            rs.close();
            IO.escribir("Desconectado");
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(Empleado dts) {
        sSQL = "insert into persona (Nombre,A_Paterno,A_Materno,Fecha_Nacimiento,Tipo_Documento,Num_Documento,Direccion,Telefono,Email,Fecha_Registro)"
                + "values (?,?,?,?,?,?,?,?,?,?)";
        sSQL2 = "insert into empleado (Cod_Persona,Sueldo,Acceso,Login,Clave,Estado)"
                + "values ((select Cod_Persona from persona order by Cod_Persona desc limit 1),?,?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getaPaterno());
            pst.setString(3, dts.getaMaterno());
            pst.setString(4, dts.getfNacimiento());
            pst.setString(5, dts.getTiDocumento());
            pst.setString(6, dts.getNuDocumento());
            pst.setString(7, dts.getDireccion());
            pst.setString(8, dts.getTelefono());
            pst.setString(9, dts.getEmail());
            pst.setDate(10, dts.getFecharegistro());

            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();
                pst2.close();
                pst.close();
                IO.escribir("Desconectado");

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                pst2.close();
                pst.close();
                IO.escribir("Desconectado");
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(Empleado dts) {
        sSQL = "update persona set Nombre=?,A_Paterno=?,A_Materno=?,Fecha_Nacimiento=?,Tipo_Documento=?,Num_Documento=?,"
                + " Direccion=?,Telefono=?,Email=?,Fecha_Registro=? where Cod_Persona=?";

        sSQL2 = "update empleado set Sueldo=?,Acceso=?,Login=?,Clave=?,Estado=?"
                + " where Cod_Persona=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getaPaterno());
            pst.setString(3, dts.getaMaterno());
            pst.setString(4, dts.getfNacimiento());
            pst.setString(5, dts.getTiDocumento());
            pst.setString(6, dts.getNuDocumento());
            pst.setString(7, dts.getDireccion());
            pst.setString(8, dts.getTelefono());
            pst.setString(9, dts.getEmail());
            pst.setDate(10, dts.getFecharegistro());
            pst.setInt(11, dts.getIdPersona());

            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            pst2.setInt(6, dts.getIdPersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();
                pst2.close();
                pst.close();
                IO.escribir("Desconectado");

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                pst2.close();
                pst.close();
                IO.escribir("Desconectado");
                return false;
                
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(Empleado dts) {
        sSQL = "delete from empleado where Cod_Persona=?";
        sSQL2 = "delete from persona where Cod_Persona=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setInt(1, dts.getIdPersona());

            pst2.setInt(1, dts.getIdPersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();
                pst2.close();
                pst.close();
                IO.escribir("Desconectado");

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                pst2.close();
                pst.close();
                IO.escribir("Desconectado");
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public DefaultTableModel login(String login, String password) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Nombre", "Apaterno", "Amaterno", "Acceso", "Login", "Clave", "Estado"};

        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.Cod_Persona,p.Nombre,p.A_Paterno,p.A_Materno,"
                + "e.Acceso,e.Login,e.Clave,e.Estado from persona p inner join empleado e "
                + "on p.Cod_Persona=e.Cod_Persona where e.Login='"
                + login + "' and e.Clave='" + password + "' and e.Estado='A'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Cod_Persona");
                registro[1] = rs.getString("Nombre");
                registro[2] = rs.getString("A_Paterno");
                registro[3] = rs.getString("A_Materno");
                registro[4] = rs.getString("Acceso");
                registro[5] = rs.getString("Login");
                registro[6] = rs.getString("Clave");
                registro[7] = rs.getString("Estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            if (rs != null) {
                rs.close();
                IO.escribir("Desconectado");
            }
            st.close();
            rs.close();
            IO.escribir("Desconectado");
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
}
