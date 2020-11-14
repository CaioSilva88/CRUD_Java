/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.Conexao;
import DAO.UsuarioDAO;
import Model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.LoginView;
import view.MenuView;

/**
 *
 * @author Leandro
 */
public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void autenticar() throws SQLException  {
       
        // autentica usuario e senha no banco de dados
       
        // buscar usuario da view
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();
        
        Usuario usuarioAutenticar = new Usuario(usuario,senha);
        
        // verificar se há no banco de dados
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
                
        boolean existe = usuarioDao.existeNoBancoPorUsuarioSenha(usuarioAutenticar);
        
        //se existir, direciona ao menu
        
        if(existe){
            
        MenuView telaMenu = new MenuView();
        telaMenu.setVisible(true);
            
        }else{
            JOptionPane.showMessageDialog(view, "Usuário ou senha inválidos");
        }
        
    }
    
    
    
    
    
    
}
