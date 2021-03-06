/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CONTROL.ControlVenda;
import CONTROL.Principal;

/**
 *
 * @author Victor
 */
public class FormVenda extends javax.swing.JFrame {

    /**
     * Creates new form FormVenda
     */
    public FormVenda() {
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
        cbxOrcamentoVenda = new javax.swing.JComboBox<>();
        btnCancelarVenda = new javax.swing.JButton();
        btnSairVenda = new javax.swing.JButton();
        btnConfirmarVenda = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(575, 200));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel3.setText("Orçamento (*)");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 51, 170, 20);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setText("Venda");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(10, 11, 170, 29);

        jLabel4.setText("Campos Obrigatórios  (*)");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(390, 31, 160, 14);

        getContentPane().add(cbxOrcamentoVenda);
        cbxOrcamentoVenda.setBounds(10, 71, 540, 20);

        btnCancelarVenda.setText("Cancelar");
        btnCancelarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVendaActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelarVenda);
        btnCancelarVenda.setBounds(50, 121, 90, 23);

        btnSairVenda.setText("Sair");
        btnSairVenda.setName(""); // NOI18N
        btnSairVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairVendaActionPerformed(evt);
            }
        });
        getContentPane().add(btnSairVenda);
        btnSairVenda.setBounds(240, 121, 70, 23);

        btnConfirmarVenda.setText("Confirmar");
        btnConfirmarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarVendaActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfirmarVenda);
        btnConfirmarVenda.setBounds(420, 121, 100, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVendaActionPerformed
        Principal.mostrarTelaInicio();
        ControlVenda.limparCampos();
    }//GEN-LAST:event_btnCancelarVendaActionPerformed

    private void btnSairVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairVendaActionPerformed
        Principal.mostrarTelaInicio();
        ControlVenda.limparCampos();
    }//GEN-LAST:event_btnSairVendaActionPerformed

    private void btnConfirmarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarVendaActionPerformed
        if(ControlVenda.editando == false)
            ControlVenda.cadastrar();
        else
            ControlVenda.editar(ControlVenda.idAux);
    }//GEN-LAST:event_btnConfirmarVendaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal.mostrarTelaInicio();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancelarVenda;
    public javax.swing.JButton btnConfirmarVenda;
    public javax.swing.JButton btnSairVenda;
    public static javax.swing.JComboBox<String> cbxOrcamentoVenda;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
