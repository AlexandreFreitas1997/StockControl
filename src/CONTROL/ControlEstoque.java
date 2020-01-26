/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.inicio;
import DAO.EnderecoDAO;
import DAO.EstoqueDAO;
import GUI.FormEstoque;
import MODEL.Endereco;
import MODEL.Estoque;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlEstoque {
    
    public static int idAux;
    public static boolean editando = false;
    
    public static void popularcbxEndereco(){
        Principal.formestoque.cbxEnderecoEstoque.removeAllItems();
        ArrayList<Endereco> enderecos = EnderecoDAO.listar();
        for(Endereco e : enderecos)
            Principal.formestoque.cbxEnderecoEstoque.addItem(e.toString());
    }
    
    public static void cadastrar(){
        try {
            if(FormEstoque.cbxEnderecoEstoque.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formcliente, "Nenhum Endereço cadastrado! Você será redirecionado para cadastrar um novo Endereço primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormEndereco();
            }
            else{
                Estoque estoque = null;
                String aux = FormEstoque.cbxEnderecoEstoque.getSelectedItem().toString();
                String[] idEndereco = aux.split(" --> ");
                estoque = new Estoque(Integer.parseInt(idEndereco[0]));
                
                EstoqueDAO.inserir(estoque);
                limparCampos();
                Principal.mostrarTelaInicio();
                atualizar();
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Erro ao inserir os dados! Verifique os dados digitados!");
        }
        catch (Exception e) {
            System.err.println("Problema detectado! control" + e);
        }
    }
    
    public static void limparCampos(){
        FormEstoque.cbxEnderecoEstoque.setSelectedIndex(0);
    }
    
    public static void preencherCampos(int indexcbxenderecos){
        FormEstoque.cbxEnderecoEstoque.setSelectedIndex(indexcbxenderecos);
    }
    
    public static void atualizar(){
        ArrayList<Estoque> estoque = EstoqueDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaEstoque.getModel();
        modelo.setRowCount(0);
        for(Estoque e : estoque)
            modelo.addRow(new Object[]{e.getIdEstoque(), e.getIdEndereco()});
    }
    
    public static void excluir(int id){
        EstoqueDAO.excluir(id);
        atualizar();
    }
    
    public static void editar(int idAux){
        String aux = FormEstoque.cbxEnderecoEstoque.getSelectedItem().toString();
                String[] idEndereco = aux.split(" --> ");
                Estoque estoque = new Estoque(Integer.parseInt(idEndereco[0]));
                
                EstoqueDAO.editar(idAux, estoque);
                limparCampos();
                Principal.mostrarTelaInicio();
                atualizar();
    }
    
    public static void vizualizar(String txt){
        camposEstoque(false);
        
        int idSelecionado = Integer.parseInt(txt);
        Estoque estoque = EstoqueDAO.pesquisar1(idSelecionado);
        
        Principal.formestoque.cbxEnderecoEstoque.removeAllItems();
        
        String[] aux = estoque.toString().split(" --> ");
        int indexcbx = 0;
        
        ArrayList<Endereco> enderecos = EnderecoDAO.listar();
        
        for(Endereco e : enderecos){
            String[] aux2 = e.toString().split(" --> ");
            if(aux2[0].equals(aux[1])){
                break;
            }
            else
                indexcbx = indexcbx + 1;
        }
        Principal.mostrarTelaFormEstoque();
        Principal.formestoque.btnCancelarEstoque.setVisible(false);
        Principal.formestoque.btnConfirmarEstoque.setVisible(false);
        Principal.formestoque.btnSairEstoque.setVisible(true);
        preencherCampos(indexcbx);
        
    }
    
    public static void capturarEditar(int idSelecionado){
        Estoque estoque = EstoqueDAO.pesquisar1(idSelecionado);
        
        Principal.formestoque.cbxEnderecoEstoque.removeAllItems();
        
        String[] aux = estoque.toString().split(" --> ");
        int indexcbx = 0;
        
        ArrayList<Endereco> enderecos = EnderecoDAO.listar();
        
        for(Endereco e : enderecos){
            String[] aux2 = e.toString().split(" --> ");
            if(aux2[0].equals(aux[1])){
                break;
            }
            else
                indexcbx = indexcbx + 1;
        }
        Principal.mostrarTelaFormEstoque();
        preencherCampos(indexcbx);
        editando = true;
        idAux = idSelecionado;
        
    }

    static void camposEstoque(boolean b) {
        FormEstoque.cbxEnderecoEstoque.setEnabled(b);
        editando = !b;
    }
    
}
