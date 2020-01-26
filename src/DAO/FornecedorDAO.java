/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.Fornecedor;
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
public class FornecedorDAO {

    public static void inserir(Fornecedor fornecedor) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into Fornecedor(IdDadosPessoais) values(?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setInt(1, fornecedor.getIdDadosPessoais());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<Fornecedor> listar() {
        
        ResultSet resultado = null;
        ArrayList<Fornecedor> fornecedor = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Fornecedor";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Fornecedor fornecedor_banco = new Fornecedor(
                    resultado.getInt("IdFornecedor"),
                    resultado.getInt("IdDadosPessoais")
                    );

                fornecedor.add(fornecedor_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return fornecedor;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from Fornecedor where idFornecedor = ?";
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
    
    public static void editar(int id, Fornecedor fornecedor){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Fornecedor set IdDadosPessoais = ? where idFornecedor = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setInt(1, fornecedor.getIdDadosPessoais());
            stm.setInt(2, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Fornecedor pesquisar1(int id){
        ResultSet resultado = null;
        Fornecedor fornecedor_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Fornecedor where idFornecedor = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                fornecedor_banco = new Fornecedor(
                    resultado.getInt("IdFornecedor"),
                    resultado.getInt("IdDadosPessoais")
                    );

            }
            stm.close();
            conectar.close();
            return fornecedor_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
