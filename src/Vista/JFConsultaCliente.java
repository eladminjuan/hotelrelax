/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.CCliente;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author jdgom
 */
public class JFConsultaCliente extends javax.swing.JInternalFrame {

    FondoPane11 fondo = new FondoPane11();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * Creates new form JFConsultaCliente
     */
    public JFConsultaCliente() {
        this.setContentPane(fondo);
        initComponents();
        inabilitar();
        mostrar("");
        txtBuscar.setEnabled(true);
    }
    
    void ocultarColumnas() {
        TablaH.getColumnModel().getColumn(0).setMaxWidth(0);
        TablaH.getColumnModel().getColumn(0).setMinWidth(0);
        TablaH.getColumnModel().getColumn(0).setPreferredWidth(0);
        TablaH.getColumnModel().getColumn(12).setMaxWidth(0);
        TablaH.getColumnModel().getColumn(12).setMinWidth(0);
        TablaH.getColumnModel().getColumn(12).setPreferredWidth(0);
        TablaH.getColumnModel().getColumn(13).setMaxWidth(0);
        TablaH.getColumnModel().getColumn(13).setMinWidth(0);
        TablaH.getColumnModel().getColumn(13).setPreferredWidth(0);
        TablaH.getColumnModel().getColumn(14).setMaxWidth(0);
        TablaH.getColumnModel().getColumn(14).setMinWidth(0);
        TablaH.getColumnModel().getColumn(14).setPreferredWidth(0);
    }
    
    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            CCliente produ = new CCliente();
            modelo = produ.mostrar(buscar);
            TablaH.setModel(modelo);
            ocultarColumnas();
            lblregistro.setText("Total Registros: " + Integer.toString(produ.totalregistros));
        } catch (Exception e) {
            System.err.println("Error en el llenado de la tabla... " + e);
            JOptionPane.showMessageDialog(null, "Error en el llenado de la tabla");
        }
    }

    void inabilitar() {
        dtcInicio.setEnabled(false);
        dtcfin.setEnabled(false);
        btnFecha.setEnabled(false);
        dtcInicio.setDate(null);
        dtcfin.setDate(null);
        txtBuscar.setEnabled(false);
    }

    void mostrar1() {
        try {
            DefaultTableModel modelo;
            CCliente produ = new CCliente();
            modelo = produ.mostrarConsulta1();
            TablaH.setModel(modelo);
            lblregistro.setText("Total Registros: " + Integer.toString(produ.totalregistros));
        } catch (Exception e) {
            System.err.println("Error en el llenado de la tabla... " + e);
            JOptionPane.showMessageDialog(null, "Error en el llenado de la tabla");
        }
    }

    void mostrar2() {
        try {
            DefaultTableModel modelo;
            CCliente produ = new CCliente();
            modelo = produ.mostrarConsulta2();
            TablaH.setModel(modelo);
            lblregistro.setText("Total Registros: " + Integer.toString(produ.totalregistros));
        } catch (Exception e) {
            System.err.println("Error en el llenado de la tabla... " + e);
            JOptionPane.showMessageDialog(null, "Error en el llenado de la tabla");
        }
    }

    void mostrar3() {
        try {
            DefaultTableModel modelo;
            CCliente produ = new CCliente();
            modelo = produ.mostrarConsulta3();
            TablaH.setModel(modelo);
            lblregistro.setText("Total Registros: " + Integer.toString(produ.totalregistros));
        } catch (Exception e) {
            System.err.println("Error en el llenado de la tabla... " + e);
            JOptionPane.showMessageDialog(null, "Error en el llenado de la tabla");
        }
    }

    void mostrar4() {

        try {
            DefaultTableModel modelo;
            CCliente produ = new CCliente();
            modelo = produ.mostrarConsulta4(sdf.format(dtcInicio.getDate()), sdf.format(dtcfin.getDate()));
            TablaH.setModel(modelo);
            lblregistro.setText("Total Registros: " + Integer.toString(produ.totalregistros));
        } catch (Exception e) {
            System.err.println("Error en el llenado de la tabla... " + e);
            JOptionPane.showMessageDialog(null, "Error en el llenado de la tabla");
        }
    }

    void mostrar5() {

        try {
            DefaultTableModel modelo;
            CCliente produ = new CCliente();
            modelo = produ.mostrarConsulta5(sdf.format(dtcInicio.getDate()));
            TablaH.setModel(modelo);
            lblregistro.setText("Total Registros: " + Integer.toString(produ.totalregistros));
        } catch (Exception e) {
            System.err.println("Error en el llenado de la tabla... " + e);
            JOptionPane.showMessageDialog(null, "Error en el llenado de la tabla");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new FondoPane11();
        rSPanelGradiente1 = new rspanelgradiente.RSPanelGradiente();
        l1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaH = new javax.swing.JTable();
        lblregistro = new javax.swing.JLabel();
        jPanel2 = new FondoPane11();
        cboConsulta = new javax.swing.JComboBox();
        dtcInicio = new com.toedter.calendar.JDateChooser();
        dtcfin = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnFecha = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtBuscar = new java.awt.TextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Consulta Clientes");

        jPanel1.setBackground(new java.awt.Color(51, 255, 0));

        rSPanelGradiente1.setColorPrimario(new java.awt.Color(135, 139, 220));
        rSPanelGradiente1.setColorSecundario(new java.awt.Color(255, 255, 255));

        l1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/logoempresa1 (1).png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sección Consulta Cliente");

        javax.swing.GroupLayout rSPanelGradiente1Layout = new javax.swing.GroupLayout(rSPanelGradiente1);
        rSPanelGradiente1.setLayout(rSPanelGradiente1Layout);
        rSPanelGradiente1Layout.setHorizontalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelGradiente1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 553, Short.MAX_VALUE)
                .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        rSPanelGradiente1Layout.setVerticalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelGradiente1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l1)))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0,80));

        jScrollPane3.setBackground(new java.awt.Color(0, 0, 0,80));
        jScrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 102), 1, true));

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
        TablaH.setGridColor(new java.awt.Color(182, 191, 240));
        TablaH.setSelectionBackground(new java.awt.Color(182, 191, 240));
        TablaH.setSelectionForeground(new java.awt.Color(0, 0, 0));
        TablaH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaHMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablaH);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblregistro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblregistro.setText("Total Registros:");

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        cboConsulta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cboConsulta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Cantidad de Registros por Dia", "Cantidad de Registros por Mes", "Cantidad de Registros por Año", "Cantidad de Registros entre 2 Fechas", "Cantidad de Registros entre una Fecha hasta la Actualidad" }));
        cboConsulta.setBorder(null);
        cboConsulta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboConsultaItemStateChanged(evt);
            }
        });

        dtcInicio.setDateFormatString("yyyy/MM/dd");

        dtcfin.setDateFormatString("yyyy/MM/dd");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Fecha Inicial:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Final:");

        btnFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/buscar.png"))); // NOI18N
        btnFecha.setText("Buscar");
        btnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Buscar:");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cboConsulta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(dtcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dtcfin, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(cboConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dtcInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtcfin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLabel2.setText("-");

        jLabel3.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblregistro))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(rSPanelGradiente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblregistro)
                .addGap(22, 22, 22))
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

    private void TablaHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaHMouseClicked

    }//GEN-LAST:event_TablaHMouseClicked

    private void cboConsultaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboConsultaItemStateChanged
        String item = (String) cboConsulta.getSelectedItem();
        if (item.equals("Seleccione")) {
            inabilitar();
            txtBuscar.setEnabled(true);
            mostrar("");
            /* DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
             for (int i = 0; i < TablaH.getRowCount(); i++) {
             dtsc.setValue(i, item, item); 
             }*/
        }
        if (item.equals("Cantidad de Registros por Mes")) {
            inabilitar();
            mostrar1();
            /* DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
             for (int i = 0; i < TablaH.getRowCount(); i++) {
             dtsc.setValue(i, item, item); 
             }*/
        }
        if (item.equals("Cantidad de Registros por Año")) {
            inabilitar();
            mostrar2();
            /* DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
             for (int i = 0; i < TablaH.getRowCount(); i++) {
             dtsc.setValue(i, item, item); 
             }*/
        }
        if (item.equals("Cantidad de Registros por Dia")) {
            inabilitar();
            mostrar3();
            /* DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
             for (int i = 0; i < TablaH.getRowCount(); i++) {
             dtsc.setValue(i, item, item); 
             }*/
        }
        if (item.equals("Cantidad de Registros entre 2 Fechas")) {
            inabilitar();
            dtcInicio.setEnabled(true);
            dtcfin.setEnabled(true);
            btnFecha.setEnabled(true);

        }
        if (item.equals("Cantidad de Registros entre una Fecha hasta la Actualidad")) {
            inabilitar();
            dtcInicio.setEnabled(true);
            btnFecha.setVisible(true);
            btnFecha.setEnabled(true);

        }
    }//GEN-LAST:event_cboConsultaItemStateChanged

    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
        String item = (String) cboConsulta.getSelectedItem();

        if (item.equals("Cantidad de Registros entre 2 Fechas")) {
            if (dtcInicio.getDate().equals(false)) {
                JOptionPane.showConfirmDialog(rootPane, "Debes ingresar una Fecha de Inicio");
                dtcInicio.requestFocus();
                return;
            }
            if (dtcfin.getDate().equals(false)) {
                JOptionPane.showConfirmDialog(rootPane, "Debes ingresar una Fecha Final");
                dtcfin.requestFocus();
                return;
            }
            mostrar4();
        }
        if (item.equals("Cantidad de Registros entre una Fecha hasta la Actualidad")) {
            if (dtcInicio.getDate().equals(false)) {
                JOptionPane.showConfirmDialog(rootPane, "Debes ingresar una Fecha de Inicio");
                dtcInicio.requestFocus();
                return;
            }
            mostrar5();

        }
    }//GEN-LAST:event_btnFechaActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        mostrar(txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarKeyReleased

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
            java.util.logging.Logger.getLogger(JFConsultaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFConsultaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFConsultaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFConsultaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFConsultaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaH;
    private javax.swing.JButton btnFecha;
    private javax.swing.JComboBox cboConsulta;
    private com.toedter.calendar.JDateChooser dtcInicio;
    private com.toedter.calendar.JDateChooser dtcfin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel lblregistro;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente1;
    private java.awt.TextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    class FondoPane11 extends JPanel {

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
