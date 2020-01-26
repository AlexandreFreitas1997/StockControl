/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import CONTROL.ControlCliente;
import CONTROL.Principal;

/**
 *
 * @author 20161stads0039
 */
public class FormCliente extends javax.swing.JFrame {

    /**
     * Creates new form CadastrarC
     */
    public FormCliente() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbxDadosPessoaisCliente = new javax.swing.JComboBox<String>();
        txtDocCliente = new javax.swing.JTextField();
        btnCancelarCliente = new javax.swing.JButton();
        btnSairCliente = new javax.swing.JButton();
        btnConfirmarCliente = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(575, 230));
        setMinimumSize(new java.awt.Dimension(575, 230));
        setPreferredSize(new java.awt.Dimension(575, 230));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                chamarInicio(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setText("Dados Pessoais");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 50, 160, 14);

        jLabel2.setText("Doc. de Identificação (CPF ou CNPJ) (*)");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 100, 270, 14);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setText("Cliente");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(10, 10, 110, 30);

        cbxDadosPessoaisCliente.setToolTipText("");
        getContentPane().add(cbxDadosPessoaisCliente);
        cbxDadosPessoaisCliente.setBounds(10, 70, 550, 20);
        getContentPane().add(txtDocCliente);
        txtDocCliente.setBounds(10, 120, 550, 20);

        btnCancelarCliente.setText("Cancelar");
        btnCancelarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelarCliente);
        btnCancelarCliente.setBounds(50, 170, 90, 23);

        btnSairCliente.setText("Sair");
        btnSairCliente.setName(""); // NOI18N
        btnSairCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairClienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnSairCliente);
        btnSairCliente.setBounds(240, 170, 70, 23);

        btnConfirmarCliente.setText("Confirmar");
        btnConfirmarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfirmarCliente);
        btnConfirmarCliente.setBounds(420, 170, 100, 23);

        jLabel7.setText("Campos Obrigatórios  (*)");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(390, 30, 160, 14);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarClienteActionPerformed
        Principal.mostrarTelaInicio();
        ControlCliente.limparCampos();
    }//GEN-LAST:event_btnCancelarClienteActionPerformed

    private void btnSairClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairClienteActionPerformed
        Principal.mostrarTelaInicio();
        ControlCliente.limparCampos();
    }//GEN-LAST:event_btnSairClienteActionPerformed

    private void chamarInicio(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_chamarInicio
        Principal.mostrarTelaInicio();
    }//GEN-LAST:event_chamarInicio

    private void btnConfirmarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarClienteActionPerformed
        if(ControlCliente.editando == false)
            ControlCliente.cadastrar();
        else
            ControlCliente.editar(ControlCliente.idAux);
    }//GEN-LAST:event_btnConfirmarClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancelarCliente;
    public javax.swing.JButton btnConfirmarCliente;
    public javax.swing.JButton btnSairCliente;
    public static javax.swing.JComboBox<String> cbxDadosPessoaisCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    public static javax.swing.JTextField txtDocCliente;
    // End of variables declaration//GEN-END:variables
}