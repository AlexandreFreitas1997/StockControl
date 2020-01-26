/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.ServicoFornecedor;
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
public class ServicoFornecedorDAO {
    
    public static void inserir(ServicoFornecedor servicofornecedor) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into ServicoFornecedor(IdServico, IdFornecedor, Valor) values(?, ?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            stm.setInt(1, servicofornecedor.getIdServico());
            stm.setInt(2, servicofornecedor.getIdFornecedor());
            stm.setFloat(3, servicofornecedor.getValor());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<ServicoFornecedor> listar() {
        
        ResultSet resultado = null;
        ArrayList<ServicoFornecedor> servicofornecedor = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from ServicoFornecedor";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                ServicoFornecedor servicofornecedor_banco = new ServicoFornecedor(
                        resultado.getInt("IdServiçoFornecedor"),
                        resultado.getInt("IdServico"),
                        resultado.getInt("idFornecedor"),
                        resultado.getFloat("Valor"));

                servicofornecedor.add(servicofornecedor_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return servicofornecedor;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from ServicoFornecedor where IdServiçoFornecedor = ?";
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
    
    public static void editar(int id, ServicoFornecedor servicofornecedor){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update ServicoFornecedor set IdServico = ?, IdFornecedor = ?, Valor = ? where IdServiçoFornecedor = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setInt(1, servicofornecedor.getIdServico());
            stm.setInt(2, servicofornecedor.getIdFornecedor());
            stm.setFloat(3, servicofornecedor.getValor());
            stm.setInt(4, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static ServicoFornecedor pesquisar1(int id){
        ResultSet resultado = null;
        ServicoFornecedor servicofornecedor_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from ServicoFornecedor where IdServiçoFornecedor = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                servicofornecedor_banco = new ServicoFornecedor(
                        resultado.getInt("IdServiçoFornecedor"),
                        resultado.getInt("IdServico"),
                        resultado.getInt("idFornecedor"),
                        resultado.getFloat("Valor"));

            }
            stm.close();
            conectar.close();
            return servicofornecedor_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
    
}
