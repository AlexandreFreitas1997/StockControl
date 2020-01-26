/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.formcliente;
import static CONTROL.Principal.inicio;
import DAO.ClienteDAO;
import DAO.DadosDAO;
import GUI.FormCliente;
import MODEL.Cliente;
import MODEL.DadosPessoais;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlCliente {
    
    public static int idAux;
    public static boolean editando = false;
    
    public static void popularcbxDados(){
        Principal.formcliente.cbxDadosPessoaisCliente.removeAllItems();
        ArrayList<DadosPessoais> dados = DadosDAO.listar();
        for(DadosPessoais d : dados)
            Principal.formcliente.cbxDadosPessoaisCliente.addItem(d.toString());
    }
    
    public static void cadastrar(){
         try {
            if(FormCliente.cbxDadosPessoaisCliente.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formcliente, "Nenhum Dado Pessoal cadastrado! Você será redirecionado para cadastrar um Dado Pessoal primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormDadosPessoais();
                return;
            }
            if(FormCliente.txtDocCliente.getText().isEmpty())
                JOptionPane.showMessageDialog(Principal.formcliente, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            
            Cliente cliente = null;
            String aux = FormCliente.cbxDadosPessoaisCliente.getSelectedItem().toString();
            String[] idCliente = aux.split(Pattern.quote(" --> "));
            int id = Integer.parseInt(idCliente[0]);
            cliente = new Cliente(
                    id, 
                    FormCliente.txtDocCliente.getText());
            
            ClienteDAO.inserir(cliente);
            limparCampos();
            Principal.mostrarTelaInicio();
            atualizar();
            
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Erro ao inserir os dados! Verifique os dados digitados!");
        }
        catch (Exception e) {
            System.err.println("Problema detectado! control" + e);
        }
    }

    public static void limparCampos() {
        FormCliente.txtDocCliente.setText("");
        FormCliente.cbxDadosPessoaisCliente.setSelectedIndex(0);
    }
    
    public static void preencherCampos(int indexcbxdados, String doc){
        FormCliente.txtDocCliente.setText(doc);
        FormCliente.cbxDadosPessoaisCliente.setSelectedIndex(indexcbxdados);
    }
    
    public static void excluir(int id){
        ClienteDAO.excluir(id);
        atualizar();
    }

    public static void atualizar() {
        ArrayList<Cliente> cliente = ClienteDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaCliente.getModel();
        modelo.setNumRows(0);
        for(Cliente c : cliente)
            modelo.addRow(new Object[]{c.getIdCliente(), c.getIdDadosPessoais(), c.getDocIdentificacao()});
    }

    public static void editar(int idAux) {
        if(FormCliente.txtDocCliente.getText().isEmpty())
                JOptionPane.showMessageDialog(Principal.formcliente, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            
        String aux = FormCliente.cbxDadosPessoaisCliente.getSelectedItem().toString();
        String[] idCliente = aux.split(Pattern.quote(" --> "));
        int id = Integer.parseInt(idCliente[0]);
        Cliente cliente_banco = new Cliente(
                idAux,
                id, 
                FormCliente.txtDocCliente.getText());
        ClienteDAO.editar(idAux, cliente_banco);
        limparCampos();
        Principal.mostrarTelaInicio();
        atualizar();
        
    }

    public static void capturarEditar(int idSelecionado) {
        int idcbx = -1;
        Cliente cliente = ClienteDAO.pesquisar1(idSelecionado);
        
        Principal.formcliente.cbxDadosPessoaisCliente.removeAllItems();
        
        String[] aux3 = cliente.toString().split(" --> ");
        String[] aux = aux3[1].split(" / ");
        
        int indexcbx = 0;
        
        ArrayList<DadosPessoais> dados = DadosDAO.listar();
        
        for(DadosPessoais d : dados){
            
            String[] aux2 = d.toString().split(" --> ");            
            if(aux2[0].equals(aux[0])){
                idcbx = indexcbx;
                break;
            }
            else
                indexcbx = indexcbx + 1;
        }
        Principal.mostrarTelaFormCliente();
        preencherCampos(indexcbx, cliente.getDocIdentificacao());
        editando = true;
        idAux = idSelecionado;
    }

    public static void vizualizar(String txt) {
        camposCliente(false);
        
        int idSelecionado = Integer.parseInt(txt);
        
        int idcbx = -1;
        Cliente cliente = ClienteDAO.pesquisar1(idSelecionado);
        
        Principal.formcliente.cbxDadosPessoaisCliente.removeAllItems();
        
        String[] aux3 = cliente.toString().split(" --> ");
        String[] aux = aux3[1].split(" / ");
        
        int indexcbx = 0;
        
        ArrayList<DadosPessoais> dados = DadosDAO.listar();
        
        for(DadosPessoais d : dados){
            
            String[] aux2 = d.toString().split(" --> ");            
            if(aux2[0].equals(aux[0])){
                idcbx = indexcbx;
                break;
            }
            else
                indexcbx = indexcbx + 1;
        }
        Principal.mostrarTelaFormCliente();
        Principal.formcliente.btnCancelarCliente.setVisible(false);
        Principal.formcliente.btnConfirmarCliente.setVisible(false);
        Principal.formcliente.btnSairCliente.setVisible(true);
        preencherCampos(indexcbx, cliente.getDocIdentificacao());
    }
    
    static void camposCliente(boolean status){
        FormCliente.txtDocCliente.setEnabled(status);
        formcliente.cbxDadosPessoaisCliente.setEnabled(status);
        editando = !status; 
   }
}
