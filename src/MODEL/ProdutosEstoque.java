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
public class ProdutosEstoque {
    
    private int IdProdutosEstoque;
    private int IdEstoque;
    private int IdProduto;
    private int Quantidade;

    public int getIdProdutosEstoque() {
        return IdProdutosEstoque;
    }

    public void setIdProdutosEstoque(int IdProdutosEstoque) {
        this.IdProdutosEstoque = IdProdutosEstoque;
    }

    public int getIdEstoque() {
        return IdEstoque;
    }

    public void setIdEstoque(int IdEstoque) {
        this.IdEstoque = IdEstoque;
    }

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public ProdutosEstoque(int IdEstoque, int IdProduto, int Quantidade) {
        this.IdEstoque = IdEstoque;
        this.IdProduto = IdProduto;
        this.Quantidade = Quantidade;
    }

    public ProdutosEstoque(int IdProdutosEstoque, int IdEstoque, int IdProduto, int Quantidade) {
        this.IdProdutosEstoque = IdProdutosEstoque;
        this.IdEstoque = IdEstoque;
        this.IdProduto = IdProduto;
        this.Quantidade = Quantidade;
    }

    @Override
    public String toString() {
        return IdProdutosEstoque + " --> " + IdEstoque + " - " + IdProduto + " - " + Quantidade;
    }
    
}
