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
public class PartesProduto {
    
    private int IdPartesProduto;
    private int IdPartes;
    private int IdProduto;

    public int getIdPartesProduto() {
        return IdPartesProduto;
    }

    public void setIdPartesProduto(int IdPartesProduto) {
        this.IdPartesProduto = IdPartesProduto;
    }

    public int getIdPartes() {
        return IdPartes;
    }

    public void setIdPartes(int IdPartes) {
        this.IdPartes = IdPartes;
    }

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
    }

    public PartesProduto(int IdPartes, int IdProduto) {
        this.IdPartes = IdPartes;
        this.IdProduto = IdProduto;
    }

    public PartesProduto(int IdPartesProduto, int IdPartes, int IdProduto) {
        this.IdPartesProduto = IdPartesProduto;
        this.IdPartes = IdPartes;
        this.IdProduto = IdProduto;
    }

    @Override
    public String toString() {
        return IdPartesProduto + " --> " + IdPartes + " - " + IdProduto;
    }
    
}
