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
 * @author Leandro
 * 
 * só existe para fazer a conexão com o banco
 */
public class Conexao {
    
    public Connection getConnection() throws SQLException{
        
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste","root","");
        
        return conexao;
        
        
    }
    
    
}
