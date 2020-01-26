/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.Partes;
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
public class PartesDAO {
    public static void inserir(Partes partes) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into Partes(Nome, Preco) values(?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setString(1, partes.getNome());
            stm.setFloat(2, partes.getPreco());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<Partes> listar() {
        
        ResultSet resultado = null;
        ArrayList<Partes> partes = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Partes";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Partes partes_banco = new Partes(
                        resultado.getInt("idPartes"),
resultado.getString("Nome"),
resultado.getFloat("Preco"));

                partes.add(partes_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return partes;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from Partes where idPartes = ?";
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
    
    public static void editar(int id, Partes partes){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Partes set Nome = ?, Preco = ? where idPartes = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setString(1, partes.getNome());
            stm.setFloat(2, partes.getPreco());
            stm.setInt(3, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Partes pesquisar1(int id){
        ResultSet resultado = null;
        Partes partes_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Partes where idPartes = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                partes_banco = new Partes(
                        resultado.getInt("idPartes"),
                        resultado.getString("Nome"),
                        resultado.getFloat("Preco"));

            }
            stm.close();
            conectar.close();
            return partes_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
