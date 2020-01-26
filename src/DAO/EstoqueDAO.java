/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.Estoque;
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
public class EstoqueDAO {
    
    public static void inserir(Estoque estoque) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into Estoque(IdEndereco) values(?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setInt(1, estoque.getIdEndereco());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<Estoque> listar() {
        
        ResultSet resultado = null;
        ArrayList<Estoque> estoque = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Estoque";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Estoque estoque_banco = new Estoque(
                        resultado.getInt("idEstoque"),
                        resultado.getInt("idEndereco"));

                estoque.add(estoque_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return estoque;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from Estoque where idEstoque = ?";
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
    
    public static void editar(int id, Estoque estoque){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Estoque set IdEndereco  = ? where idEstoque = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setInt(1, estoque.getIdEndereco());
            stm.setInt(2, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Estoque pesquisar1(int id){
        ResultSet resultado = null;
        Estoque estoque_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Estoque where idEstoque = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                estoque_banco = new Estoque(
                        resultado.getInt("idEstoque"),
                        resultado.getInt("idEndereco"));

            }
            stm.close();
            conectar.close();
            return estoque_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
