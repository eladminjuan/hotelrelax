/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jdgom
 */
public class Lista {

    private Nodo cabeza, fin;

    public Lista() {
        cabeza = null;
    }

    /**
     * Insertar al principio de la lista
     *
     * @param dato
     */
    public void insertarCabezaLista(Object dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
    }

    public void agregarAlFinal(Object dato) {

        if (estavaciaCabeza()) {

            cabeza = new Nodo((int) dato);

            fin = cabeza;

        } else {
           // fin.siguiente = new Nodo((int) dato);
           // fin = fin.siguiente;
        }

    }

    public boolean estavaciaFin() {
        return fin == null;

    }

    public boolean estavaciaCabeza() {
        return cabeza == null;

    }

    /**
     * Eliminar elemento de la lista
     *
     * @param dato
     */
    public void eliminar(Object dato) {
        Nodo actual = cabeza, anterior = null;

        while ((actual != null) && !obtenerCodigo(actual).equals(dato)) {
            if (!obtenerCodigo(actual).equals(dato)) {
                anterior = actual;
                actual = actual.getSiguiente();
            }
        }

        if (actual != null) {
            // Se distingue entre que el nodo sea el cabecera
            // o del resto de la lista
            if (actual == cabeza) {
                cabeza = actual.getSiguiente();
            } else {
                anterior.setSiguiente(actual.getSiguiente());
            }

            actual = null;
        }
    }

    public String visualizar() {
        Nodo n = cabeza;
        String cadena = "";
        while (n != null) {
            cadena = cadena + (obtenerCodigo(n) + "  " + obtenerNombre(n) + "  " + obtenerDescripcion(n) + "  " + obtenerUnidadMedida(n) + "  " + obtenerPrecioVenta(n) + "\n");
            n = n.getSiguiente();
        }

        return cadena;
    }

    public DefaultTableModel mostrarF(Lista lista) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Producto", "Descripción", "Unidad Medida", "Precio Venta"};
        IO.escribir("Lista." + visualizar());

        String[] registro = new String[5];
        modelo = new DefaultTableModel(null, titulos);
        try {
            
            Nodo n = cabeza;

            while (n != null) {
                registro[0] = (String) obtenerCodigo(n);
                registro[1] = (String) obtenerNombre(n);
                registro[2] = (String) obtenerDescripcion(n);
                registro[3] = (String) obtenerUnidadMedida(n);
                registro[4] = (String) obtenerPrecioVenta(n);
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

    public Nodo leerPrimero() {
        return cabeza;
    }

    public boolean esVacia() {
        return cabeza == null;
    }

    public void eliminarCodigosImpares() {
        Nodo n = cabeza;
        while (n != null) {
            if ((int) obtenerCodigo(n) % 2 != 0) {
                eliminar(obtenerCodigo(n));
            }

            n = n.getSiguiente();
        }
    }

    public int contar(Object dato) {
        Nodo n = cabeza;
        int cont = 0;
        while (n != null) {

            if (obtenerCodigo(n).equals(dato)) {
                cont++;
            }

            n = n.getSiguiente();
        }
        return cont;
    }

}
