/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Habitacion;
import Modelo.Pago;
import Modelo.Reserva;
import Control.CConsumo;
import Control.CHabitacion;
import Control.CPago;
import Control.CReserva;
import Control.Conexion;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
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
public class JFPago extends javax.swing.JInternalFrame {

    FondoPane8 fondo = new FondoPane8();

    String hora, minutos, segundo;

    /**
     * Creates new form JFPago
     */
    public JFPago() {
        this.setContentPane(fondo);
        initComponents();
        mostrar(idReserva);
        inhabilitar();
        txtIdReserva.setText(idReserva);
        txtCliente.setText(cliente);
        txtTotalReserva.setText(Double.toString(totalReserva));

        CConsumo func = new CConsumo();
        func.mostrar(idReserva);
        impuesto = (totalReserva + func.totalconsumo) * 0.19;
        txtImpuesto.setText(Double.toString(impuesto));
        txtTotalPago.setText(Double.toString(totalReserva + func.totalconsumo + impuesto));
        txtTotalPago1.setText(Double.toString(0.0));
        txtSaldoActual.setText(Double.toString(totalReserva + func.totalconsumo + impuesto));

        setVisible(true);
    }

    private String accion = "guardar";
    public static String idReserva;
    public static String cliente;
    public static Double totalReserva;
    public static Double impuesto;

    void hora() {
        Calendar calendario = new GregorianCalendar();
        java.util.Date horaActual = new java.util.Date();
        calendario.setTime(horaActual);
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundo = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    void ocultarColumnas() {
        TablaH.getColumnModel().getColumn(0).setMaxWidth(0);
        TablaH.getColumnModel().getColumn(0).setMinWidth(0);
        TablaH.getColumnModel().getColumn(0).setPreferredWidth(0);
        TablaH.getColumnModel().getColumn(1).setMaxWidth(0);
        TablaH.getColumnModel().getColumn(1).setMinWidth(0);
        TablaH.getColumnModel().getColumn(1).setPreferredWidth(0);
    }

    void ocultarColumnasConsumo() {
        TablaHConsumo.getColumnModel().getColumn(0).setMaxWidth(0);
        TablaHConsumo.getColumnModel().getColumn(0).setMinWidth(0);
        TablaHConsumo.getColumnModel().getColumn(0).setPreferredWidth(0);
        TablaHConsumo.getColumnModel().getColumn(1).setMaxWidth(0);
        TablaHConsumo.getColumnModel().getColumn(1).setMinWidth(0);
        TablaHConsumo.getColumnModel().getColumn(1).setPreferredWidth(0);
        TablaHConsumo.getColumnModel().getColumn(2).setMaxWidth(0);
        TablaHConsumo.getColumnModel().getColumn(2).setMinWidth(0);
        TablaHConsumo.getColumnModel().getColumn(2).setPreferredWidth(0);
    }

    void inhabilitar() {
        txtidPago.setVisible(false);
        txtIdReserva.setVisible(false);
        txtCliente.setEnabled(false);
        txtNumeroComprobante.setEnabled(false);
        cboTipocomproba.setEnabled(false);
        txtImpuesto.setEnabled(false);
        txtTotalPago.setEnabled(false);
        txtTotalPago1.setEnabled(false);
        txtTotalReserva.setEnabled(false);
        dtFechaEmision.setEnabled(false);
        dtFechaPago.setEnabled(false);
        txtSaldoActual.setEnabled(false);
        txtSaldoPagado.setEnabled(false);
        txtHoraEmision.setEnabled(false);
        txtHoraPago.setEnabled(false);

        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnPagarAbonar.setEnabled(false);
        txtidPago.setText("");
        txtNumeroComprobante.setText("");
        txtSaldoPagado.setText("");
        txtHoraPago.setText("");
        txtImpuesto.setText("");
        txtTotalPago.setText("");
        txtTotalPago1.setText("");
        txtSaldoActual.setText("");
        txtHoraEmision.setText("");
        dtFechaPago.setDate(null);
        dtFechaEmision.setDate(null);
        cboTipocomproba.setSelectedIndex(0);
    }

    void habilitar() {
        txtidPago.setVisible(false);
        txtIdReserva.setVisible(false);
        txtCliente.setEnabled(true);
        txtNumeroComprobante.setEnabled(false);
        cboTipocomproba.setEnabled(true);
        txtImpuesto.setEnabled(false);
        txtTotalPago.setEnabled(false);
        txtTotalPago1.setEnabled(false);
        txtTotalReserva.setEnabled(false);
        dtFechaEmision.setEnabled(false);
        dtFechaPago.setEnabled(false);
        txtSaldoActual.setEnabled(false);
        txtSaldoPagado.setEnabled(false);
        txtHoraEmision.setEnabled(false);
        txtHoraPago.setEnabled(false);

        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnPagarAbonar.setEnabled(true);
        txtidPago.setText("");
        txtNumeroComprobante.setText("");
        txtSaldoPagado.setText("");
        txtHoraPago.setText("");
        txtHoraEmision.setText("");
        dtFechaPago.setDate(null);
        dtFechaEmision.setDate(null);
        cboTipocomproba.setSelectedIndex(0);
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            CPago produ = new CPago();
            modelo = produ.mostrar(buscar);
            TablaH.setModel(modelo);
            ocultarColumnas();
            lblregistro.setText("Total Pagos: " + Integer.toString(produ.totalregistros));

            CConsumo produ2 = new CConsumo();
            modelo = produ2.mostrar(buscar);
            TablaHConsumo.setModel(modelo);
            ocultarColumnas();
            lblregistroConsumo.setText("Total Consumos: " + produ2.totalregistros);
            lblTotalConsumo.setText("Consumo Total: $." + produ2.totalconsumo);

        } catch (Exception e) {
            System.err.println("Error en el llenado de la tabla... " + e);
            JOptionPane.showMessageDialog(null, "Error en el llenado de la tabla");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        pupEliminar = new javax.swing.JMenuItem();
        pupImprimir = new javax.swing.JMenuItem();
        jPanel1 = new FondoPane8();
        jPanel4 = new FondoPane8();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtidPago = new javax.swing.JTextField();
        txtIdReserva = new javax.swing.JTextField();
        txtImpuesto = new javax.swing.JTextField();
        cboTipocomproba = new javax.swing.JComboBox();
        btnNuevo2 = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtCliente = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTotalReserva = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtNumeroComprobante = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTotalPago = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        dtFechaEmision = new com.toedter.calendar.JDateChooser();
        dtFechaPago = new com.toedter.calendar.JDateChooser();
        txtSaldoActual = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtSaldoPagado = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        btnPagarAbonar = new javax.swing.JButton();
        txtHoraEmision = new javax.swing.JTextField();
        txtHoraPago = new javax.swing.JTextField();
        txtTotalPago1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        lblregistro = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaHConsumo = new javax.swing.JTable();
        lblregistroConsumo = new javax.swing.JLabel();
        lblTotalConsumo = new javax.swing.JLabel();
        rSPanelGradiente3 = new rspanelgradiente.RSPanelGradiente();
        l3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaH = new javax.swing.JTable();

        pupEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/eliminar.png"))); // NOI18N
        pupEliminar.setText("Eliminar");
        pupEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pupEliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(pupEliminar);

        pupImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/impresora.png"))); // NOI18N
        pupImprimir.setText("Imprimir");
        pupImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pupImprimirActionPerformed(evt);
            }
        });
        jPopupMenu1.add(pupImprimir);

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("ID:");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Reserva:");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Impuesto:");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Tipo Comprob:");

        txtidPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidPagoActionPerformed(evt);
            }
        });

        txtIdReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdReservaActionPerformed(evt);
            }
        });

        txtImpuesto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtImpuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImpuestoActionPerformed(evt);
            }
        });

        cboTipocomproba.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Boleta", "Factura", "Ticket", "Otro" }));
        cboTipocomproba.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTipocomprobaItemStateChanged(evt);
            }
        });
        cboTipocomproba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboTipocomprobaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cboTipocomprobaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboTipocomprobaMousePressed(evt);
            }
        });
        cboTipocomproba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipocomprobaActionPerformed(evt);
            }
        });
        cboTipocomproba.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboTipocomprobaPropertyChange(evt);
            }
        });

        btnNuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/nuevo.GIF"))); // NOI18N
        btnNuevo2.setText("Nuevo");
        btnNuevo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo2ActionPerformed(evt);
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

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Total Reserva:");

        txtTotalReserva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Num Comprob:");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("Total a Pagar:");

        txtTotalPago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPagoActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText("Fecha Emision:");

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText("Fecha Pago:");

        txtSaldoActual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSaldoActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoActualActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel23.setText("Saldo Actual:");

        txtSaldoPagado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSaldoPagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoPagadoActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setText("Pagar:");

        btnPagarAbonar.setText("Pagar/Abonar");
        btnPagarAbonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarAbonarActionPerformed(evt);
            }
        });

        txtHoraEmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraEmisionActionPerformed(evt);
            }
        });

        txtHoraPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraPagoActionPerformed(evt);
            }
        });

        txtTotalPago1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalPago1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPago1ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setText("Total Pago:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel16)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel15)
                    .addComponent(jLabel20)
                    .addComponent(jLabel25)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtImpuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(txtTotalPago)
                            .addComponent(txtTotalPago1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(txtSaldoActual, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(txtSaldoPagado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPagarAbonar)
                        .addGap(66, 66, 66))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTipocomproba, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(dtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                    .addComponent(txtHoraEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(dtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtHoraPago, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidPago, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnNuevo2, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addGap(50, 50, 50))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtidPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtIdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTotalReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cboTipocomproba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtHoraEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(txtHoraPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalPago1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSaldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSaldoPagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(btnPagarAbonar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo2)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        lblregistro.setText("Registro");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/impresora.png"))); // NOI18N
        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(221, 234, 244));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Consumos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 204));

        TablaHConsumo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        TablaHConsumo.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaHConsumo.setGridColor(new java.awt.Color(135, 139, 220));
        TablaHConsumo.setSelectionBackground(new java.awt.Color(135, 139, 220));
        TablaHConsumo.setSelectionForeground(new java.awt.Color(0, 0, 0));
        TablaHConsumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaHConsumoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TablaHConsumo);

        lblregistroConsumo.setText("Registro");

        lblTotalConsumo.setText("consumo");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTotalConsumo)
                        .addGap(80, 80, 80)
                        .addComponent(lblregistroConsumo)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblregistroConsumo)
                    .addComponent(lblTotalConsumo))
                .addGap(37, 37, 37))
        );

        rSPanelGradiente3.setColorPrimario(new java.awt.Color(135, 139, 220));
        rSPanelGradiente3.setColorSecundario(new java.awt.Color(255, 255, 255));

        l3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/logoempresa1 (1).png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Pagos");

        javax.swing.GroupLayout rSPanelGradiente3Layout = new javax.swing.GroupLayout(rSPanelGradiente3);
        rSPanelGradiente3.setLayout(rSPanelGradiente3Layout);
        rSPanelGradiente3Layout.setHorizontalGroup(
            rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelGradiente3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        rSPanelGradiente3Layout.setVerticalGroup(
            rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelGradiente3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l3, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0,80));

        jScrollPane4.setBorder(null);

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
        TablaH.setColumnSelectionAllowed(true);
        TablaH.setComponentPopupMenu(jPopupMenu1);
        TablaH.setGridColor(new java.awt.Color(135, 139, 220));
        TablaH.setSelectionBackground(new java.awt.Color(135, 139, 220));
        TablaH.setSelectionForeground(new java.awt.Color(0, 0, 0));
        TablaH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaHMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TablaH);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(rSPanelGradiente3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblregistro)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rSPanelGradiente3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(lblregistro))))
                .addGap(12, 12, 12))
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

    private void txtidPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidPagoActionPerformed

    private void txtIdReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdReservaActionPerformed
        txtIdReserva.transferFocus();
    }//GEN-LAST:event_txtIdReservaActionPerformed

    private void txtImpuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImpuestoActionPerformed
        txtImpuesto.transferFocus();
    }//GEN-LAST:event_txtImpuestoActionPerformed

    private void cboTipocomprobaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipocomprobaActionPerformed
        cboTipocomproba.transferFocus();
    }//GEN-LAST:event_cboTipocomprobaActionPerformed

    private void btnNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo2ActionPerformed
        habilitar();
        btnGuardar.setText("Guardar");
        accion = "guardar";
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        dtFechaEmision.setDate(sqlDate);
        hora();
        txtHoraEmision.setText(hora + ":" + minutos + ":" + segundo);
    }//GEN-LAST:event_btnNuevo2ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtImpuesto.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un valor de Impuesto");
            txtImpuesto.requestFocus();
            return;
        }
        if (txtNumeroComprobante.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un numero de comprobante");
            txtNumeroComprobante.requestFocus();
            return;
        }
        if (txtTotalPago.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un total de Pago");
            txtTotalPago.requestFocus();
            return;
        }
        if (txtSaldoPagado.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes Pagar o Abonar para Guardar");
            btnPagarAbonar.requestFocus();
            return;
        }
        if (cboTipocomproba.getSelectedIndex() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un Tipo de Comprobante");
            cboTipocomproba.requestFocus();
            return;
        }

        Pago dts = new Pago();
        CPago produ = new CPago();
        dts.setIdReserva(Integer.parseInt(txtIdReserva.getText()));
        int seleccionado = cboTipocomproba.getSelectedIndex();
        dts.setTipoComprobante((String) cboTipocomproba.getItemAt(seleccionado));
        dts.setNumeroComprobante(txtNumeroComprobante.getText());
        dts.setImpuesto(Double.parseDouble(txtImpuesto.getText()));
        dts.setTotalPago(Double.parseDouble(txtTotalPago.getText()));
        dts.setTotalPagado(Double.parseDouble(txtTotalPago1.getText()));
        Calendar cal;
        int d, m, a;
        cal = dtFechaPago.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        dts.setFechaPago(new Date(a, m, d));
        dts.setHoraPago(txtHoraPago.getText());
        cal = dtFechaEmision.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        dts.setFechaEmision(new Date(a, m, d));
        dts.setHoraEmision(txtHoraEmision.getText());
        dts.setSaldoActual(Double.parseDouble(txtSaldoActual.getText()));

        if (accion.equals("guardar")) {
            if (produ.insertar(dts)) {
                JOptionPane.showMessageDialog(rootPane, "El Pago por $. " + txtTotalPago.getText() + " del Sr. " + txtCliente.getText() + "\n ha sido realizado correctamente");
                mostrar(idReserva);

                if (txtSaldoActual.getText().equals("0.00")) {
                    CReserva func3 = new CReserva();
                    Reserva dts3 = new Reserva();
                    dts3.setIdreserva(Integer.parseInt(txtIdReserva.getText()));
                    func3.pagar(dts3);
                }
                inhabilitar();
            }

        } else if (accion.equals("editar")) {
            dts.setIdPago(Integer.parseInt(txtidPago.getText()));
            if (produ.editar(dts)) {
                JOptionPane.showMessageDialog(rootPane, "El Pago del Sr. " + txtCliente.getText() + ", fue modificado correctamente");
                mostrar(idReserva);
                if (txtSaldoActual.getText().equals("0.00")) {
                    CReserva func3 = new CReserva();
                    Reserva dts3 = new Reserva();
                    dts3.setIdreserva(Integer.parseInt(txtIdReserva.getText()));
                    func3.pagar(dts3);
                }
                inhabilitar();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inhabilitar();
        btnGuardar.setText("Guardar");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void TablaHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaHMouseClicked
        btnGuardar.setText("Actualizar");
        habilitar();
        accion = "editar";
        int fila = TablaH.rowAtPoint(evt.getPoint());
        txtidPago.setText(TablaH.getValueAt(fila, 0).toString());
        // txtIdReserva.setText(TablaH.getValueAt(fila, 1).toString());
        cboTipocomproba.setSelectedItem(TablaH.getValueAt(fila, 2).toString());
        txtNumeroComprobante.setText(TablaH.getValueAt(fila, 3).toString());
        dtFechaEmision.setDate(Date.valueOf(TablaH.getValueAt(fila, 4).toString()));
        txtHoraEmision.setText(TablaH.getValueAt(fila, 5).toString());
        dtFechaPago.setDate(Date.valueOf(TablaH.getValueAt(fila, 6).toString()));
        txtHoraPago.setText(TablaH.getValueAt(fila, 7).toString());
        txtImpuesto.setText(TablaH.getValueAt(fila, 8).toString());
        txtTotalPago.setText(TablaH.getValueAt(fila, 9).toString());
        txtTotalPago1.setText(TablaH.getValueAt(fila, 10).toString());
        txtSaldoActual.setText(TablaH.getValueAt(fila, 11).toString());

    }//GEN-LAST:event_TablaHMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       Map p = new HashMap();
        p.put("buscar", this.txtIdReserva.getText());
        JasperReport report;
        JasperPrint print;
        try {

            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/Reportes/rptPagpGeneral.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte Pagos");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTotalPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPagoActionPerformed

    private void TablaHConsumoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaHConsumoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaHConsumoMouseClicked

    private void pupEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pupEliminarActionPerformed
        if (!txtidPago.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Seguro que quieres eliminar El Pago seleccionado?", "Confirmar", 2);
            if (confirmacion == 0) {
                CPago cama = new CPago();
                Pago dts = new Pago();
                dts.setIdPago(Integer.parseInt(txtidPago.getText()));
                cama.eliminar(dts);
                mostrar(idReserva);
                inhabilitar();
            }
        }
    }//GEN-LAST:event_pupEliminarActionPerformed

    private void txtSaldoActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldoActualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoActualActionPerformed

    private void txtSaldoPagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldoPagadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoPagadoActionPerformed

    private void txtHoraEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraEmisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraEmisionActionPerformed

    private void txtHoraPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraPagoActionPerformed

    private void btnPagarAbonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarAbonarActionPerformed
        JFVistaPago form = new JFVistaPago();
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btnPagarAbonarActionPerformed

    private void cboTipocomprobaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTipocomprobaMouseExited

    }//GEN-LAST:event_cboTipocomprobaMouseExited

    private void cboTipocomprobaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboTipocomprobaPropertyChange

    }//GEN-LAST:event_cboTipocomprobaPropertyChange

    private void cboTipocomprobaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTipocomprobaMousePressed

    }//GEN-LAST:event_cboTipocomprobaMousePressed

    private void cboTipocomprobaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTipocomprobaMouseClicked

    }//GEN-LAST:event_cboTipocomprobaMouseClicked

    private void cboTipocomprobaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTipocomprobaItemStateChanged
        @SuppressWarnings("UnusedAssignment")
        String contraseña = "";
        @SuppressWarnings("UnusedAssignment")
        String contraseña2 = "";
        contraseña = UUID.randomUUID().toString().toUpperCase().substring(0, 5);
        contraseña2 = UUID.randomUUID().toString().toLowerCase().substring(0, 5);
        String item = (String) cboTipocomproba.getSelectedItem();
        if (item.equals("Boleta")) {
            txtNumeroComprobante.setText("BO-" + contraseña + contraseña2);
        }
        if (item.equals("Factura")) {
            txtNumeroComprobante.setText("FA-" + contraseña + contraseña2);
        }
        if (item.equals("Ticket")) {
            txtNumeroComprobante.setText("TI-" + contraseña + contraseña2);
        }
        if (item.equals("Otro")) {
            txtNumeroComprobante.setText("OT-" + contraseña + contraseña2);
        }
        if (item.equals("Seleccione")) {
            txtNumeroComprobante.setText("");
        }
    }//GEN-LAST:event_cboTipocomprobaItemStateChanged

    private void txtTotalPago1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPago1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPago1ActionPerformed

    private Connection connection = new Conexion().conectar();

    private void pupImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pupImprimirActionPerformed
        if (!txtidPago.getText().equals("")) {
            Map p = new HashMap();
            p.put("idPago", this.txtidPago.getText());
            JasperReport report;
            JasperPrint print;
            try {
                report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                        + "/src/Reportes/reportepago.jrxml");
                print = JasperFillManager.fillReport(report, p, connection);
                JasperViewer view = new JasperViewer(print, false);
                view.setTitle("Comprobante de Pago");
                view.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_pupImprimirActionPerformed

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
            java.util.logging.Logger.getLogger(JFPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPago().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaH;
    private javax.swing.JTable TablaHConsumo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo2;
    private javax.swing.JButton btnPagarAbonar;
    private javax.swing.JComboBox cboTipocomproba;
    private com.toedter.calendar.JDateChooser dtFechaEmision;
    public static com.toedter.calendar.JDateChooser dtFechaPago;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel lblTotalConsumo;
    private javax.swing.JLabel lblregistro;
    private javax.swing.JLabel lblregistroConsumo;
    private javax.swing.JMenuItem pupEliminar;
    private javax.swing.JMenuItem pupImprimir;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente3;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtHoraEmision;
    public static javax.swing.JTextField txtHoraPago;
    private javax.swing.JTextField txtIdReserva;
    private javax.swing.JTextField txtImpuesto;
    private javax.swing.JTextField txtNumeroComprobante;
    public static javax.swing.JTextField txtSaldoActual;
    public static javax.swing.JTextField txtSaldoPagado;
    private javax.swing.JTextField txtTotalPago;
    public static javax.swing.JTextField txtTotalPago1;
    private javax.swing.JTextField txtTotalReserva;
    private javax.swing.JTextField txtidPago;
    // End of variables declaration//GEN-END:variables

    class FondoPane8 extends JPanel {

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
