/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CONTROL.ControlPartes;
import CONTROL.Principal;

/**
 *
 * @author Victor
 */
public class FormPartes extends javax.swing.JFrame {

    /**
     * Creates new form FormPartes
     */
    public FormPartes() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNomePartes = new javax.swing.JTextField();
        txtPrecoPartes = new javax.swing.JTextField();
        btnCancelarPartes = new javax.swing.JButton();
        btnConfirmarPartes = new javax.swing.JButton();
        btnSairPartes = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(575, 240));
        setMinimumSize(new java.awt.Dimension(575, 240));
        setPreferredSize(new java.awt.Dimension(575, 240));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel3.setText("Nome (*)");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 51, 170, 20);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setText("Partes");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(10, 11, 241, 29);

        jLabel4.setText("Campos Obrigatórios  (*)");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(390, 31, 160, 14);

        jLabel5.setText("Preço (*)");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 110, 170, 20);
        getContentPane().add(txtNomePartes);
        txtNomePartes.setBounds(10, 71, 540, 20);
        getContentPane().add(txtPrecoPartes);
        txtPrecoPartes.setBounds(10, 130, 540, 20);

        btnCancelarPartes.setText("Cancelar");
        btnCancelarPartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPartesActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelarPartes);
        btnCancelarPartes.setBounds(40, 170, 90, 23);

        btnConfirmarPartes.setText("Confirmar");
        btnConfirmarPartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarPartesActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfirmarPartes);
        btnConfirmarPartes.setBounds(410, 170, 100, 23);

        btnSairPartes.setText("Sair");
        btnSairPartes.setName(""); // NOI18N
        btnSairPartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairPartesActionPerformed(evt);
            }
        });
        getContentPane().add(btnSairPartes);
        btnSairPartes.setBounds(230, 170, 70, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarPartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPartesActionPerformed
        Principal.mostrarTelaInicio();
                ControlPartes.limparCampos();
    }//GEN-LAST:event_btnCancelarPartesActionPerformed

    private void btnConfirmarPartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarPartesActionPerformed
                if(ControlPartes.editando == false)
                    ControlPartes.cadastrar();
                else
                    ControlPartes.editar(ControlPartes.idAux);
    }//GEN-LAST:event_btnConfirmarPartesActionPerformed

    private void btnSairPartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairPartesActionPerformed
        Principal.mostrarTelaInicio();
                ControlPartes.limparCampos();
    }//GEN-LAST:event_btnSairPartesActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal.mostrarTelaInicio();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancelarPartes;
    public javax.swing.JButton btnConfirmarPartes;
    public javax.swing.JButton btnSairPartes;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JTextField txtNomePartes;
    public static javax.swing.JTextField txtPrecoPartes;
    // End of variables declaration//GEN-END:variables
}
