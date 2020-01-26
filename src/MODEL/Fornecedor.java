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
public class Fornecedor {
    
    private int idFornecedor;
    private int idDadosPessoais;

    public Fornecedor(int idFornecedor, int idDadosPessoais) {
        this.idFornecedor = idFornecedor;
        this.idDadosPessoais = idDadosPessoais;
    }

    public Fornecedor(int idDadosPessoais) {
        this.idDadosPessoais = idDadosPessoais;
    }

    public int getIdDadosPessoais() {
        return idDadosPessoais;
    }

    public void setIdDadosPessoais(int idDadosPessoais) {
        this.idDadosPessoais = idDadosPessoais;
    }

    @Override
    public String toString() {
        return idFornecedor + " --> " + idDadosPessoais;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
    
}
