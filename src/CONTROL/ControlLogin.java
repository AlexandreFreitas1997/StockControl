package CONTROL;

import static CONTROL.Principal.login;
import DAO.UsuarioDAO;
import MODEL.Usuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 20161stads0039
 */
public class ControlLogin {
   
    public static String verificarUsuario(){
        String txtlogin = login.txtLogin.getText();
        String txtsenha = ControlUsuario.hashSenha(new String(login.txtSenha.getPassword()));
        boolean status = false;
        String tipo = "";        
        ArrayList<Usuario> usuarios = UsuarioDAO.listar();
        for(Usuario u : usuarios){
            if(u.getLogin().equals(txtlogin) && u.getSenha().equals(txtsenha)){
                status = true;
                tipo = u.getTipoUsuario();
                break;
            }
        }
        if(status == true){
            JOptionPane.showMessageDialog(login, "Usuario autenticado com sucesso!");
        }
        else{
            JOptionPane.showMessageDialog(login, "Login ou Senha não coencidem com o cadastrado! Por Favor tente novamente!");
            limparCampos();
        }
        return tipo;
    }
            
    public static void gerenciarVisoes(String tipo){
        if(tipo.equals("Funcionário")){
            Principal.inicio.SubMenuCliente.setVisible(true);
            Principal.inicio.SubMenuFornecedor.setVisible(false);
            Principal.inicio.SubMenuProduto.setVisible(false);
            Principal.inicio.Separador1.setVisible(false);
            Principal.inicio.SubMenuPedido.setVisible(true);
            Principal.inicio.ItemMenuOrcamento.setVisible(false);
            Principal.inicio.ItemMenuVenda.setVisible(false);
            Principal.inicio.Separador2.setVisible(false);
            Principal.inicio.SubMenuEstoque.setVisible(false);
            Principal.inicio.Separador3.setVisible(false);
            Principal.inicio.SubMenuFuncionario.setVisible(false);
            Principal.inicio.Separador4.setVisible(false);
            Principal.inicio.ItemMenuUsuario.setVisible(false);
            
            Principal.inicio.PainelDeAbas.setEnabledAt(0, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(1, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(2, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(3, false);
            Principal.inicio.PainelDeAbas.setEnabledAt(4, false);
            Principal.inicio.PainelDeAbas.setEnabledAt(5, true);
            Principal.inicio.btnEditarProduto.setEnabled(false);
            Principal.inicio.btnExcluirProduto.setEnabled(false);
            Principal.inicio.PainelDeAbas.setEnabledAt(6, false);
            Principal.inicio.PainelDeAbas.setEnabledAt(7, false);
            Principal.inicio.PainelDeAbas.setEnabledAt(8, false);
            Principal.inicio.PainelDeAbas.setEnabledAt(9, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(10, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(11, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(12, false);
            Principal.inicio.PainelDeAbas.setEnabledAt(13, false);
            Principal.inicio.PainelDeAbas.setEnabledAt(14, false);
            Principal.inicio.PainelDeAbas.setEnabledAt(15, true);
            Principal.inicio.btnEditarProdutosEstoque.setEnabled(false);
            Principal.inicio.btnExcluirProdutosEstoque.setEnabled(false);
            Principal.inicio.PainelDeAbas.setEnabledAt(16, false);
            Principal.inicio.PainelDeAbas.setEnabledAt(17, false);
            
            Principal.mostrarTelaInicio();
            
        }
        if(tipo.equals("Gerente")){
            Principal.inicio.SubMenuCliente.setVisible(true);
            Principal.inicio.SubMenuFornecedor.setVisible(true);
            Principal.inicio.SubMenuProduto.setVisible(true);
            Principal.inicio.Separador1.setVisible(true);
            Principal.inicio.SubMenuPedido.setVisible(true);
            Principal.inicio.ItemMenuOrcamento.setVisible(true);
            Principal.inicio.ItemMenuVenda.setVisible(true);
            Principal.inicio.Separador2.setVisible(true);
            Principal.inicio.SubMenuEstoque.setVisible(true);
            Principal.inicio.Separador3.setVisible(true);
            Principal.inicio.SubMenuFuncionario.setVisible(true);
            Principal.inicio.Separador4.setVisible(false);
            Principal.inicio.ItemMenuUsuario.setVisible(false);
            
            Principal.inicio.PainelDeAbas.setEnabledAt(0, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(1, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(2, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(3, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(4, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(5, true);
            Principal.inicio.btnEditarProduto.setEnabled(true);
            Principal.inicio.btnExcluirProduto.setEnabled(true);
            Principal.inicio.PainelDeAbas.setEnabledAt(6, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(7, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(8, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(9, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(10, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(11, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(12, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(13, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(14, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(15, true);
            Principal.inicio.btnEditarProdutosEstoque.setEnabled(true);
            Principal.inicio.btnExcluirProdutosEstoque.setEnabled(true);
            Principal.inicio.PainelDeAbas.setEnabledAt(16, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(17, true);
            Principal.inicio.btnEditarUsuario.setEnabled(false);
            Principal.inicio.btnExcluirUsuario.setEnabled(false);
            
            Principal.mostrarTelaInicio();
        }
        if(tipo.equals("Administrador")){
            Principal.inicio.SubMenuCliente.setVisible(true);
            Principal.inicio.SubMenuFornecedor.setVisible(true);
            Principal.inicio.SubMenuProduto.setVisible(true);
            Principal.inicio.Separador1.setVisible(true);
            Principal.inicio.SubMenuPedido.setVisible(true);
            Principal.inicio.ItemMenuOrcamento.setVisible(true);
            Principal.inicio.ItemMenuVenda.setVisible(true);
            Principal.inicio.Separador2.setVisible(true);
            Principal.inicio.SubMenuEstoque.setVisible(true);
            Principal.inicio.Separador3.setVisible(true);
            Principal.inicio.SubMenuFuncionario.setVisible(true);
            Principal.inicio.Separador4.setVisible(true);
            Principal.inicio.ItemMenuUsuario.setVisible(true);
            
            Principal.inicio.PainelDeAbas.setEnabledAt(0, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(1, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(2, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(3, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(4, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(5, true);
            Principal.inicio.btnEditarProduto.setEnabled(true);
            Principal.inicio.btnExcluirProduto.setEnabled(true);
            Principal.inicio.PainelDeAbas.setEnabledAt(6, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(7, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(8, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(9, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(10, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(11, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(12, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(13, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(14, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(15, true);
            Principal.inicio.btnEditarProdutosEstoque.setEnabled(true);
            Principal.inicio.btnExcluirProdutosEstoque.setEnabled(true);
            Principal.inicio.PainelDeAbas.setEnabledAt(16, true);
            Principal.inicio.PainelDeAbas.setEnabledAt(17, true);
            Principal.inicio.btnEditarUsuario.setEnabled(true);
            Principal.inicio.btnExcluirUsuario.setEnabled(true);
            
            Principal.mostrarTelaInicio();
        }
    }    

    public static void limparCampos(){
        Principal.login.txtLogin.setText("");
        Principal.login.txtSenha.setText("");
    }
}
