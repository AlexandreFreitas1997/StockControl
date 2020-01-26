/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alexandre Freitas
 */
public class ConexaoBanco {
    
    private static final String URL = "jdbc:mysql://localhost/stockcontrol?useSSL=false";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
   
    public static Connection getConnection(){
        try {
                return DriverManager.getConnection(URL, USUARIO, SENHA);
            } catch (SQLException ex) {
                System.err.println("CONEXAO!" + ex);
        }
         return null;
    }
}
