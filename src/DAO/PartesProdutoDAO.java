/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.PartesProduto;
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
public class PartesProdutoDAO {
    public static void inserir(PartesProduto partesProduto) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into PartesProduto(IdPartes, IdProduto) values(?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setInt(1, partesProduto.getIdPartes());
            stm.setInt(2, partesProduto.getIdProduto());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<PartesProduto> listar() {
        
        ResultSet resultado = null;
        ArrayList<PartesProduto> partesProduto = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from PartesProduto";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                PartesProduto partesProduto_banco = new PartesProduto(
                        resultado.getInt("idPartes_Produto"),
                        resultado.getInt("IdPartes"),
                        resultado.getInt("IdProduto"));

                partesProduto.add(partesProduto_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return partesProduto;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from PartesProduto where idPartes_Produto  = ?";
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
    
    public static void editar(int id, PartesProduto partesProduto){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update PartesProduto set IdPartes = ?, IdProduto = ? where idPartes_Produto  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setInt(1, partesProduto.getIdPartes());
            stm.setInt(2, partesProduto.getIdProduto());
            stm.setInt(3, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static PartesProduto pesquisar1(int id){
        ResultSet resultado = null;
        PartesProduto partesProduto_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from PartesProduto where idPartes_Produto  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                partesProduto_banco = new PartesProduto(
                        resultado.getInt("idPartes_Produto"),
                        resultado.getInt("IdPartes"),
                        resultado.getInt("IdProduto"));

            }
            stm.close();
            conectar.close();
            return partesProduto_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
