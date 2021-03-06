/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.ItensPedido;
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
public class ItensPedidoDAO {
    public static void inserir(ItensPedido itensPedido) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into ItensPedido(IdProduto, IdPedido, Quantidade) values(?, ?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setInt(1, itensPedido.getIdProduto());
            stm.setInt(2, itensPedido.getIdPedido());
            stm.setInt(3, itensPedido.getQuantidade());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<ItensPedido> listar() {
        
        ResultSet resultado = null;
        ArrayList<ItensPedido> itensPedido = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from ItensPedido";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                ItensPedido itensPedido_banco = new ItensPedido(
                        resultado.getInt("idItens_Pedido"),
                        resultado.getInt("IdProduto"),
                        resultado.getInt("IdPedido"),
                        resultado.getInt("Quantidade"));

                itensPedido.add(itensPedido_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return itensPedido;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from ItensPedido where idItens_Pedido = ?";
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
    
    public static void editar(int id, ItensPedido itensPedido){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update ItensPedido set IdProduto = ?, IdPedido = ?, Quantidade = ? where idItens_Pedido = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setInt(1, itensPedido.getIdProduto());
            stm.setInt(2, itensPedido.getIdPedido());
            stm.setInt(3, itensPedido.getQuantidade());
                        stm.setInt(4, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static ItensPedido pesquisar1(int id){
        ResultSet resultado = null;
        ItensPedido itensPedido_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from ItensPedido where idItens_Pedido = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                itensPedido_banco = new ItensPedido(
                        resultado.getInt("idItens_Pedido"),
                        resultado.getInt("IdProduto"),
                        resultado.getInt("IdPedido"),
                        resultado.getInt("Quantidade"));

            }
            stm.close();
            conectar.close();
            return itensPedido_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
