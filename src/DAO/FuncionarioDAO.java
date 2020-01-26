/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.Funcionario;
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
public class FuncionarioDAO {

    public static void inserir(Funcionario funcionario) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into Funcionario(IdUsuario, IdDadosPessoais) values(?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setInt(1, funcionario.getIdUsuario());
            stm.setInt(2, funcionario.getIdDadosPessoais());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<Funcionario> listar() {
        
        ResultSet resultado = null;
        ArrayList<Funcionario> funcionario = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Funcionario";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Funcionario funcionario_banco = new Funcionario(
                        resultado.getInt("idFuncionario"),
                        resultado.getInt("IdUsuario"),
                        resultado.getInt("IdDadosPessoais"));

                funcionario.add(funcionario_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return funcionario;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from Funcionario where idFuncionario = ?";
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
    
    public static void editar(int id, Funcionario funcionario){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Funcionario set IdUsuario = ?, IdDadosPessoais = ? where idFuncionario = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setInt(1, funcionario.getIdUsuario());
            stm.setInt(2, funcionario.getIdDadosPessoais());
            stm.setInt(3, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Funcionario pesquisar1(int id){
        ResultSet resultado = null;
        Funcionario funcionario_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Funcionario where idFuncionario = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                funcionario_banco = new Funcionario(
                        resultado.getInt("idFuncionario"),
                        resultado.getInt("IdUsuario"),
                        resultado.getInt("IdDadosPessoais"));

            }
            stm.close();
            conectar.close();
            return funcionario_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
