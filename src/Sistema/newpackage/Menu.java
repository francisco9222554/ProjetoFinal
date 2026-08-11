package Sistema.newpackage;
import Sistema.newpackage.Loja;
import Funcionarios.newpackage.Gerente;
import Funcionarios.newpackage.Caixa;
import Pagamento.newpackage.PagamentoBoleto;
import Pagamento.newpackage.PagamentoPix;
import Pagamento.newpackage.Pagamento;
import Pagamento.newpackage.CartaoDebito;
import Pagamento.newpackage.CartaoCredito;
import java.util.Scanner;
public class Menu {
    private static Loja loja = new Loja();
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("\n===== BEM VINDO AO PDV =====");
            System.out.println("1 - Cadastrar Gerente");
            System.out.println("2 - Cadastrar Operador de Caixa");
            System.out.println("3 - Realizar Pagamento");
            System.out.println("4 - Listar Funcionarios");
            System.out.println("5 - Listar Pagamentos");
            System.out.println("6 - Abrir Caixa");
            System.out.println("7 - Fechar Caixa");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    cadastrarGerente();
                    break;
                case 2:
                    cadastrarOperadorCaixa();
                    break;
                case 3:
                    menuFormasPagamento();
                    break;
                case 4:
                    loja.listarFuncionarios();
                    break;
                case 5:
                    loja.listarPagamentos();
                    break;
                case 6:
                    alterarStatusCaixa(true);
                    break;
                case 7:
                    alterarStatusCaixa(false);
                    break;
                case 8:
                    System.out.println("\nEncerrando o sistema PDV... Ate logo!");
                    break;
                default:
                    System.out.println("\nOpcao invalida! Tente novamente.");
            }
        } while (opcao != 8);

        teclado.close();
    }

    private static void cadastrarGerente() {
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("CPF: ");
        String cpf = teclado.nextLine();
        System.out.print("Salario: R$ ");
        double salario = lerDouble();
        System.out.print("Setor: ");
        String setor = teclado.nextLine();
        System.out.print("Bonus: R$ ");
        double bonus = lerDouble();

        loja.cadastrarFuncionario(new Gerente(nome, cpf, salario, setor, bonus));
    }

    private static void cadastrarOperadorCaixa() {
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("CPF: ");
        String cpf = teclado.nextLine();
        System.out.print("Salario: R$ ");
        double salario = lerDouble();
        System.out.print("Numero do Caixa: ");
        int numCaixa = lerInteiro();

        loja.cadastrarFuncionario(new Caixa(nome, cpf, salario, numCaixa));
    }

    private static void alterarStatusCaixa(boolean abrir) {
        System.out.print("Informe o ID do Operador de Caixa: ");
        int id = lerInteiro();
        if (abrir) {
            loja.abrirCaixaFuncionario(id);
        } else {
            loja.fecharCaixaFuncionario(id);
        }
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