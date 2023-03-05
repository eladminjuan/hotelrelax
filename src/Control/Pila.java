package Control;

import Control.Conexion;
import Modelo.IO;
import Modelo.Nodo;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Pila{

    public Pila(Integer totalregistros, Nodo cima) {
        this.totalregistros = totalregistros;
        this.cima = cima;
    }

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();

    private String sSQL = "";
    public Integer totalregistros;
    private Nodo cima;

    public Pila() {
        cima = null;
    }

    // PRECAUCIÓN: Si la pila está vacía, retorna NULL
    public Nodo getCima() {
        return cima;
    }

    public Object obtenerCodigo(Nodo n) {
        return ((Producto) n.getDato()).getIdProducto();
    }

    public Object obtenerNombre(Nodo n) {
        return ((Producto) n.getDato()).getNombre();
    }

    public Object obtenerDescripcion(Nodo n) {
        return ((Producto) n.getDato()).getDescripcion();
    }

    public Object obtenerUnidadMedida(Nodo n) {
        return ((Producto) n.getDato()).getUnidadMedida();
    }

    public Object obtenerPrecioVenta(Nodo n) {
        return ((Producto) n.getDato()).getPrecioVenta();
    }

    /**
     * Insertar elemento como cima de la pila
     *
     * @param elemento
     */
    public void insertar(Object elemento) {
        Nodo nuevo = new Nodo(elemento, cima);
        cima = nuevo;
    }

    /**
     * Quitar elemento cima de la pila
     */
    public void quitar() {
        if (!esVacia()) {
            cima = cima.getSiguiente();
        }
    }

    /**
     * Vaciar pila
     */
    public void limpiar() {
        Nodo n;

        while (!esVacia()) {
            n = cima;
            cima = cima.getSiguiente();
            n.setSiguiente(null);
        }
    }

    /**
     * Contar elementos de la pila
     *
     * @return
     */
    public int contarDatos() {
        int contador = 0;
        Nodo n = cima;

        while (n != null) {
            contador++;
            n = n.getSiguiente();
        }

        return contador;
    }

    /**
     * Imprimir elementos de la pila
     */
    public String visualizar() {
        Nodo n = cima;
        String cadena = "";

        while (n != null) {
            cadena = cadena + (obtenerCodigo(n) + "  " + obtenerNombre(n) + "  " + obtenerDescripcion(n)
                    + "  " + obtenerUnidadMedida(n) + "  " + obtenerPrecioVenta(n) + "\n");
            n = n.getSiguiente();
        }

        return cadena;
    }

    /**
     * Comprobar si la pila esta vacia
     *
     * @return
     */
    public boolean esVacia() {
        return cima == null;
    }

    public DefaultTableModel crearModelo() {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Producto", "Descripción", "Unidad Medida", "Precio Venta"};

        String[] registro = new String[5];
        modelo = new DefaultTableModel(null, titulos);
        crearPila();

        try {

            Nodo n = cima;

            while (n != null) {
                registro[0] = obtenerCodigo(n).toString();
                registro[1] = obtenerNombre(n).toString();
                registro[2] = obtenerDescripcion(n).toString();
                registro[3] = obtenerUnidadMedida(n).toString();
                registro[4] = obtenerPrecioVenta(n).toString();
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

    public void crearPila() {
         totalregistros = 0;
        sSQL = "select * from producto";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while (rs.next()) {
                Producto dts = new Producto();

                dts.setIdProducto(rs.getInt("Cod_Producto"));
                dts.setNombre(rs.getString("Nombre"));
                dts.setDescripcion(rs.getString("Descripcion"));
                dts.setUnidadMedida(rs.getString("Unidad_Medida"));
                dts.setPrecioVenta(rs.getDouble("Precio_Venta"));
                
                insertar(dts);
                totalregistros = totalregistros + 1;

            }
            IO.escribir(visualizar());

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);

        }

    }

    

}
