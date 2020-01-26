/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.Tipo;
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
public class TipoDAO {
    
    public static void inserir(Tipo tipo) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into Tipo(Nome) values(?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setString(1, tipo.getNome());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<Tipo> listar() {
        
        ResultSet resultado = null;
        ArrayList<Tipo> tipo = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Tipo";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Tipo tipo_banco = new Tipo(
                        resultado.getInt("idTipo"),
resultado.getString("Nome"));

                tipo.add(tipo_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return tipo;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from Tipo where idTipo = ?";
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
    
    public static void editar(int id, Tipo tipo){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Tipo set Nome  = ? where idTipo = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setString(1, tipo.getNome());
            stm.setInt(2, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Tipo pesquisar1(int id){
        ResultSet resultado = null;
        Tipo tipo_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Tipo where idTipo = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                tipo_banco = new Tipo(
                        resultado.getInt("idTipo"),
resultado.getString("Nome"));

            }
            stm.close();
            conectar.close();
            return tipo_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
