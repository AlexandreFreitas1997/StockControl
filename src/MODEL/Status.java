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
public class Status {
 
    private int IdStatus;
    private String Nome;

    public int getIdStatus() {
        return IdStatus;
    }

    public void setIdStatus(int IdStatus) {
        this.IdStatus = IdStatus;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Status(String Nome) {
        this.Nome = Nome;
    }

    public Status(int IdStatus, String Nome) {
        this.IdStatus = IdStatus;
        this.Nome = Nome;
    }

    @Override
    public String toString() {
        return IdStatus + " --> " + Nome;
    }
    
    
    
}
