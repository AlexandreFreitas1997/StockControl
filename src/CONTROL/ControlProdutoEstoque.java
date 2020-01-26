/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.inicio;
import DAO.EstoqueDAO;
import DAO.ProdutoDAO;
import DAO.ProdutoEstoqueDAO;
import MODEL.Estoque;
import MODEL.Produto;
import MODEL.ProdutosEstoque;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlProdutoEstoque {
    public static boolean editando = false;
    public static int idAux;
    
    static void atualizar() {
        ArrayList<ProdutosEstoque> produtosestoques = ProdutoEstoqueDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaProdutosEstoque.getModel();
        modelo.setRowCount(0);
        for(ProdutosEstoque pe : produtosestoques)
            modelo.addRow(new Object[]{pe.getIdProdutosEstoque(), pe.getIdEstoque(), pe.getIdProduto(), pe.getQuantidade()});
    }

    public static void excluir(int idSelecionado) {
        ProdutoEstoqueDAO.excluir(idSelecionado);
        atualizar();
    }

    public static void vizualizar(String txt) {
        camposProdutosEstoque(false);
        int idSelecionado = Integer.parseInt(txt);
        
        ProdutosEstoque produtosestoque = ProdutoEstoqueDAO.pesquisar1(idSelecionado);
        String[] aux = produtosestoque.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxproduto = 0;
        int indexcbxestoque = 0;
        
        ArrayList<Produto> produto = ProdutoDAO.listar();
        for(Produto p : produto){
            String[] aux2 = p.toString().split(" --> ");
            if(aux2[0].equals(aux1[1]))
                break;
            else
                indexcbxproduto = indexcbxproduto + 1;
        }
            
        ArrayList<Estoque> estoque = EstoqueDAO.listar();
        for(Estoque e : estoque){
            String[] aux2 = e.toString().split(" --> ");
            if(aux2[0].equals(aux1[0]))
                break;
            else
                indexcbxestoque = indexcbxestoque + 1;
        }
        int quantidade = Integer.parseInt(aux1[2]);
        
        
        
        Principal.mostrarTelaFormProdutosEstoque();
        preencherCampos(indexcbxestoque, indexcbxproduto, quantidade);
        editando = true;
        idAux = idSelecionado;
        Principal.formprodutosEstoque.btnCancelarProdutosEstoque.setVisible(false);
        Principal.formprodutosEstoque.btnConfirmarProdutosEstoque.setVisible(false);
        Principal.formprodutosEstoque.btnSairProdutosEstoque.setVisible(true);
    }

    public static void capturarEditar(int idSelecionado) {
        ProdutosEstoque produtosestoque = ProdutoEstoqueDAO.pesquisar1(idSelecionado);
        String[] aux = produtosestoque.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxproduto = 0;
        int indexcbxestoque = 0;
        
        ArrayList<Produto> produto = ProdutoDAO.listar();
        for(Produto p : produto){
            String[] aux2 = p.toString().split(" --> ");
            if(aux2[0].equals(aux1[0]))
                break;
            else
                indexcbxproduto = indexcbxproduto + 1;
        }
            
        ArrayList<Estoque> estoque = EstoqueDAO.listar();
        for(Estoque e : estoque){
            String[] aux2 = e.toString().split(" --> ");
            if(aux2[0].equals(aux1[1]))
                break;
            else
                indexcbxestoque = indexcbxestoque + 1;
        }
        int quantidade = Integer.parseInt(aux1[2]);
        
        Principal.mostrarTelaFormProdutosEstoque();
        preencherCampos(indexcbxestoque, indexcbxproduto, quantidade);
        editando = true;
        idAux = idSelecionado;
    }

    static void popularcbxProduto() {
        Principal.formprodutosEstoque.cbxProdutoProdutosEstoque.removeAllItems();
        ArrayList<Produto> produto = ProdutoDAO.listar();
        for(Produto p : produto)
            Principal.formprodutosEstoque.cbxProdutoProdutosEstoque.addItem(p.toString());
    }

    static void popularcbxEstoque() {
        Principal.formprodutosEstoque.cbxEstoqueProdutosEstoque.removeAllItems();
        ArrayList<Estoque> estoque = EstoqueDAO.listar();
        for(Estoque e : estoque)
            Principal.formprodutosEstoque.cbxEstoqueProdutosEstoque.addItem(e.toString());
    }

    public static void limparCampos() {
        Principal.formprodutosEstoque.cbxEstoqueProdutosEstoque.setSelectedIndex(0);
        Principal.formprodutosEstoque.cbxProdutoProdutosEstoque.setSelectedIndex(0);
        Principal.formprodutosEstoque.txtQuantidadeProdutosEstoque.setText("");
    }

    public static void preencherCampos(int indexcbxestoque, int indexcbxproduto, int quantidade){
        Principal.formprodutosEstoque.cbxEstoqueProdutosEstoque.setSelectedIndex(indexcbxestoque);
        Principal.formprodutosEstoque.cbxProdutoProdutosEstoque.setSelectedIndex(indexcbxproduto);
        Principal.formprodutosEstoque.txtQuantidadeProdutosEstoque.setText(String.valueOf(quantidade));
    }
    
    public static void cadastrar() {
        try {
            if(Principal.formprodutosEstoque.cbxEstoqueProdutosEstoque.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formitensPedido, "Nenhum Estoque cadastrado! Você será redirecionado para cadastrar um novo Estoque primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormEstoque();
            }
            if(Principal.formprodutosEstoque.cbxProdutoProdutosEstoque.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formitensPedido, "Nenhum Produto cadastrado! Você será redirecionado para cadastrar um novo Produto primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormProduto();
            }
            else{
                ProdutosEstoque produtosestoque = null;
                String[] aux = Principal.formprodutosEstoque.cbxEstoqueProdutosEstoque.getSelectedItem().toString().split(" --> ");
                String[] aux1 = Principal.formprodutosEstoque.cbxProdutoProdutosEstoque.getSelectedItem().toString().split(" --> ");
                int idEstoque = Integer.parseInt(aux[0]);
                int idProduto = Integer.parseInt(aux1[0]);

                produtosestoque = new ProdutosEstoque(idEstoque, idProduto, 
                        Integer.parseInt(Principal.formprodutosEstoque.txtQuantidadeProdutosEstoque.getText()));
                
                ProdutoEstoqueDAO.inserir(produtosestoque);
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
        ProdutosEstoque produtosestoque = null;
        String[] aux = Principal.formprodutosEstoque.cbxEstoqueProdutosEstoque.getSelectedItem().toString().split(" --> ");
        String[] aux1 = Principal.formprodutosEstoque.cbxProdutoProdutosEstoque.getSelectedItem().toString().split(" --> ");
        int idEstoque = Integer.parseInt(aux[0]);
        int idProduto = Integer.parseInt(aux1[0]);

        produtosestoque = new ProdutosEstoque(idEstoque, idProduto, 
                Integer.parseInt(Principal.formprodutosEstoque.txtQuantidadeProdutosEstoque.getText()));

        ProdutoEstoqueDAO.editar(idAux, produtosestoque);
        limparCampos();
        Principal.mostrarTelaInicio();
        atualizar();
    }

    static void camposProdutosEstoque(boolean b) {
        Principal.formprodutosEstoque.cbxEstoqueProdutosEstoque.setEnabled(b);
        Principal.formprodutosEstoque.cbxProdutoProdutosEstoque.setEnabled(b);
        Principal.formprodutosEstoque.txtQuantidadeProdutosEstoque.setEnabled(b);
        editando = !b;
    }
        
}

