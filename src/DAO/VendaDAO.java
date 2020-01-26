/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROL.Principal;
import MODEL.Venda;
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
public class VendaDAO {
    public static void inserir(Venda venda) {
        
        try{
            Connection conectar = new ConexaoBanco().getConnection();

            String sql = "insert into Venda(IdOrcamento, Data) values(?, ?)";

            PreparedStatement stm = conectar.prepareStatement(sql);

            
            stm.setInt(1, venda.getIdOrcamento());
            stm.setDate(2, venda.getData());

            stm.execute();
            stm.close();

            conectar.close();
            
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }

    public static ArrayList<Venda> listar() {
        
        ResultSet resultado = null;
        ArrayList<Venda> venda = new ArrayList<>();
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Venda";

            PreparedStatement stm = conectar.prepareStatement(sql);
            resultado = stm.executeQuery();
            while(resultado.next()){
                Venda venda_banco = new Venda(
                        resultado.getInt("idVenda"),
                        resultado.getInt("IdOrcamento"),
                        resultado.getDate("Data")
                        );

                venda.add(venda_banco);
            }
            stm.close();
            conectar.close();
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
        }
        return venda;    
    }
    
    public static void excluir(int id){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "delete from Venda where idVenda  = ?";
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
    
    public static void editar(int id, Venda venda){
        try{
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "update Venda set IdOrcamento = ?, Data = ? where idVenda  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            
            stm.setInt(1, venda.getIdOrcamento());
            stm.setDate(2, venda.getData());
            stm.setInt(3, id);

            stm.execute();
            stm.close();
            conectar.close();
        }
        catch(SQLException e){
            System.err.println("Problema detectado! " + e);
        }
    }
    
    public static Venda pesquisar1(int id){
        ResultSet resultado = null;
        Venda venda_banco = null;
        
        try {
            Connection conectar = new ConexaoBanco().getConnection();
            String sql = "select * from Venda where idVenda  = ?";
            PreparedStatement stm = conectar.prepareStatement(sql);
            stm.setInt(1, id);
            resultado = stm.executeQuery();
            while(resultado.next()){
                venda_banco = new Venda(
                        resultado.getInt("idVenda"),
                        resultado.getInt("IdOrcamento"),
                        resultado.getDate("Data")
                        );

            }
            stm.close();
            conectar.close();
            return venda_banco;
            
        } catch (SQLException e){
            System.err.println("Problema detectado! " + e);
            return null;
        }
        
    }
}
