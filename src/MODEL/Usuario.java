/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author Alexandre Freitas
 */
public class Usuario {
    
    private int idUsuario;
    private String login;
    private String senha;
    private String tipoUsuario;

    public Usuario(String login, String senha, String tipoUsuario) {
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(int idUsuario, String login, String senha, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }
    
    
    

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return idUsuario + " --> " + login + " - " + tipoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
