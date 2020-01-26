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
public class Orcamento {
    
    private int IdOrcamento;
    private int IdPedido;
    private Date Data;

    public int getIdOrcamento() {
        return IdOrcamento;
    }

    public void setIdOrcamento(int IdOrcamento) {
        this.IdOrcamento = IdOrcamento;
    }

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int IdPedido) {
        this.IdPedido = IdPedido;
    }

    public Orcamento(int IdPedido, Date Data) {
        this.IdPedido = IdPedido;
        this.Data = Data;
    }

    public Orcamento(int IdOrcamento, int IdPedido, Date Data) {
        this.IdOrcamento = IdOrcamento;
        this.IdPedido = IdPedido;
        this.Data = Data;
    }

    @Override
    public String toString() {
        return IdOrcamento + " --> " + IdPedido + " - " + Data;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }
    
    
    
}
