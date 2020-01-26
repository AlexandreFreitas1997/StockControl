/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.formpartes;
import static CONTROL.Principal.inicio;
import DAO.PartesDAO;
import GUI.FormPartes;
import MODEL.Partes;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlPartes {
    public static boolean editando = false;
    public static int idAux;
    
    public static void cadastrar(){
        
        try {
            if(FormPartes.txtNomePartes.getText().isEmpty() ||
                    FormPartes.txtPrecoPartes.getText().isEmpty())
                JOptionPane.showMessageDialog(Principal.formendereco, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            else{
                Partes parte = new Partes(FormPartes.txtNomePartes.getText(), 
                                            Float.parseFloat(FormPartes.txtPrecoPartes.getText()));

                PartesDAO.inserir(parte);
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
        camposPartes(false);
        
        int id = Integer.parseInt(txt);
        Partes parte = PartesDAO.pesquisar1(id);
        Principal.mostrarTelaFormPartes();
        preencherCampos(parte.getNome(), String.valueOf(parte.getPreco()));
        formpartes.btnCancelarPartes.setVisible(false);
        formpartes.btnConfirmarPartes.setVisible(false);
        formpartes.btnSairPartes.setVisible(true);  
    }
    
    public static void capturarEditar(int id){
        Partes parte = PartesDAO.pesquisar1(id);
        Principal.mostrarTelaFormPartes();
        preencherCampos(parte.getNome(), String.valueOf(parte.getPreco()));
        editando = true;
        idAux = id;
    }
    
    public static void editar(int id){
        
        try {
            if(FormPartes.txtNomePartes.getText().isEmpty() || 
                    FormPartes.txtPrecoPartes.getText().isEmpty())
                JOptionPane.showMessageDialog(Principal.formendereco, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            else{
                Partes parte = new Partes(
                        FormPartes.txtNomePartes.getText(),
                        Float.parseFloat(FormPartes.txtPrecoPartes.getText()));

                PartesDAO.editar(id, parte);
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
        PartesDAO.excluir(id);
        atualizar();
    }

    public static void limparCampos() {
        FormPartes.txtNomePartes.setText("");
        FormPartes.txtPrecoPartes.setText("");
    }
    
    public static void preencherCampos(String nome, String preco) {
        FormPartes.txtNomePartes.setText(nome);
        FormPartes.txtPrecoPartes.setText(preco);
    }

    public static void atualizar() {
        ArrayList<Partes> partes = PartesDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaPartes.getModel();
        modelo.setNumRows(0);
        for(Partes p : partes)
            modelo.addRow(new Object[]{p.getIdPartes(), p.getNome(), p.getPreco()});
    }
    
    static void camposPartes(boolean status){
        formpartes.txtNomePartes.setEnabled(status);
        formpartes.txtPrecoPartes.setEnabled(status);
        editando = !status;
    }
}
