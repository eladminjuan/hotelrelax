/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Habitacion;
import Modelo.Reserva;
import Control.CDetalle_Reserva;
import Control.CHabitacion;
import Control.CProducto;
import Control.CReserva;
import Control.Conexion;
import com.toedter.calendar.JDateChooser;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jdgom
 */
public class JFReserva extends javax.swing.JInternalFrame /*implements Runnable*/ {

    FondoPane9 fondo = new FondoPane9();

    String hora, minutos, segundo;
    // Thread hilo;

    /**
     * Creates new form JFReserva
     */
    public JFReserva() {
        this.setContentPane(fondo);
        initComponents();
        mostrar("");
        // TablaHabitacion.setVisible(false);
        inhabilitar();
        // hilo = new Thread(this);
        // hilo.start();
        setVisible(true);
        txtTotolReserva.setEnabled(false);
        txtvalorh.setVisible(false);
    }

    private String accion = "guardar";
    public static int idUsuario;
    public static Double valor;
    DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

    void hora() {
        Calendar calendario = new GregorianCalendar();
        java.util.Date horaActual = new java.util.Date();
        calendario.setTime(horaActual);
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundo = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    /* public void run() {
     Thread current = Thread.currentThread();
     while (current == hilo) {

     hora();
     txtHoraReserva.setText(hora + ":" + minutos + ":" + segundo);

     java.util.Date utilDate = new java.util.Date();
     java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
     dcFechaReserva.setDate(sqlDate);

     }
     //  for(int i=0;i<10;i++){
     //      if(i>0){
     //         hora();
     //          lblHora.setText(hora+":"+minutos+":"+segundo);
     //     }
     //  }
     }*/
    void ocultarColumnas() {
        TablaH.getColumnModel().getColumn(0).setMaxWidth(0);
        TablaH.getColumnModel().getColumn(0).setMinWidth(0);
        TablaH.getColumnModel().getColumn(0).setPreferredWidth(0);

        TablaH.getColumnModel().getColumn(1).setMaxWidth(0);
        TablaH.getColumnModel().getColumn(1).setMinWidth(0);
        TablaH.getColumnModel().getColumn(1).setPreferredWidth(0);

        TablaH.getColumnModel().getColumn(3).setMaxWidth(0);
        TablaH.getColumnModel().getColumn(3).setMinWidth(0);
        TablaH.getColumnModel().getColumn(3).setPreferredWidth(0);
    }

    void ocultarColumnasHabitacion() {
        TablaHabitacion.getColumnModel().getColumn(0).setMaxWidth(0);
        TablaHabitacion.getColumnModel().getColumn(0).setMinWidth(0);
        TablaHabitacion.getColumnModel().getColumn(0).setPreferredWidth(0);
        TablaHabitacion.getColumnModel().getColumn(1).setMaxWidth(0);
        TablaHabitacion.getColumnModel().getColumn(1).setMinWidth(0);
        TablaHabitacion.getColumnModel().getColumn(1).setPreferredWidth(0);
        TablaHabitacion.getColumnModel().getColumn(2).setMaxWidth(0);
        TablaHabitacion.getColumnModel().getColumn(2).setMinWidth(0);
        TablaHabitacion.getColumnModel().getColumn(2).setPreferredWidth(0);
    }

    void inhabilitar() {
        txtidR.setVisible(false);
        txtIdC.setVisible(false);
        txtIdE.setVisible(false);
        txtNombreC.setEnabled(false);
        txtNombreC.setEnabled(false);
        txtNombreE.setEnabled(false);
        cboTipoReserva.setEnabled(false);
        cboCantPersonas.setEnabled(false);
        txtDescripcion.setEnabled(false);
        dcFechaReserva.setEnabled(false);
        dcFechaIngreso.setEnabled(false);
        dcFechaSalida.setEnabled(false);
        cboEstado.setEnabled(false);
        txtHoraIngreso.setEnabled(false);
        txtHoraReserva.setEnabled(false);
        txtHoraSalida.setEnabled(false);

        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnBuscaC.setEnabled(false);
        btnConsumo.setEnabled(false);
        btnRealizarPagos.setEnabled(false);
        txtidR.setText("");
        txtIdC.setText("");
        txtNombreC.setText("");
        txtDescripcion.setText("");
        dcFechaReserva.setDate(null);
        dcFechaIngreso.setDate(null);
        dcFechaSalida.setDate(null);
        txtHoraIngreso.setText("");
        txtHoraSalida.setText("");
        txtHoraReserva.setText("");
        txtTotolReserva.setText("");
        lblregistroConsumo.setText("-");
        cboTipoReserva.setSelectedIndex(0);
        cboCantPersonas.setSelectedIndex(0);
        cboEstado.setSelectedIndex(0);
        TablaHabitacion.setVisible(false);
    }

    void habilitar() {
        txtidR.setVisible(false);
        txtIdC.setVisible(false);
        txtIdE.setVisible(false);
        txtNombreC.setEnabled(false);
        txtNombreE.setEnabled(false);
        cboTipoReserva.setEnabled(true);
        cboCantPersonas.setEnabled(true);
        txtDescripcion.setEnabled(true);
        dcFechaReserva.setEnabled(false);
        dcFechaIngreso.setEnabled(true);
        dcFechaSalida.setEnabled(true);
        cboEstado.setEnabled(true);
        txtHoraIngreso.setEnabled(true);
        txtHoraSalida.setEnabled(true);

        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnBuscaC.setEnabled(true);

        btnRealizarPagos.setEnabled(true);
        txtidR.setText("");
        txtIdC.setText("");
        txtNombreC.setText("");
        txtDescripcion.setText("");
        dcFechaReserva.setDate(null);
        dcFechaIngreso.setDate(null);
        dcFechaSalida.setDate(null);
        txtHoraIngreso.setText("");
        txtHoraSalida.setText("");
        txtHoraReserva.setText("");
        cboTipoReserva.setSelectedIndex(0);
        cboCantPersonas.setSelectedIndex(0);
        cboEstado.setSelectedIndex(0);
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            CReserva produ = new CReserva();
            modelo = produ.mostrar(buscar);
            TablaH.setModel(modelo);
            ocultarColumnas();
            lblregistro.setText("Total Resgistros: " + Integer.toString(produ.totalregistros));
        } catch (Exception e) {
            System.err.println("Error en el llenado de la tabla... " + e);
            JOptionPane.showMessageDialog(null, "Error en el llenado de la tabla");
        }
    }

    void mostrarHabitacion(String buscar) {
        try {
            DefaultTableModel modelo;
            CDetalle_Reserva produ = new CDetalle_Reserva();
            modelo = produ.mostrar(buscar);
            TablaHabitacion.setModel(modelo);
            ocultarColumnasHabitacion();
            lblregistroConsumo.setText("Total Resgistros: " + Integer.toString(produ.totalregistros));
            txtTotolReserva.setText(Double.toString(produ.totalvalor));
        } catch (Exception e) {
            System.err.println("Error en el llenado de la tabla... " + e);
            JOptionPane.showMessageDialog(null, "Error en el llenado de la tablaH");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popuEliminar = new javax.swing.JMenuItem();
        pupImprimir = new javax.swing.JMenuItem();
        pupBajaHabitaciones = new javax.swing.JMenuItem();
        jPanel1 = new FondoPane9();
        jLabel10 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        lblregistro = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnConsumo = new javax.swing.JButton();
        btnRealizarPagos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new FondoPane9();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtidR = new javax.swing.JTextField();
        cboTipoReserva = new javax.swing.JComboBox();
        txtIdC = new javax.swing.JTextField();
        txtNombreC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtIdE = new javax.swing.JTextField();
        txtNombreE = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dcFechaReserva = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        dcFechaIngreso = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        dcFechaSalida = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox();
        btnBuscaC = new javax.swing.JButton();
        txtHoraReserva = new javax.swing.JTextField();
        txtHoraIngreso = new javax.swing.JTextField();
        txtHoraSalida = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtvalorh = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cboCantPersonas = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaHabitacion = new javax.swing.JTable();
        lblregistroConsumo = new javax.swing.JLabel();
        lblTotalvalorH = new javax.swing.JLabel();
        txtTotolReserva = new javax.swing.JTextField();
        rSPanelGradiente3 = new rspanelgradiente.RSPanelGradiente();
        l3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaH = new javax.swing.JTable();
        pupHabitacion = new javax.swing.JButton();

        popuEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/eliminar.png"))); // NOI18N
        popuEliminar.setText("Eliminar");
        popuEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popuEliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popuEliminar);

        pupImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/impresora.png"))); // NOI18N
        pupImprimir.setText("Imprimir");
        pupImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pupImprimirActionPerformed(evt);
            }
        });
        jPopupMenu1.add(pupImprimir);

        pupBajaHabitaciones.setText("desig. Habitaciones");
        pupBajaHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pupBajaHabitacionesActionPerformed(evt);
            }
        });
        jPopupMenu1.add(pupBajaHabitaciones);

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Reserva");

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Buscar:");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        lblregistro.setText("Registro");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/impresora.png"))); // NOI18N
        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnConsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/carros.png"))); // NOI18N
        btnConsumo.setText("Consumos");
        btnConsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsumoActionPerformed(evt);
            }
        });

        btnRealizarPagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/pago.png"))); // NOI18N
        btnRealizarPagos.setText("Realizar Pagos");
        btnRealizarPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarPagosActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);

        jPanel2.setPreferredSize(new java.awt.Dimension(370, 588));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("ID:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Cliente:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Tipo Reserva:");

        txtidR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidRActionPerformed(evt);
            }
        });

        cboTipoReserva.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Telefono", "Presencial", "En Linea" }));
        cboTipoReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoReservaActionPerformed(evt);
            }
        });

        txtIdC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdCActionPerformed(evt);
            }
        });

        txtNombreC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Empleado:");

        txtIdE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdEActionPerformed(evt);
            }
        });

        txtNombreE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreEActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Fecha Reserva:");

        dcFechaReserva.setDateFormatString("yyyy/MM/dd");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Fecha Ingreso:");

        dcFechaIngreso.setDateFormatString("yyyy/MM/dd");
        dcFechaIngreso.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcFechaIngresoPropertyChange(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Fecha Salida:");

        dcFechaSalida.setDateFormatString("yyyy/MM/dd");
        dcFechaSalida.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcFechaSalidaPropertyChange(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Estado:");

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Pendiente", "Pagada", "Anulada" }));
        cboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstadoActionPerformed(evt);
            }
        });

        btnBuscaC.setText("...");
        btnBuscaC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaCActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Descripcion:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        txtvalorh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtvalorhActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Cant Personas:");

        cboCantPersonas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10 o más" }));
        cboCantPersonas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCantPersonasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboCantPersonas, 0, 161, Short.MAX_VALUE)
                            .addComponent(cboTipoReserva, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtIdE, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtIdC, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscaC, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtidR, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtvalorh, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcFechaReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcFechaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtHoraIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                        .addComponent(txtHoraSalida))
                                    .addComponent(txtHoraReserva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(46, 46, 46))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtidR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtvalorh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtIdC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscaC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboTipoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cboCantPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtHoraReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHoraIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 3, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel11)
                                .addGap(23, 23, 23))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(dcFechaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dcFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(130, 130, 130))))
        );

        jScrollPane1.setViewportView(jPanel2);

        jPanel5.setBackground(new java.awt.Color(221, 234, 244));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Habitacion (es)"));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        TablaHabitacion.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        TablaHabitacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "N/A"
            }
        ));
        TablaHabitacion.setToolTipText("");
        TablaHabitacion.setGridColor(new java.awt.Color(135, 139, 220));
        TablaHabitacion.setSelectionBackground(new java.awt.Color(135, 139, 220));
        TablaHabitacion.setSelectionForeground(new java.awt.Color(0, 0, 0));
        TablaHabitacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaHabitacionMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TablaHabitacion);

        lblregistroConsumo.setText("-");

        lblTotalvalorH.setText("Precio Total $ : ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblTotalvalorH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotolReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblregistroConsumo)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblregistroConsumo)
                    .addComponent(lblTotalvalorH)
                    .addComponent(txtTotolReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        rSPanelGradiente3.setColorPrimario(new java.awt.Color(135, 139, 220));
        rSPanelGradiente3.setColorSecundario(new java.awt.Color(255, 255, 255));

        l3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/logoempresa1 (1).png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Reservas");

        javax.swing.GroupLayout rSPanelGradiente3Layout = new javax.swing.GroupLayout(rSPanelGradiente3);
        rSPanelGradiente3.setLayout(rSPanelGradiente3Layout);
        rSPanelGradiente3Layout.setHorizontalGroup(
            rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelGradiente3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        rSPanelGradiente3Layout.setVerticalGroup(
            rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelGradiente3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l3, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/nuevo.GIF"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 0, 0,80));

        TablaH.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        TablaH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaH.setToolTipText("Presiona clic derecho para más opciones");
        TablaH.setComponentPopupMenu(jPopupMenu1);
        TablaH.setGridColor(new java.awt.Color(135, 139, 220));
        TablaH.setSelectionBackground(new java.awt.Color(135, 139, 220));
        TablaH.setSelectionForeground(new java.awt.Color(0, 0, 0));
        TablaH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaHMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablaH);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        pupHabitacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/cama.png"))); // NOI18N
        pupHabitacion.setText("Asignar Habitación");
        pupHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pupHabitacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSPanelGradiente3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pupHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRealizarPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                                .addComponent(lblregistro))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rSPanelGradiente3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pupHabitacion))
                        .addGap(4, 4, 4)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblregistro)
                            .addComponent(jButton1)
                            .addComponent(btnConsumo)
                            .addComponent(btnRealizarPagos)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(0, 27, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo)
                            .addComponent(btnGuardar)
                            .addComponent(btnCancelar))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        habilitar();
        btnGuardar.setText("Guardar");
        accion = "guardar";
        txtIdE.setText(JFMenu.lblidpersona.getText());
        txtNombreE.setText(JFMenu.lblPresentacion.getText());
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        dcFechaReserva.setDate(sqlDate);
        hora();
        txtHoraReserva.setText(hora + ":" + minutos + ":" + segundo);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        if (txtIdC.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un Cliente");
            txtIdC.requestFocus();
            return;
        }
        if (cboTipoReserva.getSelectedIndex() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un Tipo de Reserva");
            cboTipoReserva.requestFocus();
            return;
        }
        if (cboCantPersonas.getSelectedIndex() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar una Cantidad de Persona");
            cboCantPersonas.requestFocus();
            return;
        }

        if (dcFechaIngreso.getDate().equals(false)) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar una Fecha de Ingreso");
            dcFechaIngreso.requestFocus();
            return;
        }
        if (dcFechaIngreso.getDate().equals(false)) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar una Fecha de Ingreso");
            dcFechaIngreso.requestFocus();
            return;
        }
        if (dcFechaSalida.getDate().equals(false)) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar una Fecha de Salida");
            dcFechaSalida.requestFocus();
            return;
        }

        if (txtDescripcion.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar una Descripcion");
            txtDescripcion.requestFocus();
            return;
        }
        if (txtHoraReserva.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar una Hora de Reserva");
            txtHoraReserva.requestFocus();
            return;
        }
        if (txtHoraIngreso.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar una Hora de Ingreso");
            txtHoraIngreso.requestFocus();
            return;
        }
        if (txtHoraSalida.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar una Hora de Salida");
            txtHoraSalida.requestFocus();
            return;
        }
        if (cboEstado.getSelectedIndex() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un Estado de Reserva");
            cboEstado.requestFocus();
            return;
        }

        Reserva dts = new Reserva();
        CReserva produ = new CReserva();
        dts.setIdCliente(Integer.parseInt(txtIdC.getText()));
        dts.setIdEmpleado(idUsuario);
        int seleccionado = cboTipoReserva.getSelectedIndex();
        dts.setTipoReserva((String) cboTipoReserva.getItemAt(seleccionado));
        seleccionado = cboCantPersonas.getSelectedIndex();
        dts.setPersona((String) cboCantPersonas.getItemAt(seleccionado));
        dts.setDescripcion(txtDescripcion.getText());

        Calendar cal;
        int d, m, a;
        cal = dcFechaReserva.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        dts.setFechaReserva(new Date(a, m, d));
        dts.setHoraReserva(txtHoraReserva.getText());

        cal = dcFechaIngreso.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        dts.setFechaIngreso(new Date(a, m, d));
        dts.setHoraIngreso(txtHoraIngreso.getText());

        cal = dcFechaSalida.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        dts.setFechaSalida(new Date(a, m, d));
        dts.setHoraSalida(txtHoraSalida.getText());

        seleccionado = cboEstado.getSelectedIndex();
        dts.setEstado((String) cboEstado.getItemAt(seleccionado));

        if (accion.equals("guardar")) {
            if (produ.insertar(dts)) {
                JOptionPane.showMessageDialog(rootPane, "La Reserva fue Registrada");
                mostrar("");
                inhabilitar();

            }

        } else if (accion.equals("editar")) {
            dts.setIdreserva(Integer.parseInt(txtidR.getText()));
            dts.setIdEmpleado(Integer.parseInt(txtIdE.getText()));
            if (produ.editar(dts)) {
                JOptionPane.showMessageDialog(rootPane, "La Reserva fue Actualizada");
                mostrar("");
                inhabilitar();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inhabilitar();
        btnGuardar.setText("Guardar");
        txtIdE.setText(JFMenu.lblidpersona.getText());
        txtNombreE.setText(JFMenu.lblPresentacion.getText());
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void TablaHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaHMouseClicked
        btnGuardar.setText("Actualizar");
        habilitar();
        accion = "editar";
        int fila = TablaH.rowAtPoint(evt.getPoint());
        txtidR.setText(TablaH.getValueAt(fila, 0).toString());
        txtIdC.setText(TablaH.getValueAt(fila, 1).toString());
        txtNombreC.setText(TablaH.getValueAt(fila, 2).toString());
        txtIdE.setText(TablaH.getValueAt(fila, 3).toString());
        txtNombreE.setText(TablaH.getValueAt(fila, 4).toString());
        cboTipoReserva.setSelectedItem(TablaH.getValueAt(fila, 5).toString());
        cboCantPersonas.setSelectedItem(TablaH.getValueAt(fila, 6).toString());
        txtDescripcion.setText(TablaH.getValueAt(fila, 7).toString());
        dcFechaReserva.setDate(Date.valueOf(TablaH.getValueAt(fila, 8).toString()));
        txtHoraReserva.setText(TablaH.getValueAt(fila, 9).toString());
        dcFechaIngreso.setDate(Date.valueOf(TablaH.getValueAt(fila, 10).toString()));
        txtHoraIngreso.setText(TablaH.getValueAt(fila, 11).toString());
        dcFechaSalida.setDate(Date.valueOf(TablaH.getValueAt(fila, 12).toString()));
        txtHoraSalida.setText(TablaH.getValueAt(fila, 13).toString());
        cboEstado.setSelectedItem(TablaH.getValueAt(fila, 14).toString());
        TablaHabitacion.setVisible(true);
        mostrarHabitacion(TablaH.getValueAt(fila, 0).toString());
        txtNombreC.setEnabled(true);
        btnBuscaC.setEnabled(true);
        btnConsumo.setEnabled(true);

    }//GEN-LAST:event_TablaHMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        mostrar(txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Map p = new HashMap();
        p.put("buscar", this.txtBuscar.getText());
        JasperReport report;
        JasperPrint print;
        try {

            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/Reportes/rptReservaGeneral.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reservas");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void popuEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popuEliminarActionPerformed
        if (!txtidR.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Seguro que quieres eliminar La Reserva?", "Confirmar", 2);
            if (confirmacion == 0) {
                CReserva cama = new CReserva();
                Reserva dts = new Reserva();
                dts.setIdreserva(Integer.parseInt(txtidR.getText()));
                cama.eliminar(dts);
                mostrar("");
                inhabilitar();
            }
        }
    }//GEN-LAST:event_popuEliminarActionPerformed

    private void btnConsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsumoActionPerformed
        int fila = TablaH.getSelectedRow();
        JFConsumo.idreserva = TablaH.getValueAt(fila, 0).toString();
        JFConsumo.cliente = TablaH.getValueAt(fila, 2).toString();

        JFConsumo form = new JFConsumo();
        JFMenu.Escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btnConsumoActionPerformed

    private void btnRealizarPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarPagosActionPerformed
        int fila = TablaH.getSelectedRow();
        JFPago.idReserva = TablaH.getValueAt(fila, 0).toString();
        JFPago.cliente = TablaH.getValueAt(fila, 2).toString();
        CDetalle_Reserva produ = new CDetalle_Reserva();
        JFPago.totalReserva = Double.valueOf(txtTotolReserva.getText());
        //  JFPago.idHabitacion = TablaH.getValueAt(fila, 1).toString();
        //  JFPago.habitacion = TablaH.getValueAt(fila, 2).toString();

        JFPago form = new JFPago();
        JFMenu.Escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btnRealizarPagosActionPerformed

    private void txtidRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidRActionPerformed

    private void cboTipoReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoReservaActionPerformed
        cboTipoReserva.transferFocus();
    }//GEN-LAST:event_cboTipoReservaActionPerformed

    private void txtIdCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdCActionPerformed

    private void txtNombreCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCActionPerformed

    private void txtIdEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdEActionPerformed

    private void txtNombreEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreEActionPerformed

    private void cboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEstadoActionPerformed

    private void btnBuscaCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaCActionPerformed
        JFVistaCliente form = new JFVistaCliente();
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btnBuscaCActionPerformed

    private void dcFechaSalidaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcFechaSalidaPropertyChange

    }//GEN-LAST:event_dcFechaSalidaPropertyChange

    private void dcFechaIngresoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcFechaIngresoPropertyChange

    }//GEN-LAST:event_dcFechaIngresoPropertyChange

    private void txtvalorhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtvalorhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvalorhActionPerformed

    private void TablaHabitacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaHabitacionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaHabitacionMouseClicked

    private void cboCantPersonasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCantPersonasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCantPersonasActionPerformed

    private void pupBajaHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pupBajaHabitacionesActionPerformed
        CHabitacion func3 = new CHabitacion();
        Habitacion dts3 = new Habitacion();
        dts3.setIdHabitacion(Integer.parseInt(txtidR.getText()));
        func3.desocuparAll(dts3);
    }//GEN-LAST:event_pupBajaHabitacionesActionPerformed

    private Connection connection = new Conexion().conectar();

    private void pupImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pupImprimirActionPerformed
        if (!txtidR.getText().equals("")) {
            Map p = new HashMap();
            p.put("idReserva", this.txtidR.getText());
            JasperReport report;
            JasperPrint print;
            try {
                report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                        + "/src/Reportes/rptReserva.jrxml");
                print = JasperFillManager.fillReport(report, p, connection);
                JasperViewer view = new JasperViewer(print, false);
                view.setTitle("Comprobante de Reserva");
                view.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_pupImprimirActionPerformed

    private void pupHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pupHabitacionActionPerformed
        int fila = TablaH.getSelectedRow();
        JFDetalle_Reserva.idreserva = TablaH.getValueAt(fila, 0).toString();
        JFDetalle_Reserva.cliente = TablaH.getValueAt(fila, 2).toString();
        Calendar cal;
        int d, m, a;
        cal = dcFechaIngreso.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        JFDetalle_Reserva.fechaIngreso = (new Date(a, m, d));
        cal = dcFechaSalida.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        JFDetalle_Reserva.fechaSalida = (new Date(a, m, d));

        JFDetalle_Reserva form = new JFDetalle_Reserva();
        JFMenu.Escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_pupHabitacionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaH;
    private javax.swing.JTable TablaHabitacion;
    private javax.swing.JButton btnBuscaC;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsumo;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRealizarPagos;
    private javax.swing.JComboBox cboCantPersonas;
    private javax.swing.JComboBox cboEstado;
    private javax.swing.JComboBox cboTipoReserva;
    private com.toedter.calendar.JDateChooser dcFechaIngreso;
    private com.toedter.calendar.JDateChooser dcFechaReserva;
    private com.toedter.calendar.JDateChooser dcFechaSalida;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel lblTotalvalorH;
    private javax.swing.JLabel lblregistro;
    private javax.swing.JLabel lblregistroConsumo;
    private javax.swing.JMenuItem popuEliminar;
    private javax.swing.JMenuItem pupBajaHabitaciones;
    private javax.swing.JButton pupHabitacion;
    private javax.swing.JMenuItem pupImprimir;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente3;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtHoraIngreso;
    private javax.swing.JTextField txtHoraReserva;
    private javax.swing.JTextField txtHoraSalida;
    public static javax.swing.JTextField txtIdC;
    public static javax.swing.JTextField txtIdE;
    public static javax.swing.JTextField txtNombreC;
    public static javax.swing.JTextField txtNombreE;
    private javax.swing.JTextField txtTotolReserva;
    private javax.swing.JTextField txtidR;
    public static javax.swing.JTextField txtvalorh;
    // End of variables declaration//GEN-END:variables

    class FondoPane9 extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/Files/fondo.png")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);

            super.paint(g);
        }
    }
}
