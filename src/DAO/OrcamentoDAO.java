/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.Orcamento;
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
public class OrcamentoDAO {
    public static void inserir(Orcamento orcamento) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into Orcamento(IdPedido, Data) values(?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setInt(1, orcamento.getIdPedido());
            stm.setDate(2, orcamento.getData());


            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<Orcamento> listar() {
        
        ResultSet resultado = null;
        ArrayList<Orcamento> orcamento = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Orcamento";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Orcamento orcamento_banco = new Orcamento(
                        resultado.getInt("idOrcamento"),
                        resultado.getInt("IdPedido"),
                        resultado.getDate("Data")
                        );

                orcamento.add(orcamento_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return orcamento;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from Orcamento where idOrcamento  = ?";
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
    
    public static void editar(int id, Orcamento orcamento){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Orcamento set IdPedido = ?, Data = ? where idOrcamento  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setInt(1, orcamento.getIdPedido());
            stm.setDate(2, orcamento.getData());

            stm.setInt(3, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Orcamento pesquisar1(int id){
        ResultSet resultado = null;
        Orcamento orcamento_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Orcamento where idOrcamento  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                orcamento_banco = new Orcamento(
                        resultado.getInt("idOrcamento"),
                        resultado.getInt("IdPedido"),
                        resultado.getDate("Data")
                        );

            }
            stm.close();
            conectar.close();
            return orcamento_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
