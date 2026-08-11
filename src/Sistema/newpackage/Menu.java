package Sistema.newpackage;

import Funcionarios.newpackage.Funcionario;
import Funcionarios.newpackage.Gerente;
import Funcionarios.newpackage.Caixa;
import Pagamento.newpackage.*;

import java.util.Scanner;

public class Menu {
    private static Loja loja = new Loja();
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;

        do {
            exibirMenuPrincipal();
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    selecionarPerfilAcesso();
                    break;
                case 2:
                    System.out.println("\nEncerrando o sistema PDV Javaveira... Ate logo!");
                    break;
                default:
                    System.out.println("\nOpcao invalida! Tente novamente.");
            }
        } while (opcao != 2);

        teclado.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n========================================");
        System.out.println("            LOJA JAVAVEIRA              ");
        System.out.println("========================================");
        System.out.println("1 - Acessar Sistema");
        System.out.println("2 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private static void selecionarPerfilAcesso() {
        System.out.println("\n========================================");
        System.out.println("           SELECAO DE PERFIL            ");
        System.out.println("========================================");
        System.out.println("1 - Gerente");
        System.out.println("2 - Operador de Caixa");
        System.out.println("3 - Voltar");
        System.out.print("Escolha uma opcao: ");

        int perfil = lerInteiro();

        switch (perfil) {
            case 1:
                autenticarEExecutarGerente();
                break;
            case 2:
                autenticarEExecutarCaixa();
                break;
            case 3:
                break;
            default:
                System.out.println("Perfil invalido!");
        }
    }

    private static Funcionario realizarLogin(String cargoEsperado) {
        System.out.print("\nID do Funcionario: ");
        int id = lerInteiro();
        System.out.print("Senha: ");
        String senha = teclado.nextLine();

        Funcionario func = loja.buscarFuncionarioPorId(id);

        if (func == null) {
            System.out.println("\n>> Erro: Funcionario nao encontrado!");
            return null;
        }

        if (!func.getCargo().equalsIgnoreCase(cargoEsperado)) {
            System.out.println("\n>> Erro: O perfil selecionado e incompativel com o cargo do usuario!");
            return null;
        }

        if (!func.validarSenha(senha)) {
            System.out.println("\n>> Erro: Senha incorreta!");
            return null;
        }

        System.out.println("\n>> Login realizado com sucesso! Bem-vindo(a), " + func.getNome() + ".");
        return func;
    }

    private static void autenticarEExecutarGerente() {
        Funcionario gerente = realizarLogin("Gerente");
        if (gerente != null) {
            menuGerente();
        }
    }

    private static void autenticarEExecutarCaixa() {
        Funcionario caixa = realizarLogin("Operador de Caixa");
        if (caixa != null && caixa instanceof Caixa) {
            menuCaixa((Caixa) caixa);
        }
    }

    private static void menuGerente() {
        int opcao = 0;
        do {
            System.out.println("\n========================================");
            System.out.println("          PAINEL DO GERENTE             ");
            System.out.println("========================================");
            System.out.println("1 - Cadastrar Funcionario");
            System.out.println("2 - Cadastrar Gerente");
            System.out.println("3 - Listar Funcionarios");
            System.out.println("4 - Gerar Relatorio de Vendas");
            System.out.println("5 - Listar Pagamentos");
            System.out.println("6 - Voltar");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    cadastrarOperadorCaixa();
                    break;
                case 2:
                    cadastrarGerente();
                    break;
                case 3:
                    loja.listarFuncionarios();
                    break;
                case 4:
                    loja.gerarRelatorioVendas();
                    break;
                case 5:
                    loja.listarPagamentos();
                    break;
                case 6:
                    System.out.println("Saindo do Painel do Gerente...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 6);
    }

    private static void menuCaixa(Caixa caixa) {
        int opcao = 0;
        do {
            System.out.println("\n========================================");
            System.out.println("      PAINEL OPERADOR DE CAIXA          ");
            System.out.println("  Operador: " + caixa.getNome() + " | Caixa: " + caixa.getNumeroCaixa() + " (" + caixa.getStatusCaixa() + ")");
            System.out.println("========================================");
            System.out.println("1 - Abrir Caixa");
            System.out.println("2 - Realizar Pagamento");
            System.out.println("3 - Fechar Caixa");
            System.out.println("4 - Listar Pagamentos");
            System.out.println("5 - Voltar");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    caixa.abrirCaixa();
                    break;
                case 2:
                    if (!caixa.isAberto()) {
                        System.out.println("\n>> ERRO: O caixa esta FECHADO! Abra o caixa antes de realizar pagamentos.");
                    } else {
                        menuFormasPagamento();
                    }
                    break;
                case 3:
                    caixa.fecharCaixa();
                    break;
                case 4:
                    loja.listarPagamentos();
                    break;
                case 5:
                    System.out.println("Saindo do Painel do Operador...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 5);
    }

    private static void cadastrarGerente() {
        System.out.println("\n--- Cadastrar Gerente ---");
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("CPF: ");
        String cpf = teclado.nextLine();
        System.out.print("Salario: R$ ");
        double salario = lerDouble();
        System.out.print("Senha: ");
        String senha = teclado.nextLine();
        System.out.print("Setor: ");
        String setor = teclado.nextLine();
        System.out.print("Bonus: R$ ");
        double bonus = lerDouble();

        loja.cadastrarFuncionario(new Gerente(nome, cpf, salario, senha, setor, bonus));
    }

    private static void cadastrarOperadorCaixa() {
        System.out.println("\n--- Cadastrar Operador de Caixa ---");
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("CPF: ");
        String cpf = teclado.nextLine();
        System.out.print("Salario: R$ ");
        double salario = lerDouble();
        System.out.print("Senha: ");
        String senha = teclado.nextLine();
        System.out.print("Numero do Caixa: ");
        int numCaixa = lerInteiro();

        loja.cadastrarFuncionario(new Caixa(nome, cpf, salario, senha, numCaixa));
    }

    private static void menuFormasPagamento() {
        System.out.println("\n===== FORMA DE PAGAMENTO =====");
        System.out.println("1 - PIX");
        System.out.println("2 - Boleto");
        System.out.println("3 - Cartao");
        System.out.print("Escolha a opcao: ");
        int opcao = lerInteiro();

        System.out.print("Digite o valor do pagamento: R$ ");
        double valor = lerDouble();

        Pagamento novoPagamento = null;

        switch (opcao) {
            case 1:
                System.out.print("Informe a chave PIX: ");
                String chavePix = teclado.nextLine();
                novoPagamento = new PagamentoPix(valor, chavePix);
                break;

            case 2:
                System.out.print("Informe o codigo de barras: ");
                String codBarras = teclado.nextLine();
                novoPagamento = new PagamentoBoleto(valor, codBarras);
                break;

            case 3:
                novoPagamento = processarMenuCartao(valor);
                break;

            default:
                System.out.println("Modalidade de pagamento invalida!");
                return;
        }

        if (novoPagamento != null) {
            loja.registrarPagamento(novoPagamento);
        }
    }

    private static Pagamento processarMenuCartao(double valor) {
        System.out.println("\n===== TIPO DE CARTAO =====");
        System.out.println("1 - Debito");
        System.out.println("2 - Credito");
        System.out.print("Escolha a opcao: ");
        int tipoCartao = lerInteiro();

        System.out.print("Numero do Cartao: ");
        String num = teclado.nextLine();
        System.out.print("Nome do Titular: ");
        String titular = teclado.nextLine();
        System.out.print("Bandeira: ");
        String bandeira = teclado.nextLine();
        System.out.print("CVV: ");
        String cvv = teclado.nextLine();

        if (tipoCartao == 1) {
            System.out.print("Informe o saldo disponivel do cliente: R$ ");
            double saldo = lerDouble();
            System.out.print("Digite a senha do cartao: ");
            String senha = teclado.nextLine();

            return new CartaoDebito(valor, num, titular, bandeira, cvv, saldo, senha);

        } else if (tipoCartao == 2) {
            System.out.print("Informe o limite disponivel do cliente: R$ ");
            double limite = lerDouble();

            System.out.println("Tipo de Pagamento no Credito:");
            System.out.println("1 - A Vista");
            System.out.println("2 - Parcelado");
            System.out.print("Escolha: ");
            int condicao = lerInteiro();

            if (condicao == 2) {
                System.out.print("Quantidade de parcelas: ");
                int parcelas = lerInteiro();
                return new CartaoCredito(valor, num, titular, bandeira, cvv, limite, parcelas);
            } else {
                return new CartaoCredito(valor, num, titular, bandeira, cvv, limite);
            }
        } else {
            System.out.println("Tipo de cartao invalido!");
            return null;
        }
    }

    private static int lerInteiro() {
        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private static double lerDouble() {
        try {
            return Double.parseDouble(teclado.nextLine());
        } catch (Exception e) {
            return 0.0;
        }
    }
}