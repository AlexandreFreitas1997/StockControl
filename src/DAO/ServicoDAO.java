/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.Servico;
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
public class ServicoDAO {
    
    public static void inserir(Servico servico) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into Servico(Nome) values(?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setString(1, servico.getNome());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<Servico> listar() {
        
        ResultSet resultado = null;
        ArrayList<Servico> servico = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Servico";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Servico servico_banco = new Servico(
                        resultado.getInt("idServicos"),
                        resultado.getString("Nome"));

                servico.add(servico_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return servico;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from Servico where idServicos = ?";
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
    
    public static void editar(int id, Servico servico){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Servico set Nome = ? where idServicos = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setString(1, servico.getNome());
            stm.setInt(2, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Servico pesquisar1(int id){
        ResultSet resultado = null;
        Servico servico_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Servico where idServicos = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                servico_banco = new Servico(
                        resultado.getInt("idServicos"),
                        resultado.getString("Nome"));

            }
            stm.close();
            conectar.close();
            return servico_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
    
}
