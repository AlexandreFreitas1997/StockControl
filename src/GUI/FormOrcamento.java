/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CONTROL.ControlOrcamento;
import CONTROL.Principal;

/**
 *
 * @author Victor
 */
public class FormOrcamento extends javax.swing.JFrame {

    /**
     * Creates new form FormOrcamento
     */
    public FormOrcamento() {
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
        cbxPedidoOrcamento = new javax.swing.JComboBox<String>();
        btnCancelarOrcamento = new javax.swing.JButton();
        btnSairOrcamento = new javax.swing.JButton();
        btnConfirmarOrcamento = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(575, 200));
        setMinimumSize(new java.awt.Dimension(575, 200));
        setPreferredSize(new java.awt.Dimension(575, 200));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel3.setText("Pedido (*)");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 51, 170, 20);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setText("Orçamento");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(10, 11, 170, 29);

        jLabel4.setText("Campos Obrigatórios  (*)");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(390, 31, 160, 14);

        getContentPane().add(cbxPedidoOrcamento);
        cbxPedidoOrcamento.setBounds(10, 71, 540, 20);

        btnCancelarOrcamento.setText("Cancelar");
        btnCancelarOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarOrcamentoActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelarOrcamento);
        btnCancelarOrcamento.setBounds(50, 121, 90, 23);

        btnSairOrcamento.setText("Sair");
        btnSairOrcamento.setName(""); // NOI18N
        btnSairOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairOrcamentoActionPerformed(evt);
            }
        });
        getContentPane().add(btnSairOrcamento);
        btnSairOrcamento.setBounds(240, 121, 70, 23);

        btnConfirmarOrcamento.setText("Confirmar");
        btnConfirmarOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarOrcamentoActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfirmarOrcamento);
        btnConfirmarOrcamento.setBounds(420, 121, 100, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarOrcamentoActionPerformed
                Principal.mostrarTelaInicio();
                ControlOrcamento.limparCampos();
    }//GEN-LAST:event_btnCancelarOrcamentoActionPerformed

    private void btnSairOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairOrcamentoActionPerformed
                Principal.mostrarTelaInicio();
                ControlOrcamento.limparCampos();
    }//GEN-LAST:event_btnSairOrcamentoActionPerformed

    private void btnConfirmarOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarOrcamentoActionPerformed
                if(ControlOrcamento.editando == false)
                    ControlOrcamento.cadastrar();
                else
                    ControlOrcamento.editar(ControlOrcamento.idAux);
    }//GEN-LAST:event_btnConfirmarOrcamentoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal.mostrarTelaInicio();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancelarOrcamento;
    public javax.swing.JButton btnConfirmarOrcamento;
    public javax.swing.JButton btnSairOrcamento;
    public static javax.swing.JComboBox<String> cbxPedidoOrcamento;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
