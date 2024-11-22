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
    private final ContaDAO contaDao = new ContaDAO();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();

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
        ContaEntity conta = new ContaEntity();  // Ajuste para uma conta válida
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();

        Instant momento = Instant.now();
        Timestamp agora = Timestamp.from(momento);

        // Buscando transações associadas à conta
        List<Transacao> transacoes = transacaoDAO.buscarTransacoes(conta.getId());
        System.out.println("Número de transações encontradas: " + transacoes.size());

        // Construindo o conteúdo do relatório com o StringBuilder
        StringBuilder conteudoRelatorio = new StringBuilder();
        for (Transacao t : transacoes) {
            // Formata cada linha do conteúdo do relatório com o formato desejado
            String linha = String.format("%d: Movimentacao: %.2f - %s%n",
                    t.getId(), t.getValor(), t.getTipoTransacao());
            conteudoRelatorio.append(linha);
        }

        // Verificando o conteúdo gerado
        System.out.println("Conteúdo gerado pelo StringBuilder: \n" + conteudoRelatorio);

        // Salvando o relatório com o conteúdo gerado
        Relatorio relatorio = new Relatorio();
        relatorio.setTipoRelatorio("Relatório de Transações");
        relatorio.setTimestamp(agora);
        relatorio.setConteudo(conteudoRelatorio.toString());  // Conteúdo gerado com transações
        System.out.println("Conteúdo atribuído ao relatório: \n" + relatorio.getConteudo());

        // Atribuindo o funcionário
        int idRetorno = clienteDAO.buscarporId(user, password);
        Funcionario funcionario = funcionarioDao.buscarFuncionarioPorId(idRetorno);
        relatorio.setFuncionario(funcionario);

        // Salvando o relatório no banco
        relatorioDAO.salvar(relatorio);
        System.out.println("Relatório salvo no banco com conteúdo: \n" + relatorio.getConteudo());

        // Gerando o arquivo CSV
        relatorioCSV.relatorioCSV();  // Passando o relatório para o método do CSV

        // Abrindo o arquivo CSV no Excel
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

    public boolean buscarFuncionarioId(String numeroId){
        return funcionarioDao.buscarPorId(numeroId);
    }

    public void fecharOperacao(){
        funcionarioDao.fechar();
    }

    public boolean alterarFuncionario(String codigoFuncionario, String cargo, String telefone, String local, int numeroCasa, String cep, String bairro, String cidade, String estado){

        Funcionario funcionario = funcionarioDao.buscarFuncionarioPorId(codigoFuncionario);
        UsuarioEntity usuarioEntity = funcionario.getUsuario();
        Endereco endereco = enderecoDAO.buscarEnderecoUsuario(usuarioEntity.getId());

        funcionario.setCargo(cargo);
        funcionario.setTelefone(telefone);
        endereco.setNumeroCasa(numeroCasa);
        endereco.setCep(cep);
        endereco.setLocal(local);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);

        funcionarioDao.update(funcionario);
        enderecoDAO.update(endereco);

        return true;
    }

    public String consultarFuncionario(String numeroFuncionario){
        EnderecoDAO enderecoDAO = new EnderecoDAO();


        Funcionario funcionario = funcionarioDao.buscarFuncionarioPorId(numeroFuncionario);
        UsuarioEntity usuarioEntity = funcionario.getUsuario();

        if (funcionario != null) {
            Endereco endereco = enderecoDAO.buscarEnderecoUsuario(funcionario.getId());

            return "Nome: " + usuarioEntity.getNome() +
                    "\nCodigo Funcionario: " + funcionario.getCodigoFuncionario() +
                    "\nCargo: " + funcionario.getCargo() +
                    "\nCpf: " + usuarioEntity.getCpf() +
                    "\nData de Nascimento: " + usuarioEntity.getDataNascimento() +
                    "\nNumero da casa: " + endereco.getNumeroCasa() +
                    "\nCEP: " + endereco.getCep() +
                    "\nBairro: " + endereco.getBairro() +
                    "\nCidade: " + endereco.getCidade() +
                    "\nEstado: " + endereco.getEstado();

        }

        return "Funcionario não encontrado";

    }
}
