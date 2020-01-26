/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.DadosPessoais;
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
public class DadosDAO {

    public static void inserir(DadosPessoais dados) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into dadospessoais(Nome, Email, Fixo, Celular, IdEndereco) values(?, ?, ?, ?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            stm.setString(1, dados.getNome());
            stm.setString(2, dados.getEmail());
            stm.setString(3, dados.getFixo());
            stm.setString(4, dados.getCelular());
            stm.setInt(5, dados.getIdEndereco());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<DadosPessoais> listar() {
        
        ResultSet resultado = null;
        ArrayList<DadosPessoais> dados = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from dadospessoais";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                DadosPessoais dados_banco = new DadosPessoais(
                        resultado.getInt("IdDados_Pessoais"),
                        resultado.getString("Nome"), 
                        resultado.getString("Email"), 
                        resultado.getString("Fixo"), 
                        resultado.getString("Celular"), 
                        resultado.getInt("IdEndereco"));

                dados.add(dados_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return dados;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from dadospessoais where idDados_Pessoais = ?";
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
    
    public static void editar(int id, DadosPessoais dados){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update dadospessoais set Nome = ?, Email = ?, Fixo = ?, Celular = ?, IdEndereco = ? where idDados_Pessoais = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setString(1, dados.getNome());
            stm.setString(2, dados.getEmail());
            stm.setString(3, dados.getFixo());
            stm.setString(4, dados.getCelular());
            stm.setInt(5, dados.getIdEndereco());
            stm.setInt(6, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static DadosPessoais pesquisar1(int id){
        ResultSet resultado = null;
        DadosPessoais dados_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from dadospessoais where IdDados_Pessoais = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                dados_banco = new DadosPessoais(
                        resultado.getInt("IdDados_Pessoais"),
                        resultado.getString("Nome"), 
                        resultado.getString("Email"), 
                        resultado.getString("Fixo"), 
                        resultado.getString("Celular"), 
                        resultado.getInt("IdEndereco"));

            }
            stm.close();
            conectar.close();
            return dados_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
