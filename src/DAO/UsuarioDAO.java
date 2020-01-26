/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.Usuario;
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
public class UsuarioDAO {
    
public static void inserir(Usuario usuario) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into Usuario(Login, Senha, Tipo_Usuario) values(?, ?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setString(1, usuario.getLogin());
            stm.setString(2, usuario.getSenha());
            stm.setString(3, usuario.getTipoUsuario());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<Usuario> listar() {
        
        ResultSet resultado = null;
        ArrayList<Usuario> usuario = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Usuario";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Usuario usuario_banco = new Usuario(
                        resultado.getInt("idUsuario"),
                        resultado.getString("Login"),
                        resultado.getString("Senha"),
                        resultado.getString("Tipo_Usuario"));

                usuario.add(usuario_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return usuario;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from Usuario where idUsuario = ?";
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
    
    public static void editar(int id, Usuario usuario){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Usuario set Login = ?, Senha = ?, Tipo_Usuario = ? where idUsuario = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setString(1, usuario.getLogin());
            stm.setString(2, usuario.getSenha());
            stm.setString(3, usuario.getTipoUsuario());
            stm.setInt(4, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Usuario pesquisar1(int id){
        ResultSet resultado = null;
        Usuario usuario_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Usuario where idUsuario = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                usuario_banco = new Usuario(
                        resultado.getInt("idUsuario"),
                        resultado.getString("Login"),
                        resultado.getString("Senha"),
                        resultado.getString("Tipo_Usuario"));

            }
            stm.close();
            conectar.close();
            return usuario_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
