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
public class Partes {
    
    private int IdPartes;
    private String Nome;
    private float Preco;

    public int getIdPartes() {
        return IdPartes;
    }

    public void setIdPartes(int IdPartes) {
        this.IdPartes = IdPartes;
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

    public Partes(String Nome, float Preco) {
        this.Nome = Nome;
        this.Preco = Preco;
    }

    public Partes(int IdPartes, String Nome, float Preco) {
        this.IdPartes = IdPartes;
        this.Nome = Nome;
        this.Preco = Preco;
    }

    @Override
    public String toString() {
        return IdPartes + " --> " + Nome + " - " + Preco;
    }
    
}
