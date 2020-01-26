/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.ProdutosEstoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandre Freitas
 */
public class ProdutoEstoqueDAO {
    public static void inserir(ProdutosEstoque produtosEstoque) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into ProdutosEstoque(IdEstoque, IdProduto, Quantidade) values(?, ?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setInt(1, produtosEstoque.getIdEstoque());
            stm.setInt(2, produtosEstoque.getIdProduto());
            stm.setInt(3, produtosEstoque.getQuantidade());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<ProdutosEstoque> listar() {
        
        ResultSet resultado = null;
        ArrayList<ProdutosEstoque> produtosEstoque = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from ProdutosEstoque";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                ProdutosEstoque produtosEstoque_banco = new ProdutosEstoque(
                        resultado.getInt("idProdutos_Estoque"),
                        resultado.getInt("IdEstoque"),
                        resultado.getInt("IdProduto"),
                        resultado.getInt("Quantidade"));

                produtosEstoque.add(produtosEstoque_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return produtosEstoque;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from ProdutosEstoque where idProdutos_Estoque  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1,id);
            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(Principal.inicio,"Não é possível excluir este campo pois ele está sendo usado em outra tabela!"
                    + " Para exclui-lo é necessário apagar todos os campos onde o mesmo é referenciado!", 
                    "Erro ao tentar excluir campo selecionado", 0);
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static void editar(int id, ProdutosEstoque produtosEstoque){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update ProdutosEstoque set IdEstoque = ?, IdProduto = ?, Quantidade = ? where idProdutos_Estoque  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setInt(1, produtosEstoque.getIdEstoque());
            stm.setInt(2, produtosEstoque.getIdProduto());
            stm.setInt(3, produtosEstoque.getQuantidade());
            stm.setInt(4, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static ProdutosEstoque pesquisar1(int id){
        ResultSet resultado = null;
        ProdutosEstoque produtosEstoque_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from ProdutosEstoque where idProdutos_Estoque  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                produtosEstoque_banco = new ProdutosEstoque(
                        resultado.getInt("idProdutos_Estoque"),
                        resultado.getInt("IdEstoque"),
                        resultado.getInt("IdProduto"),
                        resultado.getInt("Quantidade"));

            }
            stm.close();
            conectar.close();
            return produtosEstoque_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
