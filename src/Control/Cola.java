
package Control;

import Modelo.Nodo;
import Modelo.Reserva;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jdgom
 */
public class Cola {

    protected Nodo frente;
    protected Nodo fin;

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();

    private String sSQL = "";
    public Integer totalregistros;
    private Nodo cima;

    public Cola() {
        frente = fin = null;
    }

    // PRECAUCIÓN: Si la cola está vacía, retorna NULL
    public Nodo getFrente() {
        return frente;
    }

    public Object obtenerIdReserva(Nodo n) {
        return ((Reserva) n.getDato()).getIdreserva();
    }

    public Object obtenerIdCliente(Nodo n) {
        return ((Reserva) n.getDato()).getIdCliente();
    }

    public Object obtenerIdEmpleado(Nodo n) {
        return ((Reserva) n.getDato()).getIdEmpleado();
    }

    public Object obtenerTipoReserva(Nodo n) {
        return ((Reserva) n.getDato()).getTipoReserva();
    }

    public Object obtenerPersona(Nodo n) {
        return ((Reserva) n.getDato()).getPersona();
    }

    public Object obtenerDescripcion(Nodo n) {
        return ((Reserva) n.getDato()).getDescripcion();
    }

    public Object obtenerFechaReservaI(Nodo n) {
        return ((Reserva) n.getDato()).getFechaReserva();
    }

    public Object obtenerHoraReserva(Nodo n) {
        return ((Reserva) n.getDato()).getHoraReserva();
    }

    public Object obtenerFechaIngreso(Nodo n) {
        return ((Reserva) n.getDato()).getFechaIngreso();
    }

    public Object obtenerHoraIngreso(Nodo n) {
        return ((Reserva) n.getDato()).getHoraIngreso();
    }

    public Object obtenerFechaSalida(Nodo n) {
        return ((Reserva) n.getDato()).getFechaSalida();
    }

    public Object obtenerHoraSalida(Nodo n) {
        return ((Reserva) n.getDato()).getHoraSalida();
    }

    public Object obtenerEstado(Nodo n) {
        return ((Reserva) n.getDato()).getEstado();
    }

    /**
     * Insertar elemento al final de la cola
     *
     * @param elemento
     */
    public void encolar(Object elemento) {
        Nodo a = new Nodo(elemento);

        if (esVacia()) {
            frente = a;
        } else {
            fin.setSiguiente(a);
        }

        fin = a;
    }

    /**
     * Quitar elemento al frente de la cola
     */
    public void desencolar() {
        if (!esVacia()) {
            frente = frente.getSiguiente();
        }
    }

    /**
     * Vaciar cola
     */
    public void borrar() {
        while (frente != null) {
            frente = frente.getSiguiente();
        }

        // Reciclar elementos en memoria
        System.gc();
    }

    /**
     * Contar elementos de la cola
     *
     * @return
     */
    public int contarDatos() {
        int contador = 0;
        Nodo n = frente;

        while (n != null) {
            contador++;
            n = n.getSiguiente();
        }

        return contador;
    }

    /**
     * Imprimir elementos de la cola
     */
    public String visualizar() {
        Nodo n = frente;
        String cadena = "";
        while (n != null) {
            cadena =cadena+ (obtenerIdReserva(n) + ", " + obtenerIdCliente(n) + ", " + obtenerIdEmpleado(n)
                    + ", " + obtenerTipoReserva(n) + "," + obtenerPersona(n)
                    + "," + obtenerDescripcion(n) + ", " + obtenerFechaReservaI(n) + ", " + obtenerHoraReserva(n)
                    + "," + obtenerFechaIngreso(n) + ", " + obtenerHoraIngreso(n) + "," + obtenerFechaSalida(n)
                    + ", " + obtenerHoraSalida(n) + ", " + obtenerEstado(n)+"\n");
                    
            n = n.getSiguiente();
        }
        return cadena;
    }

    /**
     * Comprobar si la cola esta vacia
     */
    public boolean esVacia() {
        return frente == null;
    }

    public void crearCola() {

        sSQL = "select *  from reserva order by Cod_Reserva asc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                Reserva r = new Reserva();

                r.setIdreserva(rs.getInt("Cod_Reserva"));
                r.setIdCliente(rs.getInt("Cod_Cliente"));
                r.setIdEmpleado(rs.getInt("Cod_Empleado"));
                r.setTipoReserva(rs.getString("Tipo_Reserva"));
                r.setPersona(rs.getString("Cant_Persona"));
                r.setDescripcion(rs.getString("Descripcion"));
                r.setFechaReserva(rs.getDate("Fecha_Reserva"));
                r.setHoraReserva(rs.getString("Hora_Reserva"));
                r.setFechaIngreso(rs.getDate("Fecha_Ingreso"));
                r.setHoraIngreso(rs.getString("Hora_Ingreso"));
                r.setFechaSalida(rs.getDate("Fecha_Salida"));
                r.setHoraSalida(rs.getString("Hora_Salida"));
                r.setEstado(rs.getString("Estado"));
                encolar(r);
            }
            

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Error Aquí " + e);

        }
    }

    public DefaultTableModel crearModelo() {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "idcliente","idempleado","Tipo Reserva", "Cant Personas",
            "Descripcion", "Fecha Reserva", "Hora Reserva", "Fecha Ingreso", "Hora Ingreso", "Fecha Salida", "Hora Salida", "Estado"};

        String[] registro = new String[13];
        modelo = new DefaultTableModel(null, titulos);
        crearCola();

        try {

            Nodo n = frente;

            while (n != null) {
                registro[0] = obtenerIdReserva(n).toString();
                registro[1] = obtenerIdCliente(n).toString();
                registro[2] = obtenerIdEmpleado(n).toString();
                registro[3] = obtenerTipoReserva(n).toString();
                registro[4] = obtenerPersona(n).toString();
                registro[5] = obtenerDescripcion(n).toString();
                registro[6] = obtenerFechaReservaI(n).toString();
                registro[7] = obtenerHoraReserva(n).toString();
                registro[8] = obtenerFechaIngreso(n).toString();
                registro[9] = obtenerHoraIngreso(n).toString();
                registro[10] = obtenerFechaSalida(n).toString();
                registro[11] = obtenerHoraSalida(n).toString();
                registro[12] = obtenerEstado(n).toString();
                n = n.getSiguiente();
                modelo.addRow(registro);
                System.out.println("paso por aquí");
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);

        }
        return null;
    }
}
