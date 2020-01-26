/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;


import static CONTROL.Principal.formendereco;
import static CONTROL.Principal.inicio;
import GUI.FormEndereco;
import MODEL.Endereco;
import DAO.EnderecoDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlEndereco {
    
    public static boolean editando = false;
    public static int idAux;
    
    public static void cadastrar(){
        
        try {
            if(FormEndereco.txtLogradouroEndereco.getText().isEmpty() || 
                            FormEndereco.txtBairroEndereco.getText().isEmpty() ||
                            FormEndereco.txtCidadeEndereco.getText().isEmpty() ||
                            FormEndereco.txtCEPEndereco.getText().isEmpty()){
                JOptionPane.showMessageDialog(Principal.formendereco, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
                
            }else{
                if(FormEndereco.cbxEstadosEndereco.getSelectedIndex() != 00){
                    String cbxestado = null;
                    if(FormEndereco.cbxEstadosEndereco.getSelectedIndex() < 10)
                        cbxestado = "0" + FormEndereco.cbxEstadosEndereco.getSelectedIndex() + "/" + FormEndereco.cbxEstadosEndereco.getSelectedItem().toString();
                    else{
                        cbxestado = FormEndereco.cbxEstadosEndereco.getSelectedIndex() + "/" + FormEndereco.cbxEstadosEndereco.getSelectedItem().toString();
                    }
                    Endereco endereco = new Endereco(
                            FormEndereco.txtLogradouroEndereco.getText(),
                            FormEndereco.txtNumeroEndereco.getText(),
                            FormEndereco.txtComplementoEndereco.getText(),
                            FormEndereco.txtReferenciaEndereco.getText(),
                            FormEndereco.txtBairroEndereco.getText(),
                            FormEndereco.txtCidadeEndereco.getText(),
                            cbxestado.substring(0, 6),
                            FormEndereco.txtCEPEndereco.getText()
                    );

                    EnderecoDAO.inserir(endereco);
                    limparCampos();
                    Principal.mostrarTelaInicio();
                    atualizar();

                }else{
                    JOptionPane.showMessageDialog(Principal.formendereco, "Por favor selecione um estado!", "Erro", 0);
                }
            }         
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Erro ao inserir os dados! Verifique os dados digitados!");
        }
        catch(Exception e2){
            System.err.println("Problema detectado!" + e2);
        }
    }

    public static void vizualizar(String txt) {
        camposEndereco(false);

        int id = Integer.parseInt(txt);
        int idcbx = 0;
        Endereco endereco = EnderecoDAO.pesquisar1(id);
        idcbx = Integer.parseInt(endereco.getEstado().substring(0, 2));
        Principal.mostrarTelaFormEndereco();
        Principal.formendereco.btnCancelarEndereco.setVisible(false);
        Principal.formendereco.btnConfirmarEndereco.setVisible(false);
        Principal.formendereco.btnSairEndereco.setVisible(true);
        preencherCampos(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getReferencia(), endereco.getBairro(), endereco.getCidade(), idcbx, endereco.getCep());
    }
    
    public static void capturarEditar(int id){
        int idcbx = 0;
        Endereco endereco = EnderecoDAO.pesquisar1(id);
        idcbx = Integer.parseInt(endereco.getEstado().substring(0, 2));
        Principal.mostrarTelaFormEndereco();
        preencherCampos(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getReferencia(), endereco.getBairro(), endereco.getCidade(), idcbx, endereco.getCep());
        editando = true;
        idAux = id;

    }
    
    public static void editar(int id){
        
        if(FormEndereco.txtLogradouroEndereco.getText().isEmpty() || 
                            FormEndereco.txtBairroEndereco.getText().isEmpty() ||
                            FormEndereco.txtCidadeEndereco.getText().isEmpty() ||
                            FormEndereco.txtCEPEndereco.getText().isEmpty()){
                JOptionPane.showMessageDialog(Principal.formendereco, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
                
        }else{
            if(FormEndereco.cbxEstadosEndereco.getSelectedIndex() != 00){
                String cbxestado = null;
                if(FormEndereco.cbxEstadosEndereco.getSelectedIndex() < 10)
                    cbxestado = "0" + FormEndereco.cbxEstadosEndereco.getSelectedIndex() + "/" + FormEndereco.cbxEstadosEndereco.getSelectedItem().toString();
                else{
                    cbxestado = FormEndereco.cbxEstadosEndereco.getSelectedIndex() + "/" + FormEndereco.cbxEstadosEndereco.getSelectedItem().toString();
                }
                Endereco endereco_editar = new Endereco(
                        FormEndereco.txtLogradouroEndereco.getText(),
                        FormEndereco.txtNumeroEndereco.getText(),
                        FormEndereco.txtComplementoEndereco.getText(),
                        FormEndereco.txtReferenciaEndereco.getText(),
                        FormEndereco.txtBairroEndereco.getText(),
                        FormEndereco.txtCidadeEndereco.getText(),
                        cbxestado.substring(0, 6),
                        FormEndereco.txtCEPEndereco.getText()
                );
            EnderecoDAO.editar(id, endereco_editar);
            limparCampos();
            Principal.mostrarTelaInicio();
            atualizar();
            }
        }
    }
    
    public static void excluir(int id){
        EnderecoDAO.excluir(id);
        atualizar();
    }
    
    public static void atualizar(){
        
        ArrayList<Endereco> enderecos = EnderecoDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaEnderecos.getModel();
        modelo.setNumRows(0);
        for(Endereco e : enderecos)
            modelo.addRow(new Object[]{e.getIdEnderco(), e.getLogradouro(), e.getNumero(), e.getBairro(), e.getCidade(), e.getEstado().substring(3, 5), e.getCep(), e.getReferencia(), e.getComplemento()});
    }
    
    public static void limparCampos(){
        
        FormEndereco.txtLogradouroEndereco.setText("");
        FormEndereco.txtNumeroEndereco.setText("");
        FormEndereco.txtComplementoEndereco.setText("");
        FormEndereco.txtReferenciaEndereco.setText("");
        FormEndereco.txtBairroEndereco.setText("");
        FormEndereco.txtCidadeEndereco.setText("");
        FormEndereco.cbxEstadosEndereco.setSelectedIndex(0);
        FormEndereco.txtCEPEndereco.setText("");
    
    }
    
    public static void preencherCampos(String logradouro, String numero, String complemento, String referencia, String bairro, String cidade,int indexcbxestado, String cep){
        
        FormEndereco.txtLogradouroEndereco.setText(logradouro);
        FormEndereco.txtNumeroEndereco.setText(numero);
        FormEndereco.txtComplementoEndereco.setText(complemento);
        FormEndereco.txtReferenciaEndereco.setText(referencia);
        FormEndereco.txtBairroEndereco.setText(bairro);
        FormEndereco.txtCidadeEndereco.setText(cidade);
        FormEndereco.cbxEstadosEndereco.setSelectedIndex(indexcbxestado);
        FormEndereco.txtCEPEndereco.setText(cep);
    
    }
    
    static void camposEndereco(boolean status){
        formendereco.txtLogradouroEndereco.setEnabled(status);
        formendereco.txtNumeroEndereco.setEnabled(status);
        formendereco.txtBairroEndereco.setEnabled(status);
        formendereco.txtCidadeEndereco.setEnabled(status);
        formendereco.txtCEPEndereco.setEnabled(status);
        formendereco.txtComplementoEndereco.setEnabled(status);
        formendereco.txtReferenciaEndereco.setEnabled(status);
        formendereco.cbxEstadosEndereco.setEnabled(status);
        
        ControlEndereco.editando = !status;
    }
    
}