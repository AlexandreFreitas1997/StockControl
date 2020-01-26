/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.inicio;
import DAO.OrcamentoDAO;
import DAO.VendaDAO;
import MODEL.Orcamento;
import MODEL.Venda;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlVenda {
    public static boolean editando;
    public static int idAux;
    
    static void camposVenda(boolean b) {
        Principal.formvenda.cbxOrcamentoVenda.setEnabled(b);
        editando = !b;
    }

    public static void vizualizar(String txt) {
        camposVenda(false);
        int idSelecionado = Integer.parseInt(txt);
        
        Venda venda = VendaDAO.pesquisar1(idSelecionado);
        String[] aux = venda.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxorcamento = 0;
        
        ArrayList<Orcamento> orcamento = OrcamentoDAO.listar();
        for(Orcamento o : orcamento){
            String[] aux2 = o.toString().split(" --> ");
            if(aux2[0].equals(aux1[0]))
                break;
            else
                indexcbxorcamento = indexcbxorcamento + 1;
        }
        
        Principal.mostrarTelaFormVenda();
        preencherCampos(indexcbxorcamento);
        Principal.formvenda.btnCancelarVenda.setVisible(false);
        Principal.formvenda.btnConfirmarVenda.setVisible(false);
        Principal.formvenda.btnSairVenda.setVisible(true);
        
    }

    public static void capturarEditar(int idSelecionado) {
        Venda venda = VendaDAO.pesquisar1(idSelecionado);
        String[] aux = venda.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxorcamento = 0;
        
        ArrayList<Orcamento> orcamento = OrcamentoDAO.listar();
        for(Orcamento o : orcamento){
            String[] aux2 = o.toString().split(" --> ");
            if(aux2[0].equals(aux1[0]))
                break;
            else
                indexcbxorcamento = indexcbxorcamento + 1;
        }
        
        Principal.mostrarTelaFormVenda();
        preencherCampos(indexcbxorcamento);
        editando = true;
        idAux = idSelecionado;
    }

    public static void excluir(int idSelecionado) {
        VendaDAO.excluir(idSelecionado);
        atualizar();
    }

    public static void limparCampos() {
        Principal.formvenda.cbxOrcamentoVenda.setSelectedIndex(0);
    }

    public static void preencherCampos(int indexcbxpedido){
        Principal.formvenda.cbxOrcamentoVenda.setSelectedIndex(indexcbxpedido);
    }
    
    public static void cadastrar() {
        try {
            if(Principal.formvenda.cbxOrcamentoVenda.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formorcamento, "Nenhum Pedido cadastrado! Você será redirecionado para cadastrar um novo Pedido primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormPedido();
            }
            else{
                Venda venda = null;
                String[] aux = Principal.formvenda.cbxOrcamentoVenda.getSelectedItem().toString().split(" --> ");

                int idOrcamento = Integer.parseInt(aux[0]);

                Date dataatual = new Date(System.currentTimeMillis());
                venda = new Venda(idOrcamento, dataatual);

                VendaDAO.inserir(venda);
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

    public static void editar(int idAux) {
        Venda venda = null;
        String[] aux = Principal.formvenda.cbxOrcamentoVenda.getSelectedItem().toString().split(" --> ");

        int idOrcamento = Integer.parseInt(aux[0]);

        Date dataatual = new Date(System.currentTimeMillis());
        venda = new Venda(idOrcamento, dataatual);

        VendaDAO.editar(idAux, venda);
        limparCampos();
        Principal.mostrarTelaInicio();
        atualizar();
    }

    static void popularcbxOrcamento() {
        Principal.formvenda.cbxOrcamentoVenda.removeAllItems();
        ArrayList<Orcamento> orcamento = OrcamentoDAO.listar();
        for(Orcamento o : orcamento)
            Principal.formvenda.cbxOrcamentoVenda.addItem(o.toString());
    }

    static void atualizar() {
        ArrayList<Venda> venda = VendaDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaVenda.getModel();
        modelo.setRowCount(0);
        for(Venda v : venda)
            modelo.addRow(new Object[]{v.getIdVenda(), v.getData(), v.getIdOrcamento()});
    }
    
}