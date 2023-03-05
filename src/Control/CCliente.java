/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CCliente {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Nombre", "Apaterno", "Amaterno", "Fecha Nacimiento", "Doc", "Númeo Documento", "Dirección", "Teléfono", "Email", "Fecha Registro", "Código", "Login", "Password", "Estado"};

        String[] registro = new String[15];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.Cod_Persona,p.Nombre,p.A_Paterno,p.A_Materno,p.Fecha_Nacimiento,p.Tipo_Documento,p.Num_Documento,"
                + "p.Direccion,p.Telefono,p.Email,p.Fecha_Registro,c.Cod_Cliente,c.Login,c.Password,c.Estado from persona p inner join cliente c "
                + "on p.Cod_Persona=c.Cod_Persona where concat(Num_Documento,' ',Nombre,' ',A_Paterno,' ',A_Materno,' ',Tipo_Documento) like '%"
                + buscar + "%' and c.Estado='A'  order by Cod_Persona desc";

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
                registro[11] = rs.getString("Cod_Cliente");
                registro[12] = rs.getString("Login");
                registro[13] = rs.getString("Password");
                registro[14] = rs.getString("Estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(Cliente dts) {
        sSQL = "insert into persona (Nombre,A_Paterno,A_Materno,Fecha_Nacimiento,Tipo_Documento,Num_Documento,Direccion,Telefono,Email,Fecha_Registro)"
                + "values (?,?,?,?,?,?,?,?,?,?)";
        sSQL2 = "insert into cliente (Cod_Persona,Cod_Cliente,Login,Password,Estado)"
                + "values ((select Cod_Persona from persona order by Cod_Persona desc limit 1),?,?,?,?)";
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

            pst2.setString(1, dts.getCodCliente());
            pst2.setString(2, dts.getLogin());
            pst2.setString(3, dts.getPassword());
            pst2.setString(4, dts.getEstado());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(Cliente dts) {
        sSQL = "update persona set Nombre=?,A_Paterno=?,A_Materno=?,Fecha_Nacimiento=?,Tipo_Documento=?,Num_Documento=?,"
                + " Direccion=?,Telefono=?,Email=?,Fecha_Registro=? where Cod_Persona=?";

        sSQL2 = "update cliente set Cod_Cliente=?,Login=?,Password=?,Estado=?"
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

            pst2.setString(1, dts.getCodCliente());
            pst2.setString(2, dts.getLogin());
            pst2.setString(3, dts.getPassword());
            pst2.setString(4, dts.getEstado());
            pst2.setInt(5, dts.getIdPersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editarEstado(Cliente dts) {
        sSQL = "update cliente set Estado='I' where Cod_Persona=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdPersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(Cliente dts) {
        sSQL = "delete from cliente where Cod_Persona=?";
        sSQL2 = "delete from persona where Cod_Persona=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setInt(1, dts.getIdPersona());

            pst2.setInt(1, dts.getIdPersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public DefaultTableModel mostrarConsulta1() {
        DefaultTableModel modelo;

        String[] titulos = {"MES", "CANTIDAD DE REGISTROS"};

        String[] registro = new String[2];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select MONTHNAME(p.Fecha_Registro) AS Mes,Count(p.Cod_Persona) AS Cantidad_Registro_Cliente from persona p inner join cliente c \n"
                + "on p.Cod_Persona=c.Cod_Persona  GROUP BY Mes order by Mes asc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Mes");
                registro[1] = rs.getString("Cantidad_Registro_Cliente");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public DefaultTableModel mostrarConsulta2() {
        DefaultTableModel modelo;

        String[] titulos = {"AÑO", "CANTIDAD DE REGISTROS"};

        String[] registro = new String[2];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select YEAR(p.Fecha_Registro) AS Ano,Count(p.Cod_Persona) AS Cantidad_Registro_Cliente from persona p inner join cliente c \n"
                + "on p.Cod_Persona=c.Cod_Persona  GROUP BY Ano order by Ano asc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Ano");
                registro[1] = rs.getString("Cantidad_Registro_Cliente");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public DefaultTableModel mostrarConsulta3() {
        DefaultTableModel modelo;

        String[] titulos = {"DIA", "CANTIDAD DE REGISTROS"};

        String[] registro = new String[2];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select dayname(p.Fecha_Registro) AS Dia,Count(c.Cod_Persona) AS Cantidad_Registro_Cliente from persona p inner join cliente c \n" 
                +"on p.Cod_Persona=c.Cod_Persona where DATE(p.Fecha_Registro) > CURDATE() - INTERVAL 7 DAY group by Dia order by Dia desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Dia");
                registro[1] = rs.getString("Cantidad_Registro_Cliente");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
    
    public DefaultTableModel mostrarConsulta4(String inicio, String fin) {
        DefaultTableModel modelo;

        String[] titulos = {"CÓDIGO","NOMBRE","IDENTIFICACIÓN","FECHA REGISTRO"};

        String[] registro = new String[4];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.Cod_Persona,concat(p.Nombre,' ',p.A_Paterno,' ',p.A_Materno) as Nombre,p.Num_Documento,p.Fecha_Registro from persona p inner join cliente c \n" +
               "on p.Cod_Persona=c.Cod_Persona WHERE p.Fecha_Registro>='"+inicio+"' and p.Fecha_Registro<='"+fin+"' order by p.Cod_Persona desc;";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Cod_Persona");
                registro[1] = rs.getString("Nombre");
                registro[2] = rs.getString("Num_Documento");
                registro[3] = rs.getString("Fecha_Registro");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
    
    public DefaultTableModel mostrarConsulta5(String inicio) {
        DefaultTableModel modelo;

        String[] titulos = {"CÓDIGO","NOMBRE","IDENTIFICACIÓN","FECHA REGISTRO"};

        String[] registro = new String[4];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.Cod_Persona,concat(p.Nombre,' ',p.A_Paterno,' ',p.A_Materno) as Nombre,p.Num_Documento,p.Fecha_Registro from persona p inner join cliente c \n" +
               "on p.Cod_Persona=c.Cod_Persona WHERE p.Fecha_Registro>='"+inicio+"' and curdate() order by p.Cod_Persona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Cod_Persona");
                registro[1] = rs.getString("Nombre");
                registro[2] = rs.getString("Num_Documento");
                registro[3] = rs.getString("Fecha_Registro");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
}
