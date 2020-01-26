/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.inicio;
import DAO.DadosDAO;
import DAO.EnderecoDAO;
import GUI.FormDadosPessoais;
import MODEL.DadosPessoais;
import MODEL.Endereco;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlDados {
    
    public static boolean editando = false;
    public static int idAux;
    
    public static void popularcbxEndereco(){
        Principal.formdadospessoais.cbxEnderecoDados.removeAllItems();
        ArrayList<Endereco> enderecos = EnderecoDAO.listar();
        for(Endereco e : enderecos)
            Principal.formdadospessoais.cbxEnderecoDados.addItem(e.toString());  
    }
    
    public static void cadastrar(){
        
        try {
            if(FormDadosPessoais.cbxEnderecoDados.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formdadospessoais, "Nenhum Endereço cadastrado! Você será redirecionado para cadastrar um Endereço primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormEndereco();
            }
            if(FormDadosPessoais.txtNomeDados.getText().isEmpty())
                JOptionPane.showMessageDialog(Principal.formdadospessoais, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            else{
                DadosPessoais dados = null; 
                    String aux = FormDadosPessoais.cbxEnderecoDados.getSelectedItem().toString();
                    String[] idEndereco = aux.split(Pattern.quote(" -->"));
                    int id = Integer.parseInt(idEndereco[0]);
                    dados = new DadosPessoais(
                        FormDadosPessoais.txtNomeDados.getText(),
                        FormDadosPessoais.txtEmailDados.getText(),
                        FormDadosPessoais.txtTelefoneDados.getText(), 
                        FormDadosPessoais.txtCelularDados.getText(),
                        id);            

                DadosDAO.inserir(dados);
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
        camposDados(false);
        
        int idSelecionado = Integer.parseInt(txt);
        int idcbx = -1;
        DadosPessoais dados = DadosDAO.pesquisar1(idSelecionado);
        Principal.formdadospessoais.cbxEnderecoDados.removeAllItems();
        
        String[] aux = dados.toString().split(" / ");

        int indexcbx = 0;
        
        ArrayList<Endereco> endereco = EnderecoDAO.listar();
        for(Endereco e : endereco){
            
            String[] aux2 = e.toString().split(" --> ");
            if(aux2[0].equals(aux[1])){
                idcbx = indexcbx;
                break;
            }
            else
                indexcbx = indexcbx + 1;
        }        
        Principal.mostrarTelaFormDadosPessoais();
        Principal.formdadospessoais.btnCancelarDados.setVisible(false);
        Principal.formdadospessoais.btnConfirmarDados.setVisible(false);
        Principal.formdadospessoais.btnSairDados.setVisible(true);
        preencherCampos(dados.getNome(), dados.getEmail(), dados.getFixo(), dados.getCelular(), idcbx);
        
    }
    
    public static void capturarEditar(int id){
        int idcbx = -1;
        DadosPessoais dados = DadosDAO.pesquisar1(id);
        Principal.formdadospessoais.cbxEnderecoDados.removeAllItems();
        
        String[] aux = dados.toString().split(" / ");

        int indexcbx = 0;
        
        ArrayList<Endereco> endereco = EnderecoDAO.listar();
        for(Endereco e : endereco){
            
            String[] aux2 = e.toString().split(" --> ");
            if(aux2[0].equals(aux[1])){
                idcbx = indexcbx;
                break;
            }
            else
                indexcbx = indexcbx + 1;
        }        
        Principal.mostrarTelaFormDadosPessoais();
        
        preencherCampos(dados.getNome(), dados.getEmail(), dados.getFixo(), dados.getCelular(), idcbx);
        editando = true;
        idAux = id;
    }
    
    public static void editar(int id){
        
        if(FormDadosPessoais.txtNomeDados.getText().isEmpty())
                JOptionPane.showMessageDialog(Principal.formdadospessoais, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
        else{
            
        
                String aux = FormDadosPessoais.cbxEnderecoDados.getSelectedItem().toString();
                String[] idEndereco = aux.split(Pattern.quote(" --> "));
                int idEnderecocbx = Integer.parseInt(idEndereco[0]);
                
                DadosPessoais dados_editar = new DadosPessoais(
                        idAux,
                    FormDadosPessoais.txtNomeDados.getText(),
                    FormDadosPessoais.txtEmailDados.getText(),
                    FormDadosPessoais.txtTelefoneDados.getText(), 
                    FormDadosPessoais.txtCelularDados.getText(),
                    idEnderecocbx);
            DadosDAO.editar(id, dados_editar);
            limparCampos();
            Principal.mostrarTelaInicio();
            atualizar();
        } 
    }
    
    public static void excluir(int id){
        DadosDAO.excluir(id);
        atualizar();
    }
    
    public static void atualizar(){
        ArrayList<DadosPessoais> dados = DadosDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaDados.getModel();
        modelo.setNumRows(0);
        for(DadosPessoais d : dados)
            modelo.addRow(new Object[]{d.getId(), d.getNome(), d.getEmail(), d.getFixo(), d.getCelular(), d.getIdEndereco()});
        
    }
    
    public static void limparCampos(){
        FormDadosPessoais.txtNomeDados.setText("");
        FormDadosPessoais.txtEmailDados.setText("");
        FormDadosPessoais.txtTelefoneDados.setText("");
        FormDadosPessoais.txtCelularDados.setText("");
        FormDadosPessoais.cbxEnderecoDados.setSelectedIndex(0);
    }
    
    public static void preencherCampos(String Nome, String Email, String Fixo, String Celular, int indexcbxendereco){
        FormDadosPessoais.txtNomeDados.setText(Nome);
        FormDadosPessoais.txtEmailDados.setText(Email);
        FormDadosPessoais.txtTelefoneDados.setText(Fixo);
        FormDadosPessoais.txtCelularDados.setText(Celular);
        FormDadosPessoais.cbxEnderecoDados.setSelectedIndex(indexcbxendereco);
    }

    static void camposDados(boolean status) {
        FormDadosPessoais.txtNomeDados.setEnabled(status);
        FormDadosPessoais.txtEmailDados.setEnabled(status);
        FormDadosPessoais.txtTelefoneDados.setEnabled(status);
        FormDadosPessoais.txtCelularDados.setEnabled(status);
        FormDadosPessoais.cbxEnderecoDados.setEnabled(status);
        ControlDados.editando = !status;
    }

}
