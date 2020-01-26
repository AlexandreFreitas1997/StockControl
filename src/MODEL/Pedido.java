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
public class Pedido {
    
    private int IdPedido;
    private Date Data;
    private float ValorTotal;
    private int IdStatus;
    private int IdCliente;

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int IdPedido) {
        this.IdPedido = IdPedido;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public float getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(float ValorTotal) {
        this.ValorTotal = ValorTotal;
    }

    public int getIdStatus() {
        return IdStatus;
    }

    public void setIdStatus(int IdStatus) {
        this.IdStatus = IdStatus;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public Pedido(Date Data, float ValorTotal, int IdStatus, int IdCliente) {
        this.Data = Data;
        this.ValorTotal = ValorTotal;
        this.IdStatus = IdStatus;
        this.IdCliente = IdCliente;
    }

    public Pedido(int IdPedido, Date Data, float ValorTotal, int IdStatus, int IdCliente) {
        this.IdPedido = IdPedido;
        this.Data = Data;
        this.ValorTotal = ValorTotal;
        this.IdStatus = IdStatus;
        this.IdCliente = IdCliente;
    }

    @Override
    public String toString() {
        return IdPedido + " --> " + Data + " - " + ValorTotal + " - " + IdStatus + " - " + IdCliente;
    }

    
    
}
