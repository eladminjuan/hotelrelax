/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Pago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jdgom
 */
public class CPago {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Idreserva", "Comprobante", "Número", "Fecha Emisión", "Hora Emisión", "Fecha Pago", "Hora Pago", "IVA", "Total Pagar", "Total Pagado", "Saldo Actual"};

        String[] registro = new String[12];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from pago where Cod_Reserva=" + buscar + " order by Cod_Pago desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Cod_Pago");
                registro[1] = rs.getString("Cod_Reserva");
                registro[2] = rs.getString("Tipo_Comprobante");
                registro[3] = rs.getString("Num_Comprobante");
                registro[4] = rs.getString("Fecha_Emision");
                registro[5] = rs.getString("Hora_Emision");
                registro[6] = rs.getString("Fecha_Pago");
                registro[7] = rs.getString("Hora_Pago");
                registro[8] = rs.getString("Impuesto");
                registro[9] = rs.getString("Total_Pago");
                registro[10] = rs.getString("Total_Pagado");
                registro[11] = rs.getString("Saldo_Actual");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(Pago dts) {
        sSQL = "insert into pago (Cod_Reserva,Tipo_Comprobante,Num_Comprobante,Fecha_Emision,Hora_Emision,Fecha_Pago,Hora_Pago,Impuesto,Total_Pago,Total_Pagado,Saldo_Actual)"
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdReserva());
            pst.setString(2, dts.getTipoComprobante());
            pst.setString(3, dts.getNumeroComprobante());
            pst.setDate(4, dts.getFechaEmision());
            pst.setString(5, dts.getHoraEmision());
            pst.setDate(6, dts.getFechaPago());
            pst.setString(7, dts.getHoraPago());
            pst.setDouble(8, dts.getImpuesto());
            pst.setDouble(9, dts.getTotalPago());
            pst.setDouble(10, dts.getTotalPagado());
            pst.setDouble(11, dts.getSaldoActual());

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

    public boolean editar(Pago dts) {
        sSQL = "update pago set Cod_Reserva=?,Tipo_Comprobante=?,Num_Comprobante=?,Fecha_Emision=?,Hora_Emision=?,Fecha_Pago=?,Hora_Pago=?,Impuesto=?,Total_Pago=?,Total_Pagado=?,Saldo_Actual=?"
                + " where Cod_Pago=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdReserva());
            pst.setString(2, dts.getTipoComprobante());
            pst.setString(3, dts.getNumeroComprobante());
            pst.setDate(4, dts.getFechaEmision());
            pst.setString(5, dts.getHoraEmision());
            pst.setDate(6, dts.getFechaPago());
            pst.setString(7, dts.getHoraPago());
            pst.setDouble(8, dts.getImpuesto());
            pst.setDouble(9, dts.getTotalPago());
            pst.setDouble(10, dts.getTotalPagado());
            pst.setDouble(11, dts.getSaldoActual());

            pst.setInt(12, dts.getIdPago());

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

    public boolean eliminar(Pago dts) {
        sSQL = "delete from pago where Cod_Pago=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdPago());

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

    public DefaultTableModel mostrarPago(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Idreserva", "Cliente","Identificación", "Comprobante", "Número", "Fecha Emisión", "Hora Emisión", "Fecha Pago", "Hora Pago", "IVA", "Total Pagar", "Total Pagado", "Saldo Actual"};

        String[] registro = new String[14];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.Cod_Pago,p.Cod_Reserva,concat(pe.Nombre,' ',pe.A_Paterno,' ',pe.A_Materno) as Nombre,pe.Num_Documento,p.Tipo_Comprobante,p.Num_Comprobante,p.Fecha_Emision,p.Hora_Emision,p.Fecha_Pago,p.Hora_Pago,\n"
                + "p.Impuesto,p.Total_Pago,p.Total_Pagado,p.Saldo_Actual from pago as p inner join reserva as r on p.Cod_Reserva=r.Cod_Reserva inner join cliente as c\n"
                + "on r.Cod_Cliente=c.Cod_Persona inner join persona as pe on c.Cod_Persona=pe.Cod_Persona where concat(pe.Nombre,' ',pe.Num_Documento,' ',p.Tipo_Comprobante,' ',pe.A_Paterno,' ',pe.A_Materno,' ',p.Num_Comprobante) like '%"
                + buscar + "%' order by Cod_Pago desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Cod_Pago");
                registro[1] = rs.getString("Cod_Reserva");
                registro[2] = rs.getString("Nombre");
                registro[3] = rs.getString("Num_Documento");
                registro[4] = rs.getString("Tipo_Comprobante");
                registro[5] = rs.getString("Num_Comprobante");
                registro[6] = rs.getString("Fecha_Emision");
                registro[7] = rs.getString("Hora_Emision");
                registro[8] = rs.getString("Fecha_Pago");
                registro[9] = rs.getString("Hora_Pago");
                registro[10] = rs.getString("Impuesto");
                registro[11] = rs.getString("Total_Pago");
                registro[12] = rs.getString("Total_Pagado");
                registro[13] = rs.getString("Saldo_Actual");

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
