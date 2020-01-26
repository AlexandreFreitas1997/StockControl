/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.formproduto;
import static CONTROL.Principal.inicio;
import DAO.ProdutoDAO;
import DAO.TipoDAO;
import MODEL.Produto;
import MODEL.Tipo;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlProduto {
    
    public static boolean editando = false;
    public static int idAux;

    public static void popularcbxTipo() {
        Principal.formproduto.cbxTipoProduto.removeAllItems();
        ArrayList<Tipo> tipos = TipoDAO.listar();
        for(Tipo t : tipos)
            Principal.formproduto.cbxTipoProduto.addItem(t.toString());
    }
    
    public static void cadastrar(){
        try {
            if(formproduto.cbxTipoProduto.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formproduto, "Nenhum Tipo cadastrado! Você será redirecionado para cadastrar um Tipo primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormTipo();
            }
            if(formproduto.txtNomeProduto.getText().isEmpty() ||
                    formproduto.txtNomeProduto.getText().isEmpty() ||
                    formproduto.cbxGeneroProduto.getSelectedIndex() == 0)
                JOptionPane.showMessageDialog(Principal.formdadospessoais, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
            else{
                Produto produto = null;
                String aux = formproduto.cbxTipoProduto.getSelectedItem().toString();
                String[] aux2 = aux.split(" --> ");
                int idTipo = Integer.parseInt(aux2[0]);
                String charGenero = formproduto.cbxGeneroProduto.getSelectedItem().toString();
                charGenero = charGenero.substring(0, 1);
                produto = new Produto(
                        formproduto.txtNomeProduto.getText(), 
                        Float.parseFloat(formproduto.txtPrecoProduto.getText()),
                        charGenero,
                        formproduto.cbCustomizavelProduto.isSelected(),
                        idTipo);
                
                ProdutoDAO.inserir(produto);
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
        camposProduto(false);
        
        int idSelecionado = Integer.parseInt(txt);
        Produto produto = ProdutoDAO.pesquisar1(idSelecionado);
        formproduto.cbxTipoProduto.removeAllItems();
        String[] aux = produto.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int idcbxgenero = 0;
        if(aux1[2].equals("U"))
            idcbxgenero = 1;
        if(aux1[2].equals("M"))
            idcbxgenero = 2;
        if(aux1[2].equals("F"))
            idcbxgenero = 2;
        
        boolean customizavel = false;
        if(aux1[3].equals("true"))
            customizavel = true;
                
        int idcbxtipo = -1;
        int index = 0;
        
        ArrayList<Tipo> tipos = TipoDAO.listar();
        
        for(Tipo t : tipos){
            String aux2[] = t.toString().split(" --> ");
            
            if(aux1[4].equals(aux2[0])){
                idcbxtipo = index;
                break;
            }
            else
                index = index + 1;
        }
        Principal.mostrarTelaFormProduto();
        formproduto.btnCancelarProduto.setVisible(false);
        formproduto.btnConfirmarProduto.setVisible(false);
        formproduto.btnSairProduto.setVisible(true);
        preencherCampos(aux1[0], aux1[1], idcbxgenero, customizavel, idcbxtipo);
        
        
    }
    
    public static void capturarEditar(int id){
        Produto produto = ProdutoDAO.pesquisar1(id);
        formproduto.cbxTipoProduto.removeAllItems();
        String[] aux = produto.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int idcbxgenero = 0;
        if(aux1[2].equals("U"))
            idcbxgenero = 1;
        if(aux1[2].equals("M"))
            idcbxgenero = 2;
        if(aux1[2].equals("F"))
            idcbxgenero = 2;
        
        boolean customizavel = false;
        if(aux1[3].equals("true"))
            customizavel = true;
                
        int idcbxtipo = -1;
        int index = 0;
        
        ArrayList<Tipo> tipos = TipoDAO.listar();
        
        for(Tipo t : tipos){
            String aux2[] = t.toString().split(" --> ");
            
            if(aux1[4].equals(aux2[0])){
                idcbxtipo = index;
                break;
            }
            else
                index = index + 1;
        }
        Principal.mostrarTelaFormProduto();
        preencherCampos(aux1[0], aux1[1], idcbxgenero, customizavel, idcbxtipo);
        
        editando = true;
        idAux = id;
    }
            
    public static void editar(int id){
        if(formproduto.txtNomeProduto.getText().isEmpty() ||
                formproduto.txtNomeProduto.getText().isEmpty() ||
                formproduto.cbxGeneroProduto.getSelectedIndex() == 0)
            JOptionPane.showMessageDialog(Principal.formdadospessoais, "Por favor preencha os campos OBRIGATÓRIOS!", "Erro", 0);
        else{
            Produto produto = null;
            String aux = formproduto.cbxTipoProduto.getSelectedItem().toString();
            String[] aux2 = aux.split(" --> ");
            int idTipo = Integer.parseInt(aux2[0]);
            String charGenero = formproduto.cbxGeneroProduto.getSelectedItem().toString();
            charGenero = charGenero.substring(0, 1);
            produto = new Produto(
                    formproduto.txtNomeProduto.getText(), 
                    Float.parseFloat(formproduto.txtPrecoProduto.getText()),
                    charGenero,
                    formproduto.cbCustomizavelProduto.isSelected(),
                    idTipo);

            ProdutoDAO.editar(id, produto);
            limparCampos();
            Principal.mostrarTelaInicio();
            atualizar();
        }
    }
    
    public static void excluir(int id){
        ProdutoDAO.excluir(id);
        atualizar();
    }
            
    public static void atualizar() {
        ArrayList<Produto> produtos = ProdutoDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaProduto.getModel();
        modelo.setNumRows(0);
        for(Produto p : produtos)
            modelo.addRow(new Object[]{p.getIdProduto(), p.getNome(), p.getPreco(), p.getGenero(), p.isCustomizavel(), p.getIdTipo()});
    }

    public static void limparCampos(){
        formproduto.txtNomeProduto.setText("");
        formproduto.txtPrecoProduto.setText("");
        formproduto.cbxGeneroProduto.setSelectedIndex(0);
        formproduto.cbCustomizavelProduto.setSelected(false);
        formproduto.cbxTipoProduto.setSelectedIndex(0);
    }
    
    public static void preencherCampos(String nome, String preco, int indexcbxgenero, boolean customizavel, int indexcbxtipo){
        formproduto.txtNomeProduto.setText(nome);
        formproduto.txtPrecoProduto.setText(preco);
        formproduto.cbxGeneroProduto.setSelectedIndex(indexcbxgenero);
        formproduto.cbCustomizavelProduto.setSelected(customizavel);
        formproduto.cbxTipoProduto.setSelectedIndex(indexcbxtipo);
    }
    
    static void camposProduto(boolean b) {
        formproduto.txtNomeProduto.setEnabled(b);
        formproduto.txtPrecoProduto.setEnabled(b);
        formproduto.cbxGeneroProduto.setEnabled(b);
        formproduto.cbCustomizavelProduto.setEnabled(b);
        formproduto.cbxTipoProduto.setEnabled(b);
        editando = !b;
    }

    
    
}
