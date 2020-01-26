/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.inicio;
import DAO.FornecedorDAO;
import DAO.ServicoDAO;
import DAO.ServicoFornecedorDAO;
import GUI.FormServicoFornecedor;
import MODEL.Fornecedor;
import MODEL.Servico;
import MODEL.ServicoFornecedor;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlServico {
    
    public static boolean editando = false;
    public static int idAux;
    
    public static void popularcbxFornecedor(){
        Principal.formservicofornecedor.cbxFornecedorServico.removeAllItems();
        ArrayList<Fornecedor> fornecedores = FornecedorDAO.listar();
        for(Fornecedor f : fornecedores)
            Principal.formservicofornecedor.cbxFornecedorServico.addItem(f.toString());
    }
    
    public static void cadastrar(){
        try {
            
            if(FormServicoFornecedor.cbxFornecedorServico.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formdadospessoais, "Nenhum Fornecedor cadastrado! Você será redirecionado para cadastrar um Fornecedor primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormFornecedor();
            }
            
            if(FormServicoFornecedor.txtNomeServico.getText().isEmpty() ||
                    FormServicoFornecedor.txtValorServico.getText().isEmpty())
                JOptionPane.showMessageDialog(Principal.formdadospessoais, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            else{
                Servico servico = new Servico(FormServicoFornecedor.txtNomeServico.getText());
                ServicoDAO.inserir(servico);
                int idServico = 0;
                ArrayList<Servico> servico_banco = ServicoDAO.listar();
                
                for(Servico s : servico_banco){
                    if(idServico < s.getIdServico())
                        idServico = s.getIdServico();
                }
                
                String[] aux = FormServicoFornecedor.cbxFornecedorServico.getSelectedItem().toString().split(" --> ");
                int idFornecedor = Integer.parseInt(aux[0]);                
                
                ServicoFornecedor servicofornecedor = new ServicoFornecedor(
                        idServico, 
                        idFornecedor,
                        Float.parseFloat(FormServicoFornecedor.txtValorServico.getText()));
                
                ServicoFornecedorDAO.inserir(servicofornecedor);
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
        camposServico(false);
        
        int idSelecionado = Integer.parseInt(txt);
        ServicoFornecedor servicofornecedor = ServicoFornecedorDAO.pesquisar1(idSelecionado);
        
        Principal.formservicofornecedor.cbxFornecedorServico.removeAllItems();
        
        String[] aux = servicofornecedor.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        int indexcbx = 0;
        
        ArrayList<Fornecedor> fornecedor = FornecedorDAO.listar();
        for(Fornecedor f : fornecedor){
            String[] aux2 = f.toString().split(" --> ");
            if(aux2[0].equals(aux1[1]))
                break;
            else
                indexcbx = indexcbx + 1;
        }
        
        Servico servico_banco = ServicoDAO.pesquisar1(Integer.parseInt(aux1[0]));
        String nomeservico = servico_banco.getNome();
        
        Principal.mostrarTelaFormServicoFornecedor();
        Principal.formservicofornecedor.btnCancelarServico.setVisible(false);
        Principal.formservicofornecedor.btnConfirmarServico.setVisible(false);
        Principal.formservicofornecedor.btnSairServico.setVisible(true);
        preencherCampos(nomeservico, indexcbx, String.valueOf(servicofornecedor.getValor()));

    }
    
    public static void capturarEditar(int id){
        
        ServicoFornecedor servicofornecedor = ServicoFornecedorDAO.pesquisar1(id);
        
        Principal.formservicofornecedor.cbxFornecedorServico.removeAllItems();
        
        String[] aux = servicofornecedor.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        int indexcbx = 0;
        
        ArrayList<Fornecedor> fornecedor = FornecedorDAO.listar();
        for(Fornecedor f : fornecedor){
            String[] aux2 = f.toString().split(" --> ");
            if(aux2[0].equals(aux1[1]))
                break;
            else
                indexcbx = indexcbx + 1;
        }
        
        Servico servico_banco = ServicoDAO.pesquisar1(Integer.parseInt(aux1[0]));
        String nomeservico = servico_banco.getNome();
        
        Principal.mostrarTelaFormServicoFornecedor();
        preencherCampos(nomeservico, indexcbx, String.valueOf(servicofornecedor.getValor()));
        
        editando = true;
        idAux = id;
    }
    
    public static void editar(int id){ 
        try {
            if(FormServicoFornecedor.txtNomeServico.getText().isEmpty() ||
                FormServicoFornecedor.txtValorServico.getText().isEmpty())
            JOptionPane.showMessageDialog(Principal.formdadospessoais, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            else{
                ServicoFornecedor servicofornecedor_editando = ServicoFornecedorDAO.pesquisar1(id);
                String aux3 = servicofornecedor_editando.toString();
                String[] aux2 = aux3.split(" --> ");
                String[] aux1 = aux2[1].split(" - ");

                Servico servico = new Servico(FormServicoFornecedor.txtNomeServico.getText());
                
                int idservico = Integer.parseInt(aux1[0]);
                ServicoDAO.editar(idservico,servico);

                String[] aux = FormServicoFornecedor.cbxFornecedorServico.getSelectedItem().toString().split(" --> ");
                int idFornecedor = Integer.parseInt(aux[0]);                

                ServicoFornecedor servicofornecedor = new ServicoFornecedor(
                        idservico, 
                        idFornecedor,
                        Float.parseFloat(FormServicoFornecedor.txtValorServico.getText()));

                ServicoFornecedorDAO.editar(id, servicofornecedor);
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
    
    public static void excluir(int IdSelecionado){
        String aux3 = ServicoFornecedorDAO.pesquisar1(IdSelecionado).toString();
        String[] aux2 = aux3.split(" --> ");
        String[] aux1 = aux2[1].split(" - ");
        
        int idServico = Integer.parseInt(aux1[0]);;
        int idServicoFornecedor = IdSelecionado;
        
        ServicoFornecedorDAO.excluir(idServicoFornecedor);
        ServicoDAO.excluir(idServico);
        atualizar();
    }
    
    public static void atualizar(){
        Servico aux;
        String[] aux1;
        
        ArrayList<ServicoFornecedor> servicofornecedor = ServicoFornecedorDAO.listar();
        
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaServico.getModel();
        modelo.setNumRows(0);
        for(ServicoFornecedor sf : servicofornecedor){
            int aux2 = sf.getIdServico();
            aux = ServicoDAO.pesquisar1(aux2);
            aux1 = aux.toString().split(" --> ");
                       
            modelo.addRow(new Object[]{ 
            sf.getIdServicoFornecedor(),                
            aux1[1],   
            sf.getIdFornecedor(),  
            sf.getValor()});
        }

    }
            
    public static void limparCampos(){
        FormServicoFornecedor.txtNomeServico.setText("");
        FormServicoFornecedor.cbxFornecedorServico.setSelectedIndex(0);
        FormServicoFornecedor.txtValorServico.setText("");
    }       
    
    public static void preencherCampos(String nome, int indexcbxfornecedor, String valor){
        FormServicoFornecedor.txtNomeServico.setText(nome);
        FormServicoFornecedor.cbxFornecedorServico.setSelectedIndex(indexcbxfornecedor);
        FormServicoFornecedor.txtValorServico.setText(valor);
    }

    static void camposServico(boolean status) {
        FormServicoFornecedor.txtNomeServico.setEnabled(status);
        FormServicoFornecedor.cbxFornecedorServico.setEnabled(status);
        FormServicoFornecedor.txtValorServico.setEnabled(status);
        editando = !status;
    }
    
}
