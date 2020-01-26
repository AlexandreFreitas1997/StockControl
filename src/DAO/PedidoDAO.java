/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.Pedido;
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
public class PedidoDAO {
    public static void inserir(Pedido pedido) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into Pedido(Data, ValorTotal, IdStatus, IdCliente) values(?, ?, ?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setDate(1, pedido.getData());
            stm.setFloat(2, pedido.getValorTotal());
            stm.setInt(3, pedido.getIdStatus());
            stm.setInt(4, pedido.getIdCliente());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<Pedido> listar() {
        
        ResultSet resultado = null;
        ArrayList<Pedido> pedido = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Pedido";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Pedido pedido_banco = new Pedido(
                        resultado.getInt("idPedido"),
                        resultado.getDate("Data"),
                        resultado.getFloat("ValorTotal"),
                        resultado.getInt("IdStatus"),
                        resultado.getInt("IdCliente"));

                pedido.add(pedido_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return pedido;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from Pedido where idPedido  = ?";
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
    
    public static void editar(int id, Pedido pedido){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Pedido set Data = ?, ValorTotal = ?, IdStatus = ?, IdCliente = ? where idPedido  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setDate(1, pedido.getData());
            stm.setFloat(2, pedido.getValorTotal());
            stm.setInt(3, pedido.getIdStatus());
            stm.setInt(4, pedido.getIdCliente());
            stm.setInt(5, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Pedido pesquisar1(int id){
        ResultSet resultado = null;
        Pedido pedido_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Pedido where idPedido  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                pedido_banco = new Pedido(
                        resultado.getInt("idPedido"),
                        resultado.getDate("Data"),
                        resultado.getFloat("ValorTotal"),
                        resultado.getInt("IdStatus"),
                        resultado.getInt("IdCliente"));

            }
            stm.close();
            conectar.close();
            return pedido_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }

    public static void editarValorTotal(int id, float valortotal) {
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Pedido set ValorTotal = ? where idPedido = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setFloat(1, valortotal);
            stm.setInt(2, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
}
