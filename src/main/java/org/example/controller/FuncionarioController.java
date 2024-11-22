package org.example.controller;

import org.example.bancoController.RelatorioCSV;
import org.example.dao.*;
import org.example.entity.*;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


public class FuncionarioController {
    private final FuncionarioDAO funcionarioDao = new FuncionarioDAO();
    private final UsuarioDAO usuarioDao = new UsuarioDAO();
    private final ClienteDAO clienteDao = new ClienteDAO();

    public void cadastroFuncionario(Funcionario funcionario){
        funcionarioDao.salvar(funcionario);
    }

    public Funcionario atualizarFuncionario(Funcionario funcionario){
        return funcionarioDao.update(funcionario);
    }

    public boolean login(String user, String senha, TipoUsuario tipousuario){
        if(user.isEmpty() || user.equals(" ")){
            return false;
        }else if (senha.isEmpty() || senha.equals(" ")){
            return false;
        }else{
            return usuarioDao.validarUsuario(user, senha, tipousuario);
        }
    }

    public boolean validarFuncionario(String user, String password, TipoUsuario tipoUsuario){
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.validarUsuario(user, password, tipoUsuario)){
            return true;
        }else{
            return false;
        }
    }

    public void gerarRelatorio(String user, String password, TipoUsuario tipoUsuario ) throws IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        RelatorioCSV relatorioCSV = new RelatorioCSV();
        ContaEntity conta = new ContaEntity();
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();



        Instant momento = Instant.now();
        Timestamp agora =  Timestamp.from(momento);

        List<Transacao> transacoes = transacaoDAO.buscarTransacoes(conta.getId());

        StringBuilder conteudoRelatorio = new StringBuilder();
        for (Transacao t : transacoes) {
            conteudoRelatorio.append(String.format("%d: Movimentacao: %s - %s%n", t.getId(), t.getValor(), t.getTipoTransacao()));
        }

        // Salva o relatório
        Relatorio relatorio = new Relatorio();
        relatorio.setTipoRelatorio("Relatório de Transações");
        relatorio.setTimestamp(agora);
        relatorio.setConteudo(conteudoRelatorio.toString());
        int idRetorno = clienteDAO.buscarporId(user,password);
        Funcionario funcionario = funcionarioDao.buscarFuncionarioPorId(idRetorno);
        relatorio.setFuncionario(funcionario);

        relatorioDAO.salvar(relatorio);

        relatorioCSV.relatorioCSV();

        File file1 = new File("relatorio.csv");

        if (usuarioDAO.validarUsuario(user, password, tipoUsuario)) {
            try {

                String os = System.getProperty("os.name").toLowerCase();

                if (os.contains("win")) {

                    String command = "cmd /c start excel \"" + file1.getAbsolutePath() + "\"";
                    Runtime.getRuntime().exec(command);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }

        usuarioDAO.fechar();
    }

    public boolean buscarClienteId(String numeroId){
        return clienteDao.buscarClienteId(numeroId);
    }

    public void fecharOperacao(){
        funcionarioDao.fechar();
    }
}
