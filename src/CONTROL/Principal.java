/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CONTROL;

import DAO.UsuarioDAO;
import GUI.FormCliente;
import GUI.FormDadosPessoais;
import GUI.FormEndereco;
import GUI.FormEstoque;
import GUI.FormFornecedor;
import GUI.FormFuncionario;
import GUI.FormItensPedido;
import GUI.FormLogin;
import GUI.FormOrcamento;
import GUI.FormPartes;
import GUI.FormPartesProduto;
import GUI.FormPedido;
import GUI.FormProduto;
import GUI.FormProdutosEstoque;
import GUI.FormServicoFornecedor;
import GUI.FormStatus;
import GUI.FormTipo;
import GUI.FormUsuario;
import GUI.FormVenda;
import GUI.Inicio;
import MODEL.Usuario;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.util.ArrayList;

/**
 *
 * @author 20161stads0039
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        mostrarTelaLogin();        
        
        ControlEndereco.atualizar();
        ControlDados.atualizar();
        ControlCliente.atualizar();
        ControlFornecedor.atualizar();
        ControlServico.atualizar();
        ControlEstoque.atualizar();
        ControlTipo.atualizar();
        ControlPartes.atualizar();
        ControlStatus.atualizar();
        ControlProduto.atualizar();
        ControlPartesProduto.atualizar();
        ControlPedido.atualizar();
        ControlOrcamento.atualizar();
        ControlVenda.atualizar();
        ControlItensPedido.atualizar();
        ControlProdutoEstoque.atualizar();
        ControlUsuario.atualizar();
        ControlFuncionario.atualizar();
    }
    
    public static FormLogin login = new FormLogin();
    public static Inicio inicio = new Inicio();
    public static FormCliente formcliente = new FormCliente();
    public static FormEndereco formendereco = new FormEndereco();
    public static FormFornecedor formfornecedor = new FormFornecedor();
    public static FormServicoFornecedor formservicofornecedor = new FormServicoFornecedor();
    public static FormUsuario formusuario = new FormUsuario();
    public static FormDadosPessoais formdadospessoais = new FormDadosPessoais();
    public static FormFuncionario formfuncionario = new FormFuncionario();
    public static FormEstoque formestoque = new FormEstoque();
    public static FormTipo formtipo = new FormTipo();
    public static FormPartes formpartes = new FormPartes();
    public static FormStatus formstatus = new FormStatus();
    public static FormProduto formproduto = new FormProduto();
    public static FormPartesProduto formpartesProduto = new FormPartesProduto();
    public static FormPedido formpedido = new FormPedido();
    public static FormOrcamento formorcamento = new FormOrcamento();
    public static FormVenda formvenda = new FormVenda();
    public static FormItensPedido formitensPedido = new FormItensPedido();
    public static FormProdutosEstoque formprodutosEstoque = new FormProdutosEstoque();
    
    
    
    
    public static void mostrarTelaLogin(){
        boolean status = false;
        login.setVisible(true);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        ControlLogin.limparCampos();
    }
    
    
    
    public static void mostrarTelaInicio(){
        
        inicio.setExtendedState(MAXIMIZED_BOTH);
        
        login.setVisible(false);
        inicio.setVisible(true);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        ControlCliente.camposCliente(true);
        ControlEndereco.camposEndereco(true);
        ControlFornecedor.camposFornecedor(true);
        ControlServico.camposServico(true);
        ControlUsuario.camposUsuario(true);
        ControlDados.camposDados(true);
        ControlFuncionario.camposFuncionario(true);
        ControlEstoque.camposEstoque(true);
        ControlTipo.camposTipo(true);
        ControlPartes.camposPartes(true);
        ControlStatus.camposStatus(true);
        ControlProduto.camposProduto(true);
        ControlPartesProduto.camposPartesProduto(true);
        ControlPedido.camposPedido(true);
        ControlOrcamento.camposOrcamento(true);
        ControlVenda.camposVenda(true);
        ControlItensPedido.camposItensPedido(true);
        ControlProdutoEstoque.camposProdutosEstoque(true);

    }    

    public static void mostrarTelaFormCliente(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(true);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formcliente.btnCancelarCliente.setVisible(true);
        formcliente.btnConfirmarCliente.setVisible(true);
        formcliente.btnSairCliente.setVisible(false);
        
        ControlCliente.popularcbxDados();
        
    }
    
    public static void mostrarTelaFormEndereco(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(true);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formendereco.btnConfirmarEndereco.setVisible(true);
        formendereco.btnCancelarEndereco.setVisible(true);
        formendereco.btnSairEndereco.setVisible(false);
        
    }
    
    public static void mostrarTelaFormUsuario() {
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(true);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formusuario.btnCancelarUsuario.setVisible(true);
        formusuario.btnConfirmarUsuario.setVisible(true);
        formusuario.btnSairUsuario.setVisible(false);  
    
    }

    public static void mostrarTelaFormDadosPessoais(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(true);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formdadospessoais.btnCancelarDados.setVisible(true);
        formdadospessoais.btnConfirmarDados.setVisible(true);
        formdadospessoais.btnSairDados.setVisible(false);
        
        ControlDados.popularcbxEndereco();
    }

    public static void mostrarTelaFormFornecedor(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(true);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formfornecedor.btnCancelarFornecedor.setVisible(true);
        formfornecedor.btnConfirmarFornecedor.setVisible(true);
        formfornecedor.btnSairFornecedor.setVisible(false);
        
        ControlFornecedor.popularcbxDados();
    }
    
    public static void mostrarTelaFormServicoFornecedor(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(true);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formservicofornecedor.btnCancelarServico.setVisible(true);
        formservicofornecedor.btnConfirmarServico.setVisible(true);
        formservicofornecedor.btnSairServico.setVisible(false);
        
        ControlServico.popularcbxFornecedor();
    }
    
    public static void mostrarTelaFormFuncionario(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(true);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formfuncionario.btnCancelarFuncionario.setVisible(true);
        formfuncionario.btnConfirmarFuncionario.setVisible(true);
        formfuncionario.btnSairFuncionario.setVisible(false);  
        
        ControlFuncionario.popularcbxDados();
        ControlFuncionario.popularcbxUsuario();
    }
    
    public static void mostrarTelaFormEstoque(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(true);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formestoque.btnCancelarEstoque.setVisible(true);
        formestoque.btnConfirmarEstoque.setVisible(true);
        formestoque.btnSairEstoque.setVisible(false);
        
        ControlEstoque.popularcbxEndereco();
    }
    
    public static void mostrarTelaFormTipo(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(true);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formtipo.btnCancelarTipo.setVisible(true);
        formtipo.btnConfirmarTipo.setVisible(true);
        formtipo.btnSairTipo.setVisible(false);        
    }
    
    public static void mostrarTelaFormPartes(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(true);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formpartes.btnCancelarPartes.setVisible(true);
        formpartes.btnConfirmarPartes.setVisible(true);
        formpartes.btnSairPartes.setVisible(false);        
    }
    
    public static void mostrarTelaFormStatus(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(true);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formstatus.btnCancelarStatus.setVisible(true);
        formstatus.btnConfirmarStatus.setVisible(true);
        formstatus.btnSairStatus.setVisible(false);        
    }
    
    public static void mostrarTelaFormProduto(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(true);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formproduto.btnCancelarProduto.setVisible(true);
        formproduto.btnConfirmarProduto.setVisible(true);
        formproduto.btnSairProduto.setVisible(false);  
        
        ControlProduto.popularcbxTipo();
    }
    
    public static void mostrarTelaFormPartesProduto(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(true);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formpartesProduto.btnCancelarPartesProduto.setVisible(true);
        formpartesProduto.btnConfirmarPartesProduto.setVisible(true);
        formpartesProduto.btnSairPartesProduto.setVisible(false);  
        
        ControlPartesProduto.popularcbxPartes();
        ControlPartesProduto.popularcbxProduto();
    }
    
    public static void mostrarTelaFormPedido(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(true);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formpedido.btnCancelarPedido.setVisible(true);
        formpedido.btnConfirmarPedido.setVisible(true);
        formpedido.btnSairPedido.setVisible(false);  
        
        ControlPedido.popularcbxCliente();
        ControlPedido.popularcbxStatus();
    }
    
    public static void mostrarTelaFormOrcamento(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(true);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formorcamento.btnCancelarOrcamento.setVisible(true);
        formorcamento.btnConfirmarOrcamento.setVisible(true);
        formorcamento.btnSairOrcamento.setVisible(false);  
        
        ControlOrcamento.popularcbxPedido();
    }
    
    public static void mostrarTelaFormVenda(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(true);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(false);
        
        formvenda.btnCancelarVenda.setVisible(true);
        formvenda.btnConfirmarVenda.setVisible(true);
        formvenda.btnSairVenda.setVisible(false);  
        
        ControlVenda.popularcbxOrcamento();
    }
    
    public static void mostrarTelaFormItensPedido(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(true);
        formprodutosEstoque.setVisible(false);
        
        formitensPedido.btnCancelarItensPedido.setVisible(true);
        formitensPedido.btnConfirmarItensPedido.setVisible(true);
        formitensPedido.btnSairItensPedido.setVisible(false);  
        
        ControlItensPedido.popularcbxProduto();
        ControlItensPedido.popularcbxPedido();
    }
    
    public static void mostrarTelaFormProdutosEstoque(){
        login.setVisible(false);
        inicio.setVisible(false);
        formcliente.setVisible(false);
        formendereco.setVisible(false);
        formfornecedor.setVisible(false);
        formservicofornecedor.setVisible(false);
        formusuario.setVisible(false);
        formdadospessoais.setVisible(false);
        formfuncionario.setVisible(false);
        formestoque.setVisible(false);
        formtipo.setVisible(false);
        formpartes.setVisible(false);
        formstatus.setVisible(false);
        formproduto.setVisible(false);
        formpartesProduto.setVisible(false);
        formpedido.setVisible(false);
        formorcamento.setVisible(false);
        formvenda.setVisible(false);
        formitensPedido.setVisible(false);
        formprodutosEstoque.setVisible(true);
        
        formprodutosEstoque.btnCancelarProdutosEstoque.setVisible(true);
        formprodutosEstoque.btnConfirmarProdutosEstoque.setVisible(true);
        formprodutosEstoque.btnSairProdutosEstoque.setVisible(false);  
        
        ControlProdutoEstoque.popularcbxProduto();
        ControlProdutoEstoque.popularcbxEstoque();
    }
}
