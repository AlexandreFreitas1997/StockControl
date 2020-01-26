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
public class DadosPessoais {
    private int id;
    private String nome;
    private String email;
    private String fixo;
    private String celular;
    private int idEndereco;

    public DadosPessoais(String nome, String email, String fixo, String celular, int idEndereco) {
        this.nome = nome;
        this.email = email;
        this.fixo = fixo;
        this.celular = celular;
        this.idEndereco = idEndereco;
    }

    public DadosPessoais(int id, String nome, String email, String fixo, String celular, int idEndereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fixo = fixo;
        this.celular = celular;
        this.idEndereco = idEndereco;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFixo() {
        return fixo;
    }

    public void setFixo(String fixo) {
        this.fixo = fixo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return id + " --> " + nome + " - " + email + " - " + fixo + " - " + celular + " / " + idEndereco;
    }


}
