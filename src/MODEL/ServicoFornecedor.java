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
public class ServicoFornecedor {
    
    private int idServicoFornecedor;
    private int idServico;
    private int idFornecedor;
    private float valor;

    public ServicoFornecedor(int idServico, int idFornecedor, float valor) {
        this.idServico = idServico;
        this.idFornecedor = idFornecedor;
        this.valor = valor;
    }

    public ServicoFornecedor(int idServicoFornecedor, int idServico, int idFornecedor, float valor) {
        this.idServicoFornecedor = idServicoFornecedor;
        this.idServico = idServico;
        this.idFornecedor = idFornecedor;
        this.valor = valor;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    @Override
    public String toString() {
        return idServicoFornecedor + " --> " + idServico + " - " + idFornecedor + " - " + valor;
    }

    public int getIdServicoFornecedor() {
        return idServicoFornecedor;
    }

    public void setIdServicoFornecedor(int idServicoFornecedor) {
        this.idServicoFornecedor = idServicoFornecedor;
    }
    
}
