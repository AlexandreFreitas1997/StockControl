/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.formstatus;
import static CONTROL.Principal.inicio;
import DAO.StatusDAO;
import MODEL.Status;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlStatus {
    public static boolean editando = false;
    public static int idAux;
    
    public static void cadastrar(){
        
        try {
            if(formstatus.txtNomeStatus.getText().isEmpty())
                JOptionPane.showMessageDialog(Principal.formstatus, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            else{
                Status status = new Status(formstatus.txtNomeStatus.getText());

                StatusDAO.inserir(status);
                limparCampos();
                Principal.mostrarTelaInicio();
                atualizar();
            }
            
            
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Erro ao inserir os dados! Verifique os dados digitados!");
        }
        catch(Exception e2){
            System.err.println("Problema detectado!" + e2);
        }
    }
    
    public static void vizualizar(String txt){
        camposStatus(false);
        
        int id = Integer.parseInt(txt);
        Status status = StatusDAO.pesquisar1(id);
        Principal.mostrarTelaFormStatus();
        preencherCampos(status.getNome());
        formstatus.btnCancelarStatus.setVisible(false);
        formstatus.btnConfirmarStatus.setVisible(false);
        formstatus.btnSairStatus.setVisible(true); 
    }
    
    public static void capturarEditar(int id){
        Status status = StatusDAO.pesquisar1(id);
        Principal.mostrarTelaFormStatus();
        preencherCampos(status.getNome());
        editando = true;
        idAux = id;
    }
    
    public static void editar(int id){
        
        try {
            if(formstatus.txtNomeStatus.getText().isEmpty())
                JOptionPane.showMessageDialog(Principal.formstatus, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            else{
                Status status = new Status(formstatus.txtNomeStatus.getText());

                StatusDAO.editar(id, status);
                limparCampos();
                Principal.mostrarTelaInicio();
                atualizar();
            }
            
            
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Erro ao inserir os dados! Verifique os dados digitados!");
        }
        catch(Exception e2){
            System.err.println("Problema detectado!" + e2);
        }
    }
    
    public static void excluir(int id){
        StatusDAO.excluir(id);
        atualizar();
    }

    public static void limparCampos() {
        formstatus.txtNomeStatus.setText("");
    }
    
    public static void preencherCampos(String nome) {
        formstatus.txtNomeStatus.setText(nome);
    }

    public static void atualizar() {
        ArrayList<Status> status = StatusDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaStatus.getModel();
        modelo.setNumRows(0);
        for(Status s : status)
            modelo.addRow(new Object[]{s.getIdStatus(), s.getNome()});
    }
    
    static void camposStatus(boolean status){
        formstatus.txtNomeStatus.setEnabled(status);
        editando = !status;
    }
}
