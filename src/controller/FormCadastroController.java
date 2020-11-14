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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.FormCadastroView;

/**
 *
 * @author Leandro
 */
public class FormCadastroController {
    
    private FormCadastroView view;

    public FormCadastroController(FormCadastroView view) {
        this.view = view;
    }
    
    public void salvaUsuario(){
        
        
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();
        
        
     Usuario usuarioTiago = new Usuario(usuario,senha);
     
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.insert(usuarioTiago);
            //mensagem de que deu certo!
            // pop up
            JOptionPane.showMessageDialog(null, "Usuario salvo com sucesso!");
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FormCadastroView.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
        
        
    }
    
    
    
}
