package CONTROL;

import static CONTROL.Principal.inicio;
import DAO.DadosDAO;
import DAO.FuncionarioDAO;
import DAO.UsuarioDAO;
import MODEL.DadosPessoais;
import MODEL.Funcionario;
import MODEL.Usuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Freitas
 */
public class ControlFuncionario {
    public static int idAux;
    public static boolean editando = false;
    
    public static void popularcbxDados(){
        Principal.formfuncionario.cbxDadosPessoaisFuncionario.removeAllItems();
        ArrayList<DadosPessoais> dados = DadosDAO.listar();
        for(DadosPessoais d : dados)
            Principal.formfuncionario.cbxDadosPessoaisFuncionario.addItem(d.toString());
    }
    
    public static void popularcbxUsuario(){
        Principal.formfuncionario.cbxUsuarioFuncionario.removeAllItems();
        ArrayList<Usuario> usuarios = UsuarioDAO.listar();
        for(Usuario u : usuarios)
            Principal.formfuncionario.cbxUsuarioFuncionario.addItem(u.toString());
    }

    public static void atualizar() {
        ArrayList<Funcionario> funcionarios = FuncionarioDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel) inicio.TabelaFuncionario.getModel();
        modelo.setRowCount(0);
        for(Funcionario f : funcionarios)
            modelo.addRow(new Object[]{f.getIdFuncionario(), f.getIdUsuario(), f.getIdDadosPessoais()});
    }

    public static void excluir(int idSelecionado) {
        FuncionarioDAO.excluir(idSelecionado);
        atualizar();
    }

    public static void vizualizar(String txt) {
        camposFuncionario(false);
        int idSelecionado = Integer.parseInt(txt);
        
        Funcionario funcionario = FuncionarioDAO.pesquisar1(idSelecionado);
        String[] aux = funcionario.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxdado = 0;
        int indexcbxusuario = 0;
        
        ArrayList<DadosPessoais> dados = DadosDAO.listar();
        for(DadosPessoais d : dados){
            String[] aux2 = d.toString().split(" --> ");
            if(aux2[0].equals(aux1[1]))
                break;
            else
                indexcbxdado = indexcbxdado + 1;
        }
        
        
        ArrayList<Usuario> usuarios = UsuarioDAO.listar();
        for(Usuario u : usuarios){
            String[] aux2 = u.toString().split(" --> ");
            if(aux2[0].equals(aux1[0]))
                break;
            else
                indexcbxusuario = indexcbxusuario + 1;
        }
        
        Principal.mostrarTelaFormFuncionario();
        preencherCampos(indexcbxdado, indexcbxusuario);
        Principal.formfuncionario.btnCancelarFuncionario.setVisible(false);
        Principal.formfuncionario.btnConfirmarFuncionario.setVisible(false);
        Principal.formfuncionario.btnSairFuncionario.setVisible(true);
        
    }

    public static void capturarEditar(int idSelecionado) {
        Funcionario funcionario = FuncionarioDAO.pesquisar1(idSelecionado);
        String[] aux = funcionario.toString().split(" --> ");
        String[] aux1 = aux[1].split(" - ");
        
        int indexcbxdado = 0;
        int indexcbxusuario = 0;
        
        ArrayList<DadosPessoais> dados = DadosDAO.listar();
        for(DadosPessoais d : dados){
            String[] aux2 = d.toString().split(" --> ");
            if(aux2[0].equals(aux1[1]))
                break;
            else
                indexcbxdado = indexcbxdado + 1;
        }
        
        
        ArrayList<Usuario> usuarios = UsuarioDAO.listar();
        for(Usuario u : usuarios){
            String[] aux2 = u.toString().split(" --> ");
            if(aux2[0].equals(aux1[0]))
                break;
            else
                indexcbxusuario = indexcbxusuario + 1;
        }
        
        Principal.mostrarTelaFormFuncionario();
        preencherCampos(indexcbxdado, indexcbxusuario);
        editando = true;
        idAux = idSelecionado;
    }

    public static void cadastrar() {
        try {
            if(Principal.formfuncionario.cbxDadosPessoaisFuncionario.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formfuncionario, "Nenhum Dado Pessoal cadastrado! Você será redirecionado para cadastrar um novo Dado Pessoal primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaFormDadosPessoais();
            }
            if(Principal.formfuncionario.cbxUsuarioFuncionario.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(Principal.formfuncionario, "Nenhum Usuário cadastrado! Favor contatar um Administrador para cadastrar um novo Usuário primeiro e após isso repita a operação.", "Erro", 0);
                Principal.mostrarTelaInicio();
                limparCampos();
            }
            else{
                String aux = Principal.formfuncionario.cbxDadosPessoaisFuncionario.getSelectedItem().toString();
                String[] aux2 = aux.split(" --> ");
                String aux3 = Principal.formfuncionario.cbxUsuarioFuncionario.getSelectedItem().toString();
                String[] aux4 = aux3.split(" --> ");
                int idDados = Integer.parseInt(aux2[0]);
                int idUsuario = Integer.parseInt(aux4[0]);

                Funcionario funcionario = new Funcionario(idUsuario, idDados);

                FuncionarioDAO.inserir(funcionario);
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

    public static void limparCampos() {
        Principal.formfuncionario.cbxDadosPessoaisFuncionario.setSelectedIndex(0);
        Principal.formfuncionario.cbxUsuarioFuncionario.setSelectedIndex(0);
    }

    public static void preencherCampos(int indexcbxdado, int indexcbxusuario){
        Principal.formfuncionario.cbxDadosPessoaisFuncionario.setSelectedIndex(indexcbxdado);
        Principal.formfuncionario.cbxUsuarioFuncionario.setSelectedIndex(indexcbxusuario);
    }
    
    public static void editar(int idAux) {
        String aux = Principal.formfuncionario.cbxDadosPessoaisFuncionario.getSelectedItem().toString();
        String[] aux2 = aux.split(" --> ");
        String aux3 = Principal.formfuncionario.cbxUsuarioFuncionario.getSelectedItem().toString();
        String[] aux4 = aux3.split(" --> ");
        int idDados = Integer.parseInt(aux2[0]);
        int idUsuario = Integer.parseInt(aux4[0]);

        Funcionario funcionario = new Funcionario(idUsuario, idDados);

        FuncionarioDAO.editar(idAux, funcionario);
        limparCampos();
        Principal.mostrarTelaInicio();
        atualizar();
    }

    static void camposFuncionario(boolean b) {
        Principal.formfuncionario.cbxDadosPessoaisFuncionario.setEnabled(b);
        Principal.formfuncionario.cbxUsuarioFuncionario.setEnabled(b);
        editando = !b;
    }
    
    
}