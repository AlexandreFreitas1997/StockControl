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
public class Funcionario {
    
    private int idFuncionario;

    public Funcionario(int idFuncionario, int idUsuario, int idDadosPessoais) {
        this.idFuncionario = idFuncionario;
        this.idUsuario = idUsuario;
        this.idDadosPessoais = idDadosPessoais;
    }
    private int idUsuario;
    private int idDadosPessoais;

    public Funcionario(int idUsuario, int idDadosPessoais) {
        this.idUsuario = idUsuario;
        this.idDadosPessoais = idDadosPessoais;
    }

    public int getIdDadosPessoais() {
        return idDadosPessoais;
    }

    public void setIdDadosPessoais(int idDadosPessoais) {
        this.idDadosPessoais = idDadosPessoais;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return idFuncionario + " --> " + idUsuario + " - " + idDadosPessoais;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

}
