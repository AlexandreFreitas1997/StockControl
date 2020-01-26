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
public class Tipo {
    
    private int IdTipo;
    private String Nome;

    public int getIdTipo() {
        return IdTipo;
    }

    public void setIdTipo(int IdTipo) {
        this.IdTipo = IdTipo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Tipo(String Nome) {
        this.Nome = Nome;
    }

    public Tipo(int IdTipo, String Nome) {
        this.IdTipo = IdTipo;
        this.Nome = Nome;
    }

    @Override
    public String toString() {
        return IdTipo + " --> " + Nome;
    }
    
    
    
}
