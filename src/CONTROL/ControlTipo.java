/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.formtipo;
import static CONTROL.Principal.inicio;
import DAO.TipoDAO;
import GUI.FormTipo;
import MODEL.Tipo;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlTipo {
    
    public static boolean editando = false;
    public static int idAux;
    
    public static void cadastrar(){
        
        try {
            if(FormTipo.txtNomeTipo.getText().isEmpty())
                JOptionPane.showMessageDialog(Principal.formendereco, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            else{
                Tipo tipo = new Tipo(FormTipo.txtNomeTipo.getText());

                TipoDAO.inserir(tipo);
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
        camposTipo(false);
        
        int id = Integer.parseInt(txt);
        Tipo tipo = TipoDAO.pesquisar1(id);
        Principal.mostrarTelaFormTipo();
        preencherCampos(tipo.getNome());
        formtipo.btnCancelarTipo.setVisible(false);
        formtipo.btnConfirmarTipo.setVisible(false);
        formtipo.btnSairTipo.setVisible(true); 
    }
    
    public static void capturarEditar(int id){
        Tipo tipo = TipoDAO.pesquisar1(id);
        Principal.mostrarTelaFormTipo();
        preencherCampos(tipo.getNome());
        editando = true;
        idAux = id;
    }
    
    public static void editar(int id){
        
        try {
            if(FormTipo.txtNomeTipo.getText().isEmpty())
                JOptionPane.showMessageDialog(Principal.formendereco, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            else{
                Tipo tipo = new Tipo(FormTipo.txtNomeTipo.getText());

                TipoDAO.editar(id, tipo);
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
        TipoDAO.excluir(id);
        atualizar();
    }

    public static void limparCampos() {
        FormTipo.txtNomeTipo.setText("");
    }
    
    public static void preencherCampos(String nome) {
        FormTipo.txtNomeTipo.setText(nome);
    }

    public static void atualizar() {
        ArrayList<Tipo> tipos = TipoDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaTipo.getModel();
        modelo.setNumRows(0);
        for(Tipo t : tipos)
            modelo.addRow(new Object[]{t.getIdTipo(), t.getNome()});
    }
    
    static void camposTipo(boolean status){
        formtipo.txtNomeTipo.setEnabled(status);
        editando = !status;
    } 
    
    
    
    
}
