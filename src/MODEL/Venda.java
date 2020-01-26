/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.sql.Date;

/**
 *
 * @author Alexandre Freitas
 */
public class Venda {
    
    private int IdVenda;
    private int IdOrcamento;
    private Date Data;

    public int getIdVenda() {
        return IdVenda;
    }

    public void setIdVenda(int IdVenda) {
        this.IdVenda = IdVenda;
    }

    public int getIdOrcamento() {
        return IdOrcamento;
    }

    public void setIdOrcamento(int IdOrcamento) {
        this.IdOrcamento = IdOrcamento;
    }

    public Venda(int IdOrcamento, Date Data) {
        this.IdOrcamento = IdOrcamento;
        this.Data = Data;
    }

    public Venda(int IdVenda, int IdOrcamento, Date Data) {
        this.IdVenda = IdVenda;
        this.IdOrcamento = IdOrcamento;
        this.Data = Data;
    }

    @Override
    public String toString() {
        return IdVenda + " --> " + IdOrcamento + " - " + Data;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }
    
}
