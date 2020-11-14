/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.FormCadastroView;

/**
 *
 * @author Leandro
 */
public class UsuarioDAO {
    
    private final Connection connection;
    Usuario usuario;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

 
    
    public Usuario insert(Usuario usuario) throws SQLException{
        
             //this.usuario = usuario;
            // TODO add your handling code here:
            
            String sql = "insert into usuario(usuarioNome,senha) values (?,?)";
            
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,usuario.getUsuario());
            statement.setString(2,usuario.getSenha());
            statement.execute();
            
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                 usuario.setId(id);
        
    }
            return usuario;
          
    }// final metodo
    
        public void update(Usuario usuario) throws SQLException{
        
        String sql = "update usuario set usuarioNome =?, senha =? where id=?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,usuario.getUsuario());
        statement.setString(2,usuario.getSenha());
        statement.setInt(3,usuario.getId());
        
        statement.execute();
        
    }
    
    public void insertOrUpdate(Usuario usuario) throws SQLException{
        
        if(usuario.getId()>0){
            update(usuario);
        }else{
            insert(usuario);
        }
        
        
    }
    
    public void delete(Usuario usuario) throws SQLException{
        
         String sql = "delete from usuario where id =?";
        
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1,usuario.getId());
        
        statement.execute();
        
    }
    
    public ArrayList<Usuario> selectAll() throws SQLException{
        
         String sql = "select * from usuario";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        
        
        return pesquisa(statement);
    }

    private ArrayList<Usuario> pesquisa(PreparedStatement statement) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        // enquanto houver linhas na tabela usuario
        while (resultSet.next()){
            int id = resultSet.getInt("id") ;
            String usuario = resultSet.getString("usuarioNome");
            String senha = resultSet.getString("senha");
            
            Usuario usuarioComDadosBanco = new Usuario(id,usuario,senha);
            //add usuarios no array list
            usuarios.add(usuarioComDadosBanco);
            
            
        } // fim while
        
        return usuarios;
    }
    
    
    public Usuario selectPorId(Usuario usuario) throws SQLException{
        
        String sql = "select * from usuario where id =?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setInt(1,usuario.getId());
        
       return pesquisa(statement).get(0);     
        
    
    }
    

    public boolean existeNoBancoPorUsuarioSenha(Usuario usuario) throws SQLException {
        
        String sql = "select * from usuario where usuarioNome = ? and senha = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,usuario.getUsuario());
        statement.setString(2,usuario.getSenha());
        
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        //se tiver proxima linha no banco, retorna true. Se não,retorna false
        //return resultSet.next();
       
       return resultSet.next();
    }


    
    

    
    
}
