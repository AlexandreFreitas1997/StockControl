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
public class Cliente {
    
    private int idCliente;
    private int idDadosPessoais;
    private String docIdentificacao;

    public Cliente(int idDadosPessoais, String docIdentificacao) {
        this.idDadosPessoais = idDadosPessoais;
        this.docIdentificacao = docIdentificacao;
    }

    public Cliente(int idCliente, int idDadosPessoais, String docIdentificacao) {
        this.idCliente = idCliente;
        this.idDadosPessoais = idDadosPessoais;
        this.docIdentificacao = docIdentificacao;
    }

    public String getDocIdentificacao() {
        return docIdentificacao;
    }

    public void setDocIdentificacao(String docIdentificacao) {
        this.docIdentificacao = docIdentificacao;
    }

    public int getIdDadosPessoais() {
        return idDadosPessoais;
    }

    public void setIdDadosPessoais(int idDadosPessoais) {
        this.idDadosPessoais = idDadosPessoais;
    }

    @Override
    public String toString() {
        return idCliente + " --> " + idDadosPessoais + " / " + docIdentificacao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}
