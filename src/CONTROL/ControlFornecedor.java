/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.inicio;
import DAO.DadosDAO;
import DAO.FornecedorDAO;
import GUI.FormFornecedor;
import MODEL.DadosPessoais;
import MODEL.Fornecedor;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlFornecedor {
    
    public static int idAux;
    public static boolean editando = false;
    
    public static void popularcbxDados(){
        Principal.formfornecedor.cbxDadosFornecedor.removeAllItems();
        ArrayList<DadosPessoais> dados = DadosDAO.listar();
        for(DadosPessoais d : dados)
            Principal.formfornecedor.cbxDadosFornecedor.addItem(d.toString());
    }
    
    public static void cadastrar(){
        try {
            if(FormFornecedor.cbxDadosFornecedor.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formcliente, "Nenhum Dado cadastrado! Você será redirecionado para cadastrar um novo Dado primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormDadosPessoais();
                return;
            }
            else{
                Fornecedor fornecedor = null;
                String aux = FormFornecedor.cbxDadosFornecedor.getSelectedItem().toString();
                String[] idDado = aux.split(" --> ");
                int id = Integer.parseInt(idDado[0]);
                fornecedor = new Fornecedor(id);

                FornecedorDAO.inserir(fornecedor);
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

    public static void limparCampos() {
        FormFornecedor.cbxDadosFornecedor.setSelectedIndex(0);
    }
    
    public static void preencherCampos(int indexcbxdados){
        FormFornecedor.cbxDadosFornecedor.setSelectedIndex(indexcbxdados);
    }

    public static void atualizar() {
        ArrayList<Fornecedor> fornecedor = FornecedorDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelalFornecedor.getModel();
        modelo.setRowCount(0);
        for(Fornecedor f : fornecedor)
            modelo.addRow(new Object[]{f.getIdFornecedor(), f.getIdDadosPessoais()});
    }
    
    public static void excluir(int id){
        FornecedorDAO.excluir(id);
        atualizar();
    }
    
    public static void editar(int idAux){
        String aux = FormFornecedor.cbxDadosFornecedor.getSelectedItem().toString();
            String[] idDado = aux.split(" --> ");
            int id = Integer.parseInt(idDado[0]);
            Fornecedor fornecedor_banco = new Fornecedor(id);
            
            FornecedorDAO.editar(idAux, fornecedor_banco);
            limparCampos();
            Principal.mostrarTelaInicio();
            atualizar();
    }
    
    public static void vizualizar(String txt){
        camposFornecedor(false);
        
        int idSelecionado = Integer.parseInt(txt);
        
        Fornecedor fornecedor = FornecedorDAO.pesquisar1(idSelecionado);
        
        Principal.formfornecedor.cbxDadosFornecedor.removeAllItems();
        
        String[] aux = fornecedor.toString().split(" --> ");
        
        int indexcbx = 0;
        
        ArrayList<DadosPessoais> dados = DadosDAO.listar();
        
        for(DadosPessoais d : dados){
            
            String[] aux2 = d.toString().split(" --> ");
            if(aux2[0].equals(aux[1])){
                break;
            }
            else
                indexcbx = indexcbx + 1;
        }
        Principal.mostrarTelaFormFornecedor();
        Principal.formfornecedor.btnCancelarFornecedor.setVisible(false);
        Principal.formfornecedor.btnConfirmarFornecedor.setVisible(false);
        Principal.formfornecedor.btnSairFornecedor.setVisible(true);
        preencherCampos(indexcbx);
        
    }
    
    public static void capturarEditar(int idSelecionado){
        Fornecedor fornecedor = FornecedorDAO.pesquisar1(idSelecionado);
        
        Principal.formfornecedor.cbxDadosFornecedor.removeAllItems();
        
        String[] aux = fornecedor.toString().split(" --> ");
        
        int indexcbx = 0;
        
        ArrayList<DadosPessoais> dados = DadosDAO.listar();
        
        for(DadosPessoais d : dados){
            
            String[] aux2 = d.toString().split(" --> ");
            if(aux2[0].equals(aux[1])){
                break;
            }
            else
                indexcbx = indexcbx + 1;
        }
        Principal.mostrarTelaFormFornecedor();
        preencherCampos(indexcbx);
        editando = true;
        idAux = idSelecionado;
    }
    
    static void camposFornecedor(boolean status){
        FormFornecedor.cbxDadosFornecedor.setEnabled(status);
        editando = !status;
    }
}
