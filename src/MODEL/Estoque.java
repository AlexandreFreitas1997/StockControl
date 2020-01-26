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
public class Estoque {
    
    private int IdEstoque;
    private int IdEndereco;

    public int getIdEstoque() {
        return IdEstoque;
    }

    public void setIdEstoque(int IdEstoque) {
        this.IdEstoque = IdEstoque;
    }

    public int getIdEndereco() {
        return IdEndereco;
    }

    public void setIdEndereco(int IdEndereco) {
        this.IdEndereco = IdEndereco;
    }

    public Estoque(int IdEndereco) {
        this.IdEndereco = IdEndereco;
    }

    public Estoque(int IdEstoque, int IdEndereco) {
        this.IdEstoque = IdEstoque;
        this.IdEndereco = IdEndereco;
    }

    @Override
    public String toString() {
        return IdEstoque + " --> " + IdEndereco;
    }
    
}
