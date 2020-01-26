/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.inicio;
import DAO.PartesDAO;
import DAO.PartesProdutoDAO;
import DAO.ProdutoDAO;
import MODEL.Partes;
import MODEL.PartesProduto;
import MODEL.Produto;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlPartesProduto {
    
    public static int idAux;
    public static boolean editando = false;
    
    public static void popularcbxPartes(){
        Principal.formpartesProduto.cbxPartePartesProduto.removeAllItems();
        ArrayList<Partes> partes = PartesDAO.listar();
        for(Partes p : partes)
            Principal.formpartesProduto.cbxPartePartesProduto.addItem(p.toString());
    }
    
    public static void popularcbxProduto(){
        Principal.formpartesProduto.cbxProdutoPartesProduto.removeAllItems();
        ArrayList<Produto> produto = ProdutoDAO.listar();
        for(Produto p : produto)
            Principal.formpartesProduto.cbxProdutoPartesProduto.addItem(p.toString());
    }

    static void camposPartesProduto(boolean b) {
        Principal.formpartesProduto.cbxPartePartesProduto.setEnabled(b);
        Principal.formpartesProduto.cbxProdutoPartesProduto.setEnabled(b);
        editando = !b;
    }

    public static void atualizar() {
        ArrayList<PartesProduto> partesproduto = PartesProdutoDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaPartesProduto.getModel();
        modelo.setRowCount(0);
        for(PartesProduto pp : partesproduto)
            modelo.addRow(new Object[]{pp.getIdPartesProduto(), pp.getIdPartes(), pp.getIdProduto()});
    }

    public static void excluir(int idSelecionado) {
        PartesProdutoDAO.excluir(idSelecionado);
        atualizar();
    }

    public static void vizualizar(String txt) {
        camposPartesProduto(false);
        int idSelecionado = Integer.parseInt(txt);
        
        PartesProduto partesproduto = PartesProdutoDAO.pesquisar1(idSelecionado);
        String[] aux = partesproduto.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxpartes = 0;
        int indexcbxproduto = 0;
        
        ArrayList<Partes> partes = PartesDAO.listar();
        for(Partes p : partes){
            String[] aux2 = p.toString().split(" --> ");
            if(aux2[0].equals(aux1[0]))
                break;
            else
                indexcbxpartes = indexcbxpartes + 1;
        }
        
        
        ArrayList<Produto> produtos = ProdutoDAO.listar();
        for(Produto p : produtos){
            String[] aux2 = p.toString().split(" --> ");
            if(aux2[0].equals(aux1[1]))
                break;
            else
                indexcbxproduto = indexcbxproduto + 1;
        }
        
        Principal.mostrarTelaFormPartesProduto();
        preencherCampos(indexcbxpartes, indexcbxproduto);
        Principal.formpartesProduto.btnCancelarPartesProduto.setVisible(false);
        Principal.formpartesProduto.btnConfirmarPartesProduto.setVisible(false);
        Principal.formpartesProduto.btnSairPartesProduto.setVisible(true);
        
    }

    public static void capturarEditar(int idSelecionado) {
        PartesProduto partesproduto = PartesProdutoDAO.pesquisar1(idSelecionado);
        String[] aux = partesproduto.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxpartes = 0;
        int indexcbxproduto = 0;
        
        ArrayList<Partes> partes = PartesDAO.listar();
        for(Partes p : partes){
            String[] aux2 = p.toString().split(" --> ");
            if(aux2[0].equals(aux1[0]))
                break;
            else
                indexcbxpartes = indexcbxpartes + 1;
        }
        
        
        ArrayList<Produto> produtos = ProdutoDAO.listar();
        for(Produto p : produtos){
            String[] aux2 = p.toString().split(" --> ");
            if(aux2[0].equals(aux1[1]))
                break;
            else
                indexcbxproduto = indexcbxproduto + 1;
        }
        
        Principal.mostrarTelaFormPartesProduto();
        preencherCampos(indexcbxpartes, indexcbxproduto);
        editando = true;
        idAux = idSelecionado;
    }

    public static void cadastrar() {
        try {
            if(Principal.formpartesProduto.cbxPartePartesProduto.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formpartesProduto, "Nenhuma Parte de Produto cadastrada! Você será redirecionado para cadastrar uma nova Parte de Produto primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormPartes();
            }
            if(Principal.formpartesProduto.cbxProdutoPartesProduto.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formpartesProduto, "Nenhum Produto cadastrado! Você será redirecionado para cadastrar um novo Produto primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormProduto();
            }
            else{
                PartesProduto partesproduto = null;
                String aux = Principal.formpartesProduto.cbxPartePartesProduto.getSelectedItem().toString();
                String[] aux2 = aux.split(" --> ");
                String aux3 = Principal.formpartesProduto.cbxProdutoPartesProduto.getSelectedItem().toString();
                String[] aux4 = aux3.split(" --> ");
                int idPartes = Integer.parseInt(aux2[0]);
                int idProduto = Integer.parseInt(aux4[0]);
                
                partesproduto = new PartesProduto(idPartes, idProduto);
                
                PartesProdutoDAO.inserir(partesproduto);
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
        Principal.formpartesProduto.cbxPartePartesProduto.setSelectedIndex(0);
        Principal.formpartesProduto.cbxProdutoPartesProduto.setSelectedIndex(0);
    }

    public static void preencherCampos(int indexcbxparte, int indexcbxproduto){
        Principal.formpartesProduto.cbxPartePartesProduto.setSelectedIndex(indexcbxparte);
        Principal.formpartesProduto.cbxProdutoPartesProduto.setSelectedIndex(indexcbxproduto);
    }
    
    public static void editar(int idAux) {
        String aux = Principal.formpartesProduto.cbxPartePartesProduto.getSelectedItem().toString();
        String[] aux2 = aux.split(" --> ");
        String aux3 = Principal.formpartesProduto.cbxProdutoPartesProduto.getSelectedItem().toString();
        String[] aux4 = aux3.split(" --> ");
        int idPartes = Integer.parseInt(aux2[0]);
        int idProduto = Integer.parseInt(aux4[0]);

        PartesProduto partesproduto = new PartesProduto(idPartes, idProduto);

        PartesProdutoDAO.editar(idAux, partesproduto);
        limparCampos();
        Principal.mostrarTelaInicio();
        atualizar();
    }
    
    
}
