/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.Cliente;
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
public class ClienteDAO {
    public static void inserir(Cliente cliente) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into Cliente(IdDadosPessoais, Doc_Identificacao) values(?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setInt(1, cliente.getIdDadosPessoais());
            stm.setString(2, cliente.getDocIdentificacao());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado!" + e);
        }
    }

    public static ArrayList<Cliente> listar() {
        
        ResultSet resultado = null;
        ArrayList<Cliente> cliente = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Cliente";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Cliente cliente_banco = new Cliente(
                        resultado.getInt("idCliente"),
                        resultado.getInt("IdDadosPessoais"),
                        resultado.getString("Doc_Identificacao"));

                cliente.add(cliente_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return cliente;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from Cliente where idCliente = ?";
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
    
    public static void editar(int id, Cliente cliente){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Cliente set IdDadosPessoais = ?, Doc_Identificacao = ? where idCliente = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setInt(1, cliente.getIdDadosPessoais());
            stm.setString(2, cliente.getDocIdentificacao());
            stm.setInt(3, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Cliente pesquisar1(int id){
        ResultSet resultado = null;
        Cliente cliente_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Cliente where idCliente = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                cliente_banco = new Cliente(
                        resultado.getInt("idCliente"),
                        resultado.getInt("IdDadosPessoais"),
                        resultado.getString("Doc_Identificacao"));

            }
            stm.close();
            conectar.close();
            return cliente_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
