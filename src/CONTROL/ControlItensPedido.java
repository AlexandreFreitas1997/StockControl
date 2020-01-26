/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.inicio;
import DAO.ItensPedidoDAO;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import MODEL.ItensPedido;
import MODEL.Pedido;
import MODEL.Produto;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlItensPedido {
    public static boolean editando;
    public static int idAux;
    
    static void atualizar() {
        ArrayList<ItensPedido> itenspedido = ItensPedidoDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaItensPedido.getModel();
        modelo.setRowCount(0);
        for(ItensPedido ip : itenspedido)
            modelo.addRow(new Object[]{ip.getIdItensPedido(), ip.getIdPedido(), ip.getIdProduto(), ip.getQuantidade()});
    }

    public static void excluir(int idSelecionado) {
        int idPedido = ItensPedidoDAO.pesquisar1(idSelecionado).getIdPedido();
        ItensPedidoDAO.excluir(idSelecionado);
        ControlPedido.atualizarValorTotal(idPedido);
        atualizar();
        ControlPedido.atualizar();
    }

    public static void vizualizar(String txt) {
        camposItensPedido(false);
        int idSelecionado = Integer.parseInt(txt);
        
        ItensPedido itenspedido = ItensPedidoDAO.pesquisar1(idSelecionado);
        String[] aux = itenspedido.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxproduto = 0;
        int indexcbxpedido = 0;
        
        ArrayList<Produto> produto = ProdutoDAO.listar();
        for(Produto p : produto){
            String[] aux2 = p.toString().split(" --> ");
            if(aux2[0].equals(aux1[0]))
                break;
            else
                indexcbxproduto = indexcbxproduto + 1;
        }
            
        ArrayList<Pedido> pedido = PedidoDAO.listar();
        for(Pedido p : pedido){
            String[] aux2 = p.toString().split(" --> ");
            if(aux2[0].equals(aux1[1]))
                break;
            else
                indexcbxpedido = indexcbxpedido + 1;
        }
        int quantidade = Integer.parseInt(aux1[2]);
        
        Principal.mostrarTelaFormItensPedido();
        preencherCampos(indexcbxpedido, indexcbxproduto, quantidade);
        Principal.formitensPedido.btnCancelarItensPedido.setVisible(false);
        Principal.formitensPedido.btnConfirmarItensPedido.setVisible(false);
        Principal.formitensPedido.btnSairItensPedido.setVisible(true);
    }

    public static void capturarEditar(int idSelecionado) {
         ItensPedido itenspedido = ItensPedidoDAO.pesquisar1(idSelecionado);
        String[] aux = itenspedido.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxproduto = 0;
        int indexcbxpedido = 0;
        
        ArrayList<Produto> produto = ProdutoDAO.listar();
        for(Produto p : produto){
            String[] aux2 = p.toString().split(" --> ");
            if(aux2[0].equals(aux1[0]))
                break;
            else
                indexcbxproduto = indexcbxproduto + 1;
        }
            
        ArrayList<Pedido> pedido = PedidoDAO.listar();
        for(Pedido p : pedido){
            String[] aux2 = p.toString().split(" --> ");
            if(aux2[0].equals(aux1[1]))
                break;
            else
                indexcbxpedido = indexcbxpedido + 1;
        }
        int quantidade = Integer.parseInt(aux1[2]);
        
        Principal.mostrarTelaFormItensPedido();
        preencherCampos(indexcbxpedido, indexcbxproduto, quantidade);
        editando = true;
        idAux = idSelecionado;
    }

    static void popularcbxProduto() {
        Principal.formitensPedido.cbxProdutoItensPedido.removeAllItems();
        ArrayList<Produto> produto = ProdutoDAO.listar();
        for(Produto p : produto)
            Principal.formitensPedido.cbxProdutoItensPedido.addItem(p.toString());
    }

    static void popularcbxPedido() {
        Principal.formitensPedido.cbxPedidoItensPedido.removeAllItems();
        ArrayList<Pedido> pedido = PedidoDAO.listar();
        for(Pedido p : pedido)
            Principal.formitensPedido.cbxPedidoItensPedido.addItem(p.toString());
    }

    public static void limparCampos() {
        Principal.formitensPedido.cbxPedidoItensPedido.setSelectedIndex(0);
        Principal.formitensPedido.cbxProdutoItensPedido.setSelectedIndex(0);
        Principal.formitensPedido.txtQuantidadeItensPedido.setText("");
    }

    public static void preencherCampos(int indexcbxpedido, int indexcbxproduto, int quantidade){
        Principal.formitensPedido.cbxPedidoItensPedido.setSelectedIndex(indexcbxpedido);
        Principal.formitensPedido.cbxProdutoItensPedido.setSelectedIndex(indexcbxproduto);
        Principal.formitensPedido.txtQuantidadeItensPedido.setText(String.valueOf(quantidade));
    }
    
    public static void cadastrar() {
        try {
            if(Principal.formitensPedido.cbxPedidoItensPedido.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formitensPedido, "Nenhum Pedido cadastrado! Você será redirecionado para cadastrar um novo Pedido primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormPedido();
            }
            if(Principal.formitensPedido.cbxProdutoItensPedido.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formitensPedido, "Nenhum Produto cadastrado! Você será redirecionado para cadastrar um novo Produto primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormProduto();
            }
            else{
                ItensPedido itenspedido = null;
                String[] aux = Principal.formitensPedido.cbxPedidoItensPedido.getSelectedItem().toString().split(" --> ");
                String[] aux1 = Principal.formitensPedido.cbxProdutoItensPedido.getSelectedItem().toString().split(" --> ");
                int idPedido = Integer.parseInt(aux[0]);
                int idProduto = Integer.parseInt(aux1[0]);
                
                itenspedido = new ItensPedido(idProduto, idPedido, 
                        Integer.parseInt(Principal.formitensPedido.txtQuantidadeItensPedido.getText()));
                
                ItensPedidoDAO.inserir(itenspedido);
                ControlPedido.atualizarValorTotal(idPedido);
                limparCampos();
                Principal.mostrarTelaInicio();
                atualizar();
                ControlPedido.atualizar();
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Erro ao inserir os dados! Verifique os dados digitados!");
        }
        catch (Exception e) {
            System.err.println("Problema detectado! control" + e);
        }
    }

    public static void editar(int idAux) {
        ItensPedido itenspedido = null;
        String[] aux = Principal.formitensPedido.cbxPedidoItensPedido.getSelectedItem().toString().split(" --> ");
        String[] aux1 = Principal.formitensPedido.cbxProdutoItensPedido.getSelectedItem().toString().split(" --> ");
        int idPedido = Integer.parseInt(aux[0]);
        int idProduto = Integer.parseInt(aux1[0]);

        itenspedido = new ItensPedido(idProduto, idPedido, 
                Integer.parseInt(Principal.formitensPedido.txtQuantidadeItensPedido.getText()));

        ItensPedidoDAO.editar(idAux, itenspedido);
        ControlPedido.atualizarValorTotal(idPedido);
        limparCampos();
        Principal.mostrarTelaInicio();
        atualizar();
        ControlPedido.atualizar();
    }

    static void camposItensPedido(boolean b) {
        Principal.formitensPedido.cbxPedidoItensPedido.setEnabled(b);
        Principal.formitensPedido.cbxProdutoItensPedido.setEnabled(b);
        Principal.formitensPedido.txtQuantidadeItensPedido.setEnabled(b);
        editando = !b;
    }
        
}
