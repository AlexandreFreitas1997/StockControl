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
public class ItensPedido {
    
    private int IdItensPedido;
    private int IdProduto;
    private int IdPedido;
    private int Quantidade;

    public int getIdItensPedido() {
        return IdItensPedido;
    }

    public void setIdItensPedido(int IdItensPedido) {
        this.IdItensPedido = IdItensPedido;
    }

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
    }

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int IdPedido) {
        this.IdPedido = IdPedido;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public ItensPedido(int IdProduto, int IdPedido, int Quantidade) {
        this.IdProduto = IdProduto;
        this.IdPedido = IdPedido;
        this.Quantidade = Quantidade;
    }

    public ItensPedido(int IdItensPedido, int IdProduto, int IdPedido, int Quantidade) {
        this.IdItensPedido = IdItensPedido;
        this.IdProduto = IdProduto;
        this.IdPedido = IdPedido;
        this.Quantidade = Quantidade;
    }

    @Override
    public String toString() {
        return IdItensPedido + " --> " + IdProduto + " - " + IdPedido + " - " + Quantidade;
    }
    
    
}
