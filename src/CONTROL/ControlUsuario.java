/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.inicio;
import DAO.UsuarioDAO;
import GUI.FormUsuario;
import MODEL.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlUsuario {
    public static boolean editando; 
    public static int idAux;
    
    public static String hashSenha(String senha){
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            byte senhadigerida[] = algorithm.digest(senha.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : senhadigerida) {
              hexString.append(String.format("%02X", 0xFF & b));
            }
            String hashsenha = hexString.toString();
            
            return hashsenha;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }
    
    public static void cadastrar(){
        try {
            
            if(FormUsuario.txtLoginUsuario.getText().isEmpty() || 
                    FormUsuario.txtSenhaUsuario.getText().isEmpty()){
                JOptionPane.showMessageDialog(Principal.formendereco, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            }
            else{
                if(FormUsuario.cbxTipoUsuario.getSelectedIndex() != 0){
                    Usuario usuario = new Usuario(
                            FormUsuario.txtLoginUsuario.getText(),
                            hashSenha(FormUsuario.txtSenhaUsuario.getText()),
                            FormUsuario.cbxTipoUsuario.getSelectedItem().toString());

                    UsuarioDAO.inserir(usuario);
                    limparCampos();
                    Principal.mostrarTelaInicio();
                    atualizar();
                }
                else
                    JOptionPane.showMessageDialog(Principal.formendereco, "Por favor selecione um Tipo de Usuario!", "Erro", 0);
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Erro ao inserir os dados! Verifique os dados digitados!");
        }
        catch(Exception e2){
            System.err.println("Problema detectado!" + e2);
        }
    }
    
    public static void excluir(int id){
        UsuarioDAO.excluir(id);
        atualizar();
    }

    public static void limparCampos() {
        FormUsuario.txtLoginUsuario.setText("");
        FormUsuario.txtSenhaUsuario.setText("");
        FormUsuario.cbxTipoUsuario.setSelectedIndex(0);
    }
    
    public static void preencherCampos(String login, int indexcbxtipousuario) {
        JOptionPane.showMessageDialog(Principal.formusuario, "Por questoões de segurança não foi possivel preencher o campo Senha.", "Aviso de Segurança", 2);
        FormUsuario.txtLoginUsuario.setText(login);
        FormUsuario.txtSenhaUsuario.setText("");
        FormUsuario.cbxTipoUsuario.setSelectedIndex(indexcbxtipousuario);
    }

    public static void atualizar() {
        ArrayList<Usuario> usuario = UsuarioDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaUsuario.getModel();
        modelo.setNumRows(0);
        for(Usuario u : usuario)
            modelo.addRow(new Object[]{u.getIdUsuario(), u.getLogin(), u.getTipoUsuario()});
    }
    
    static void camposUsuario(boolean status){
        FormUsuario.txtLoginUsuario.setEnabled(status);
        FormUsuario.txtSenhaUsuario.setEnabled(status);
        FormUsuario.cbxTipoUsuario.setEnabled(status);
        editando = !status;
    }

    public static void editar(int idAux) {
        if(FormUsuario.txtLoginUsuario.getText().isEmpty() || 
                FormUsuario.txtSenhaUsuario.getText().isEmpty()){
            JOptionPane.showMessageDialog(Principal.formendereco, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
        }
        else{
            if(FormUsuario.cbxTipoUsuario.getSelectedIndex() != 0){
                Usuario usuario = new Usuario(
                        FormUsuario.txtLoginUsuario.getText(),
                        hashSenha(FormUsuario.txtSenhaUsuario.getText()),
                        FormUsuario.cbxTipoUsuario.getSelectedItem().toString());
                UsuarioDAO.editar(idAux, usuario);
                limparCampos();
                Principal.mostrarTelaInicio();
                atualizar();
            }
            else
                JOptionPane.showMessageDialog(Principal.formendereco, "Por favor selecione um Tipo de Usuario!", "Erro", 0);
        }      
    }

    public static void vizualizar(String txt) {
        camposUsuario(false);
        int idSelecionado = Integer.parseInt(txt);
        
        int idcbx = 0;
        Usuario usuario = UsuarioDAO.pesquisar1(idSelecionado);
        if(usuario.getTipoUsuario().equals("Funcionário"))
            idcbx = 1;
        if(usuario.getTipoUsuario().equals("Gerente"))
            idcbx = 2;
        if(usuario.getTipoUsuario().equals("Administrador"))
            idcbx = 3;
        
        Principal.mostrarTelaFormUsuario();
        preencherCampos(usuario.getLogin(), idcbx);
        Principal.formusuario.btnCancelarUsuario.setVisible(false);
        Principal.formusuario.btnConfirmarUsuario.setVisible(false);
        Principal.formusuario.btnSairUsuario.setVisible(true);
    }

    public static void capturarEditar(int idSelecionado) {
        int idcbx = 0;
        Usuario usuario = UsuarioDAO.pesquisar1(idSelecionado);
        if(usuario.getTipoUsuario().equals("Funcionário"))
            idcbx = 1;
        if(usuario.getTipoUsuario().equals("Gerente"))
            idcbx = 2;
        if(usuario.getTipoUsuario().equals("Administrador"))
            idcbx = 3;
        
        Principal.mostrarTelaFormUsuario();
        preencherCampos(usuario.getLogin(), idcbx);
        editando = true;
        idAux = idSelecionado;
    }
    
    
}
