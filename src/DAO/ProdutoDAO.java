/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.Produto;
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
public class ProdutoDAO {
    public static void inserir(Produto produto) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into Produto(Nome, Preco, Genero, Customizavel, IdTipo) values(?, ?, ?, ?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setString(1, produto.getNome());
            stm.setFloat(2, produto.getPreco());
            stm.setString(3, produto.getGenero());
            stm.setBoolean(4, produto.isCustomizavel());
            stm.setInt(5, produto.getIdTipo());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<Produto> listar() {
        
        ResultSet resultado = null;
        ArrayList<Produto> produto = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Produto";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Produto produto_banco = new Produto(
                        resultado.getInt("idProduto"),
                        resultado.getString("Nome"),
                        resultado.getFloat("Preco"),
                        resultado.getString("Genero"),
                        resultado.getBoolean("Customizavel"),
                        resultado.getInt("IdTipo"));

                produto.add(produto_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return produto;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from Produto where idProduto  = ?";
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
    
    public static void editar(int id, Produto produto){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Produto set Nome = ?, Preco = ?, Genero = ?, Customizavel = ?, IdTipo = ? where idProduto  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setString(1, produto.getNome());
            stm.setFloat(2, produto.getPreco());
            stm.setString(3, produto.getGenero());
            stm.setBoolean(4, produto.isCustomizavel());
            stm.setInt(5, produto.getIdTipo());
            stm.setInt(6, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Produto pesquisar1(int id){
        ResultSet resultado = null;
        Produto produto_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Produto where idProduto  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                produto_banco = new Produto(
                        resultado.getInt("idProduto"),
                        resultado.getString("Nome"),
                        resultado.getFloat("Preco"),
                        resultado.getString("Genero"),
                        resultado.getBoolean("Customizavel"),
                        resultado.getInt("IdTipo"));

            }
            stm.close();
            conectar.close();
            return produto_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
