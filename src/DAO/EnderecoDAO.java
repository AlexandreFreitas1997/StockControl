/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import static CONTROL.Principal.inicio;
import GUI.FormEndereco;
import MODEL.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class EnderecoDAO {

    public static void inserir(Endereco endereco) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into endereco(Logradouro, Numero, Complemento, Referencia, Bairro, Cidade, Estado, CEP) values(?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            stm.setString(1, endereco.getLogradouro());
            stm.setString(2, endereco.getNumero());
            stm.setString(3, endereco.getComplemento());
            stm.setString(4, endereco.getReferencia());
            stm.setString(5, endereco.getBairro());
            stm.setString(6, endereco.getCidade());
            stm.setString(7, endereco.getEstado());
            stm.setString(8, endereco.getCep());

            stm.execute();
            stm.close();

            conectar.close();
        
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        
    }

    public static ArrayList<Endereco> listar() {
        
        ResultSet resultado = null;
        ArrayList<Endereco> enderecos = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from endereco";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Endereco endereco_banco = new Endereco(
                        resultado.getInt("IdEndereco"),
                        resultado.getString("Logradouro"), 
                        resultado.getString("Numero"), 
                        resultado.getString("Complemento"), 
                        resultado.getString("Referencia"), 
                        resultado.getString("Bairro"), 
                        resultado.getString("Cidade"), 
                        resultado.getString("Estado"), 
                        resultado.getString("CEP"));
                enderecos.add(endereco_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return enderecos;
    }

    public static void excluir(int id) {
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from endereco where idEndereco = ?";
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
    
    public static void editar(int id, Endereco endereco){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update endereco set Logradouro = ?, Numero = ?, Complemento = ?, Referencia = ?, Bairro = ?, Cidade = ?, Estado = ?, CEP = ? where idEndereco = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setString(1, endereco.getLogradouro());
            stm.setString(2, endereco.getNumero());
            stm.setString(3, endereco.getComplemento());
            stm.setString(4, endereco.getReferencia());
            stm.setString(5, endereco.getBairro());
            stm.setString(6, endereco.getCidade());
            stm.setString(7, endereco.getEstado());
            stm.setString(8, endereco.getCep());
            stm.setInt(9, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Endereco pesquisar1(int id){
        Endereco endereco_banco = null;
        ResultSet resultado = null;        
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from endereco where idEndereco = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                endereco_banco = new Endereco(resultado.getInt("IdEndereco"),
                        resultado.getString("Logradouro"), 
                        resultado.getString("Numero"), 
                        resultado.getString("Complemento"), 
                        resultado.getString("Referencia"), 
                        resultado.getString("Bairro"), 
                        resultado.getString("Cidade"), 
                        resultado.getString("Estado"), 
                        resultado.getString("CEP"));
            }
            stm.close();
            conectar.close();
            return endereco_banco;
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
    }
    
}
