package Sistema.newpackage;

import Funcionarios.newpackage.Funcionario;
import Funcionarios.newpackage.Gerente;
import Funcionarios.newpackage.Caixa;
import Pagamento.newpackage.Pagamento;
import java.util.ArrayList;
import java.util.List;

public class Loja {
    private List<Funcionario> funcionarios;
    private List<Pagamento> pagamentos;

    public Loja() {
        this.funcionarios = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
        inicializarDadosPadrao();
    }

    private void inicializarDadosPadrao() {
        // Cadastra um gerente padrao para permitir o primeiro acesso ao sistema
        funcionarios.add(new Gerente("Gerente Padrao", "000.000.000-00", 5000.0, "1234", "Administracao", 1000.0));
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        System.out.println("\n>> Funcionario " + funcionario.getNome() + " (ID: " + funcionario.getId() + ") cadastrado com sucesso!");
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public void listarFuncionarios() {
        System.out.println("\n========================================");
        System.out.println("         LISTA DE FUNCIONARIOS          ");
        System.out.println("========================================");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            f.mostrarDados();
            System.out.println("----------------------------------------");
        }
    }

    public void registrarPagamento(Pagamento pagamento) {
        if (pagamento != null) {
            boolean sucesso = pagamento.processarPagamento();
            pagamentos.add(pagamento);
            if (!sucesso) {
                System.out.println(">> O pagamento foi registrado com status: " + pagamento.getStatus());
            }
        }
    }

    public void listarPagamentos() {
        System.out.println("\n========================================");
        System.out.println("          LISTA DE PAGAMENTOS           ");
        System.out.println("========================================");
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento realizado ate o momento.");
            return;
        }
        for (Pagamento p : pagamentos) {
            p.exibirInformacoes();
        }
    }

    public void gerarRelatorioVendas() {
        System.out.println("\n========================================");
        System.out.println("          RELATORIO DE VENDAS           ");
        System.out.println("========================================");

        double totalVendas = 0.0;
        int qtdVendas = 0;

        for (Pagamento p : pagamentos) {
            if ("APROVADO".equalsIgnoreCase(p.getStatus())) {
                p.exibirInformacoes();
                totalVendas += p.getValor();
                qtdVendas++;
            }
        }

        System.out.println("========================================");
        System.out.println("Total de Vendas Aprovadas: " + qtdVendas);
        System.out.printf("Valor Total Arrecadado   : R$ %.2f\n", totalVendas);
        System.out.println("========================================");
    }
}