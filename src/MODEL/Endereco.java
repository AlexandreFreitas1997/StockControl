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
public class Endereco {

    private int idEnderco;
    private String logradouro;
    private String numero;
    private String complemento;
    private String referencia;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    

    public Endereco(String logradouro, String numero, String complemento, String referencia, String bairro, String cidade, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.referencia = referencia;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public Endereco(int idEnderco, String logradouro, String numero, String complemento, String referencia, String bairro, String cidade, String estado, String cep) {
        this.idEnderco = idEnderco;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.referencia = referencia;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public int getIdEnderco() {
        return idEnderco;
    }

    public void setIdEnderco(int idEnderco) {
        this.idEnderco = idEnderco;
    }
    
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    @Override
    public String toString() {
        return idEnderco + " --> " + logradouro + " - " + numero + " - " + complemento + " - " + referencia + " - " + bairro + " - " + cidade + " - " + estado.substring(3, 5) + " - " + cep ;
    }
    
}
