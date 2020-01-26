/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.inicio;
import DAO.OrcamentoDAO;
import DAO.PedidoDAO;
import MODEL.Orcamento;
import MODEL.Pedido;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlOrcamento {
    public static boolean editando;
    public static int idAux;
    
    static void camposOrcamento(boolean b) {
        Principal.formorcamento.cbxPedidoOrcamento.setEnabled(b);
        editando = !b;
    }

    public static void vizualizar(String txt) {
        camposOrcamento(false);
        int idSelecionado = Integer.parseInt(txt);
        
        Orcamento orcamento = OrcamentoDAO.pesquisar1(idSelecionado);
        String[] aux = orcamento.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxpedido = 0;
        
        ArrayList<Pedido> pedido = PedidoDAO.listar();
        for(Pedido p : pedido){
            String[] aux2 = p.toString().split(" --> ");
            if(aux2[0].equals(aux1[0]))
                break;
            else
                indexcbxpedido = indexcbxpedido + 1;
        }
        
        Principal.mostrarTelaFormOrcamento();
        preencherCampos(indexcbxpedido);
        Principal.formorcamento.btnCancelarOrcamento.setVisible(false);
        Principal.formorcamento.btnConfirmarOrcamento.setVisible(false);
        Principal.formorcamento.btnSairOrcamento.setVisible(true);
        
    }

    public static void capturarEditar(int idSelecionado) {
        Orcamento orcamento = OrcamentoDAO.pesquisar1(idSelecionado);
        String[] aux = orcamento.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxpedido = 0;
        
        ArrayList<Pedido> pedido = PedidoDAO.listar();
        for(Pedido p : pedido){
            String[] aux2 = p.toString().split(" --> ");
            if(aux2[0].equals(aux1[0]))
                break;
            else
                indexcbxpedido = indexcbxpedido + 1;
        }
        
        Principal.mostrarTelaFormOrcamento();
        preencherCampos(indexcbxpedido);
        editando = true;
        idAux = idSelecionado;
    }

    public static void excluir(int idSelecionado) {
        OrcamentoDAO.excluir(idSelecionado);
        atualizar();
    }

    public static void limparCampos() {
        Principal.formorcamento.cbxPedidoOrcamento.setSelectedIndex(0);
    }

    public static void preencherCampos(int indexcbxpedido){
        Principal.formorcamento.cbxPedidoOrcamento.setSelectedIndex(indexcbxpedido);
    }
    
    public static void cadastrar() {
        try {
            if(Principal.formorcamento.cbxPedidoOrcamento.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formorcamento, "Nenhum Pedido cadastrado! Você será redirecionado para cadastrar um novo Pedido primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormPedido();
            }
            else{
                Orcamento orcamento = null;
                String[] aux = Principal.formorcamento.cbxPedidoOrcamento.getSelectedItem().toString().split(" --> ");
                
                int idPedido = Integer.parseInt(aux[0]);
                
                Date dataatual = new Date(System.currentTimeMillis());
                orcamento = new Orcamento(idPedido, dataatual);
                
                OrcamentoDAO.inserir(orcamento);
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
        Orcamento orcamento = null;
        String[] aux = Principal.formorcamento.cbxPedidoOrcamento.getSelectedItem().toString().split(" --> ");

        int idPedido = Integer.parseInt(aux[0]);

        Date dataatual = new Date(System.currentTimeMillis());
        orcamento = new Orcamento(idPedido, dataatual);

        OrcamentoDAO.editar(idAux, orcamento);
        limparCampos();
        Principal.mostrarTelaInicio();
        atualizar();
    }

    static void popularcbxPedido() {
        Principal.formorcamento.cbxPedidoOrcamento.removeAllItems();
        ArrayList<Pedido> pedido = PedidoDAO.listar();
        for(Pedido p : pedido)
            Principal.formorcamento.cbxPedidoOrcamento.addItem(p.toString());
    }

    static void atualizar() {
        ArrayList<Orcamento> orcamento = OrcamentoDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaOrcamento.getModel();
        modelo.setRowCount(0);
        for(Orcamento o : orcamento)
            modelo.addRow(new Object[]{o.getIdOrcamento(), o.getData(), o.getIdPedido()});
    }
    
}
