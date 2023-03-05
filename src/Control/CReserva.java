package Control;

import Modelo.Habitacion;
import Modelo.Producto;
import Modelo.Reserva;
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
public class CReserva {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "idcliente", "Cliente", "idempleado", "Empleado", "Tipo Reserva", "Cant Personas", "Descripcion", "Fecha Reserva", "Hora Reserva", "Fecha Ingreso", "Hora Ingreso", "Fecha Salida", "Hora Salida", "Estado"};

        String[] registro = new String[15];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select r.Cod_Reserva,r.Cod_Cliente,"
                + "(select Nombre from persona where Cod_Persona=r.Cod_Cliente)as clienten,"
                + "(select A_Paterno from persona where Cod_Persona=r.Cod_Cliente)as clienteap,"
                + "r.Cod_Empleado,(select Nombre from persona where Cod_Persona=r.Cod_Empleado)as empleadon,"
                + "(select A_Paterno from persona where Cod_Persona=r.Cod_Empleado)as empleadoap,"
                + "r.Tipo_Reserva,r.Cant_Persona,r.Descripcion,r.Fecha_Reserva,r.Hora_Reserva,r.Fec"
                + "ha_Ingreso,r.Hora_Ingreso,r.Fecha_Salida,r.Hora_Salida,"
                + "r.Estado from reserva r where concat(r.Fecha_Reserva,' ',r.Fecha_Ingreso,' ',r.Fecha_Salida,' ',r.Estado,' ',r.Tipo_Reserva) like '%" + buscar + "%' order by Cod_Reserva desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Cod_Reserva");
                registro[1] = rs.getString("Cod_Cliente");
                registro[2] = rs.getString("clienten") + " " + rs.getString("clienteap");
                registro[3] = rs.getString("Cod_Empleado");
                registro[4] = rs.getString("empleadon") + " " + rs.getString("empleadoap");
                registro[5] = rs.getString("Tipo_Reserva");
                registro[6] = rs.getString("Cant_Persona");
                registro[7] = rs.getString("Descripcion");
                registro[8] = rs.getString("Fecha_Reserva");
                registro[9] = rs.getString("Hora_Reserva");
                registro[10] = rs.getString("Fecha_Ingreso");
                registro[11] = rs.getString("Hora_Ingreso");
                registro[12] = rs.getString("Fecha_Salida");
                registro[13] = rs.getString("Hora_Salida");
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

    public boolean insertar(Reserva dts) {
        sSQL = "insert into reserva (Cod_Cliente,Cod_Empleado,Tipo_Reserva,Cant_Persona,Descripcion,Fecha_Reserva,Hora_Reserva,Fecha_Ingreso,Hora_Ingreso,Fecha_Salida,Hora_Salida,Estado)"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdCliente());
            pst.setInt(2, dts.getIdEmpleado());
            pst.setString(3, dts.getTipoReserva());
            pst.setString(4, dts.getPersona());
            pst.setString(5, dts.getDescripcion());
            pst.setDate(6, dts.getFechaReserva());
            pst.setString(7, dts.getHoraReserva());
            pst.setDate(8, dts.getFechaIngreso());
            pst.setString(9, dts.getHoraIngreso());
            pst.setDate(10, dts.getFechaSalida());
            pst.setString(11, dts.getHoraSalida());
            pst.setString(12, dts.getEstado());

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

    public boolean editar(Reserva dts) {
        sSQL = "update reserva set Cod_Cliente=?,Cod_Empleado=?,Tipo_Reserva=?,Cant_Persona=?,Descripcion=?,Fecha_Reserva=?,Hora_Reserva=?,Fecha_Ingreso=?,Hora_Ingreso=?,Fecha_Salida=?,Hora_Salida=?,Estado=?"
                + " where Cod_Reserva=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdCliente());
            pst.setInt(2, dts.getIdEmpleado());
            pst.setString(3, dts.getTipoReserva());
            pst.setString(4, dts.getPersona());
            pst.setString(5, dts.getDescripcion());
            pst.setDate(6, dts.getFechaReserva());
            pst.setString(7, dts.getHoraReserva());
            pst.setDate(8, dts.getFechaIngreso());
            pst.setString(9, dts.getHoraIngreso());
            pst.setDate(10, dts.getFechaSalida());
            pst.setString(11, dts.getHoraSalida());
            pst.setString(12, dts.getEstado());

            pst.setInt(13, dts.getIdreserva());

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

    public boolean pagar(Reserva dts) {
        sSQL = "update reserva set Estado='Pagada'"
                + " where Cod_Reserva=?";
        //alt + 39

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdreserva());

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

    public boolean eliminar(Reserva dts) {
        sSQL = "delete from detalle_reserva where Cod_Reserva=?";
        sSQL2 = "delete from reserva where Cod_Reserva=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setInt(1, dts.getIdreserva());
            pst2.setInt(1, dts.getIdreserva());

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

    public DefaultTableModel mostrarConsulta1() {
        DefaultTableModel modelo;

        String[] titulos = {"MES", "CANTIDAD DE RESERVAS"};

        String[] registro = new String[2];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select MONTHNAME(Fecha_Reserva) AS Mes,Count(Cod_Reserva) AS Cantidad_Reservas from reserva GROUP BY Mes order by Mes asc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Mes");
                registro[1] = rs.getString("Cantidad_Reservas");

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

        String[] titulos = {"AÑO", "CANTIDAD DE RESERVAS"};

        String[] registro = new String[2];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select YEAR(Fecha_Reserva) AS Ano,Count(Cod_Reserva) AS Cantidad_Reservas from reserva GROUP BY Ano order by Ano asc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Ano");
                registro[1] = rs.getString("Cantidad_Reservas");

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

        String[] titulos = {"DIA", "CANTIDAD DE RESERVAS"};

        String[] registro = new String[2];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select dayname(Fecha_Reserva) AS Dia,Count(Cod_Reserva) AS Cantidad_Reservas from reserva where \n"
                + "DATE(Fecha_Reserva) > CURDATE() - INTERVAL 7 DAY group by Dia order by Dia desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Dia");
                registro[1] = rs.getString("Cantidad_Reservas");

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

        String[] titulos = {"CÓDIGO", "CLIENTE", "IDENTIFICACIÓN", "EMPLEADO", "FECHA RESERVA"};

        String[] registro = new String[5];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select r.Cod_Reserva,(select Nombre from persona where Cod_Persona=r.Cod_Cliente)as clienten,\n"
                + "(select A_Paterno from persona where Cod_Persona=r.Cod_Cliente)as clienteap,\n"
                + "(select Num_Documento from persona where Cod_Persona=r.Cod_Cliente)as documento,\n"
                + "(select Nombre from persona where Cod_Persona=r.Cod_Empleado)as empleadon,(select A_Paterno from persona where Cod_Persona=r.Cod_Empleado)as empleadoap,\n"
                + "r.Fecha_Reserva from reserva r WHERE r.Fecha_Reserva>='" + inicio + "' and r.Fecha_Reserva<='" + fin + "' order by r.Cod_Reserva desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Cod_Reserva");
                registro[1] = rs.getString("clienten") + " " + rs.getString("clienteap");
                registro[2] = rs.getString("documento");
                registro[3] = rs.getString("empleadon") + " " + rs.getString("empleadoap");
                registro[4] = rs.getString("Fecha_Reserva");

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

        String[] titulos = {"CÓDIGO", "CLIENTE", "IDENTIFICACIÓN", "EMPLEADO", "FECHA RESERVA"};

        String[] registro = new String[5];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select r.Cod_Reserva,(select Nombre from persona where Cod_Persona=r.Cod_Cliente)as clienten,\n"
                + "(select A_Paterno from persona where Cod_Persona=r.Cod_Cliente)as clienteap,\n"
                + "(select Num_Documento from persona where Cod_Persona=r.Cod_Cliente)as documento,\n"
                + "(select Nombre from persona where Cod_Persona=r.Cod_Empleado)as empleadon,(select A_Paterno from persona where Cod_Persona=r.Cod_Empleado)as empleadoap,\n"
                + "r.Fecha_Reserva from reserva r WHERE r.Fecha_Reserva>='" + inicio + "' and curdate() order by r.Cod_Reserva desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Cod_Reserva");
                registro[1] = rs.getString("clienten") + " " + rs.getString("clienteap");
                registro[2] = rs.getString("documento");
                registro[3] = rs.getString("empleadon") + " " + rs.getString("empleadoap");
                registro[4] = rs.getString("Fecha_Reserva");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public DefaultTableModel mostrarConsulta6() {
        DefaultTableModel modelo;

        String[] titulos = {"CANT. HABITACIONES", "CLIENTE", "IDENTIFICACIÓN", "EMPLEADO", "FECHA RESERVA"};

        String[] registro = new String[5];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select (select count(*)from detalle_reserva where detalle_reserva.Cod_Reserva=r.Cod_Reserva)as canthabitacion,\n"
                + "concat(pe.Nombre,' ',pe.A_Paterno,' ',pe.A_Materno) as Nombre,pe.Num_Documento,\n"
                + "(select concat(Nombre,' ',A_Paterno) from persona where Cod_Persona=r.Cod_Empleado)as empleadon,r.Fecha_Reserva from reserva as r\n"
                + "inner join cliente c on r.Cod_Cliente=c.Cod_Persona inner join persona pe on c.Cod_Persona=pe.Cod_Persona order by canthabitacion desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("canthabitacion");
                registro[1] = rs.getString("Nombre");
                registro[2] = rs.getString("Num_Documento");
                registro[3] = rs.getString("empleadon");
                registro[4] = rs.getString("Fecha_Reserva");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public DefaultTableModel mostrarConsulta7(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"HABITACIÓN #", "TIPO", "CARACTERISTICA", "PRECIO", "ESTADO", "CLIENTE", "IDENTIFICACIÓN", "FECHA RESERVA", "FECHA INGRESO", "FECHA SALIDA"};

        String[] registro = new String[10];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select (select h.Numero from detalle_reserva de,habitacion h where h.Cod_Habitacion=de.Cod_Habitacion and de.Cod_Reserva=r.Cod_Reserva)as Numero,\n"
                + "(select h.Tipo_Habitacion from detalle_reserva de,habitacion h where h.Cod_Habitacion=de.Cod_Habitacion and de.Cod_Reserva=r.Cod_Reserva)as Tipo,\n"
                + "(select h.Caracteristicas from detalle_reserva de,habitacion h where h.Cod_Habitacion=de.Cod_Habitacion and de.Cod_Reserva=r.Cod_Reserva)as Caracteristica,\n"
                + "(select h.Precio_Diario from detalle_reserva de,habitacion h where h.Cod_Habitacion=de.Cod_Habitacion and de.Cod_Reserva=r.Cod_Reserva)as Precio,\n"
                + "(select h.Estado from detalle_reserva de,habitacion h where h.Cod_Habitacion=de.Cod_Habitacion and de.Cod_Reserva=r.Cod_Reserva)as Estado,\n"
                + "concat(pe.Nombre,' ',pe.A_Paterno,' ',pe.A_Materno)as Nombre,pe.Num_Documento,r.Fecha_Reserva,r.Fecha_Ingreso,r.Fecha_Salida from reserva r \n"
                + "inner join cliente c on r.Cod_Cliente=c.Cod_Persona inner join persona pe on c.Cod_Persona=pe.Cod_Persona \n"
                + "where concat(pe.Nombre,' ',pe.A_Paterno,' ',pe.A_Materno,' ',pe.Num_Documento) like '%" + buscar + "%' order by r.Fecha_Reserva desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("Numero");
                registro[1] = rs.getString("Tipo");
                registro[2] = rs.getString("Caracteristica");
                registro[3] = rs.getString("Precio");
                registro[4] = rs.getString("Estado");
                registro[5] = rs.getString("Nombre");
                registro[6] = rs.getString("Num_Documento");
                registro[7] = rs.getString("Fecha_Reserva");
                registro[8] = rs.getString("Fecha_Ingreso");
                registro[9] = rs.getString("Fecha_Salida");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public DefaultTableModel mostrarConsulta8() {
        DefaultTableModel modelo;

        String[] titulos = {"CANT. RESERVAS", "CLIENTE", "IDENTIFICACIÓN", "FECHA RESERVA"};

        String[] registro = new String[4];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select count(r.Cod_Reserva)as cantidadReservas,concat(pe.Nombre,' ',pe.A_Paterno,' ',pe.A_Materno)as Nombre,pe.Num_Documento,r.Fecha_Reserva\n"
                + "from reserva r inner join cliente c on r.Cod_Cliente=c.Cod_Persona inner join persona pe on c.Cod_Persona=pe.Cod_Persona group by pe.Num_Documento \n"
                + "order by cantidadReservas asc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("cantidadReservas");
                registro[1] = rs.getString("Nombre");
                registro[2] = rs.getString("Num_Documento");
                registro[3] = rs.getString("Fecha_Reserva");

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
