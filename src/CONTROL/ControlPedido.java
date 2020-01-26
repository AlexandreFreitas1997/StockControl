/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import static CONTROL.Principal.formpedido;
import static CONTROL.Principal.inicio;
import DAO.ClienteDAO;
import DAO.ItensPedidoDAO;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import DAO.StatusDAO;
import MODEL.Cliente;
import MODEL.ItensPedido;
import MODEL.Pedido;
import MODEL.Status;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlPedido {
    
    public static boolean editando;
    public static int idAux;
    
    
    public static void limparCampos() {
        formpedido.cbxClientePedido.setSelectedIndex(0);
        formpedido.cbxStatusPedido.setSelectedItem(0);
    }

    public static void preencherCampos(int indexcbxcliente, int indexcbxstatus){
        formpedido.cbxClientePedido.setSelectedIndex(indexcbxcliente);
        formpedido.cbxStatusPedido.setSelectedItem(indexcbxstatus);
    }
    
    public static void cadastrar() {
        try {
            if(formpedido.cbxClientePedido.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formpedido, "Nenhum Cliente cadastrado! Você será redirecionado para cadastrar um novo Cliente primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormCliente();
            }
            if(formpedido.cbxStatusPedido.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formpedido, "Nenhum Status cadastrado! Você será redirecionado para cadastrar um novo Status primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormStatus();
            }
            else{
                Pedido pedido = null;
                String aux = formpedido.cbxClientePedido.getSelectedItem().toString();
                String[] aux2 = aux.split(" --> ");
                String aux3 = formpedido.cbxStatusPedido.getSelectedItem().toString();
                String[] aux4 = aux3.split(" --> ");
                int idCliente = Integer.parseInt(aux2[0]);
                int idStatus = Integer.parseInt(aux4[0]);
                
                float valortotal = 0;
                
                Date dataatual = new Date(System.currentTimeMillis());
                
                pedido = new Pedido(dataatual, valortotal, idStatus, idCliente);
                
                PedidoDAO.inserir(pedido);
                limparCampos();
                Principal.mostrarTelaInicio();
                atualizar();
                
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Erro ao inserir os dados! Verifique os dados digitados!");
        }
        catch (Exception e) {
            System.err.println("Problema detectado! control" + e);
        }
    }
    
    public static void atualizarValorTotal(int id){
        float valortotal = 0;
        ArrayList<ItensPedido> itenspedido = ItensPedidoDAO.listar();
        for(ItensPedido ip : itenspedido){
            float precoproduto;
            int quantidade;
            int idProduto;
            int idPedido;
            String[] auxip = ip.toString().split(" --> ");
            String[] auxip1 = auxip[1].split(" - ");
            quantidade = Integer.parseInt(auxip1[2]);
            idProduto = Integer.parseInt(auxip1[0]);
            idPedido = Integer.parseInt(auxip1[1]);
            precoproduto = ProdutoDAO.pesquisar1(idProduto).getPreco();
            valortotal = valortotal + (precoproduto * quantidade);
        }
        
        PedidoDAO.editarValorTotal(id, valortotal);
        atualizar();
    }

    public static void editar(int idAux) {
        String aux = formpedido.cbxClientePedido.getSelectedItem().toString();
        String[] aux2 = aux.split(" --> ");
        String aux3 = formpedido.cbxStatusPedido.getSelectedItem().toString();
        String[] aux4 = aux3.split(" --> ");
        int idCliente = Integer.parseInt(aux2[0]);
        int idStatus = Integer.parseInt(aux4[0]);

        float valortotal = 0;

        Date dataatual = PedidoDAO.pesquisar1(idAux).getData();

        Pedido pedido = new Pedido(dataatual, valortotal, idStatus, idCliente);

        PedidoDAO.editar(idAux, pedido);
        limparCampos();
        Principal.mostrarTelaInicio();
        atualizar();
        atualizarValorTotal(idAux);
    }

    static void popularcbxCliente() {
        formpedido.cbxClientePedido.removeAllItems();
        ArrayList<Cliente> cliente = ClienteDAO.listar();
        for(Cliente c : cliente)
            formpedido.cbxClientePedido.addItem(c.toString());
    }

    static void popularcbxStatus() {
        formpedido.cbxStatusPedido.removeAllItems();
        ArrayList<Status> status = StatusDAO.listar();
        for(Status s : status)
            formpedido.cbxStatusPedido.addItem(s.toString());
    }

    static void camposPedido(boolean b) {
        formpedido.cbxClientePedido.setEnabled(b);
        formpedido.cbxStatusPedido.setEnabled(b);
        editando = !b;
    }

    static void atualizar() {
        ArrayList<Pedido> pedido = PedidoDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaPedido.getModel();
        modelo.setRowCount(0);
        for(Pedido p : pedido)
            modelo.addRow(new Object[]{p.getIdPedido(), p.getData(), p.getValorTotal(), p.getIdCliente(), p.getIdStatus()});
    }

    public static void excluir(int idSelecionado) {
        PedidoDAO.excluir(idSelecionado);
        atualizar();
    }

    public static void vizualizar(String txt) {
        camposPedido(false);
        int idSelecionado = Integer.parseInt(txt);
        
        Pedido pedido = PedidoDAO.pesquisar1(idSelecionado);
        String[] aux = pedido.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxcliente = 0;
        int indexcbxstatus = 0;
        
        ArrayList<Cliente> cliente = ClienteDAO.listar();
        for(Cliente c : cliente){
            String[] aux2 = c.toString().split(" --> ");
            if(aux2[0].equals(aux1[3]))
                break;
            else
                indexcbxcliente = indexcbxcliente + 1;
        }
        
        ArrayList<Status> status = StatusDAO.listar();
        for(Status s : status){
            String[] aux2 = s.toString().split(" --> ");
            if(aux2[0].equals(aux1[2]))
                break;
            else
                indexcbxstatus = indexcbxstatus + 1;
        }
        
        Principal.mostrarTelaFormPedido();
        preencherCampos(indexcbxcliente, indexcbxstatus);
        Principal.formpedido.btnCancelarPedido.setVisible(false);
        Principal.formpedido.btnConfirmarPedido.setVisible(false);
        Principal.formpedido.btnSairPedido.setVisible(true);

    }

    public static void capturarEditar(int idSelecionado) {
        Pedido pedido = PedidoDAO.pesquisar1(idSelecionado);
        String[] aux = pedido.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxcliente = 0;
        int indexcbxstatus = 0;
        
        ArrayList<Cliente> cliente = ClienteDAO.listar();
        for(Cliente c : cliente){
            String[] aux2 = c.toString().split(" --> ");
            if(aux2[0].equals(aux1[3]))
                break;
            else
                indexcbxcliente = indexcbxcliente + 1;
        }
        
        ArrayList<Status> status = StatusDAO.listar();
        for(Status s : status){
            String[] aux2 = s.toString().split(" --> ");
            if(aux2[0].equals(aux1[2]))
                break;
            else
                indexcbxstatus = indexcbxstatus + 1;
        }
        
        Principal.mostrarTelaFormPedido();
        preencherCampos(indexcbxcliente, indexcbxstatus);
        editando = true;
        idAux = idSelecionado;
    }
    
}
