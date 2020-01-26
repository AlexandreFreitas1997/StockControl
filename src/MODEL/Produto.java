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
public class Produto {
    
    private int IdProduto;
    private String Nome;
    private float Preco;
    private String Genero;
    private boolean Customizavel;
    private int IdTipo;

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public float getPreco() {
        return Preco;
    }

    public void setPreco(float Preco) {
        this.Preco = Preco;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public boolean isCustomizavel() {
        return Customizavel;
    }

    public void setCustomizavel(boolean Customizavel) {
        this.Customizavel = Customizavel;
    }

    public int getIdTipo() {
        return IdTipo;
    }

    public void setIdTipo(int IdTipo) {
        this.IdTipo = IdTipo;
    }

    public Produto(String Nome, float Preco, String Genero, boolean Customizavel, int IdTipo) {
        this.Nome = Nome;
        this.Preco = Preco;
        this.Genero = Genero;
        this.Customizavel = Customizavel;
        this.IdTipo = IdTipo;
    }

    public Produto(int IdProduto, String Nome, float Preco, String Genero, boolean Customizavel, int IdTipo) {
        this.IdProduto = IdProduto;
        this.Nome = Nome;
        this.Preco = Preco;
        this.Genero = Genero;
        this.Customizavel = Customizavel;
        this.IdTipo = IdTipo;
    }

    @Override
    public String toString() {
        return IdProduto + " --> " + Nome + " - " + Preco + " - " + Genero + " - " + Customizavel + " - " + IdTipo;
    }

    
    
    
}
