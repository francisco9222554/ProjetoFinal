package fase2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Menu {
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static List<Pagamento> pagamentos = new ArrayList<>();
    private static int contadorPagamento = 1;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 8) {
            System.out.println("\n--- SISTEMA DE PAGAMENTOS (PDV) ---");
            System.out.println("1. Cadastrar Gerente");
            System.out.println("2. Cadastrar Operador de Caixa");
            System.out.println("3. Realizar Pagamento");
            System.out.println("4. Listar Funcionarios");
            System.out.println("5. Listar Pagamentos");
            System.out.println("6. Abrir Caixa");
            System.out.println("7. Fechar Caixa");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opcao: ");
            
            if (teclado.hasNextInt()) {
                opcao = teclado.nextInt();
                teclado.nextLine();
            } else {
                teclado.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarGerente(teclado);
                    break;
                case 2:
                    cadastrarOperadorCaixa(teclado);
                    break;
                case 3:
                    realizarPagamento(teclado);
                    break;
                case 4:
                    listarFuncionarios();
                    break;
                case 5:
                    listarPagamentos();
                    break;
                case 6:
                    alterarStatusCaixa(teclado, true);
                    break;
                case 7:
                    alterarStatusCaixa(teclado, false);
                    break;
                case 8:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
        teclado.close();
    }

    private static boolean idExiste(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private static Funcionario buscarFuncionarioPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    private static void cadastrarGerente(Scanner teclado) {
        System.out.print("ID: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        if (idExiste(id)) {
            System.out.println("Erro: Ja existe um funcionario com esse ID!");
            return;
        }

        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("CPF: ");
        String cpf = teclado.nextLine();
        System.out.print("Salario: ");
        double salario = teclado.nextDouble();
        teclado.nextLine();
        System.out.print("Setor: ");
        String setor = teclado.nextLine();
        System.out.print("Bonus: ");
        double bonus = teclado.nextDouble();

        Gerente g = new Gerente(id, nome, cpf, salario, setor, bonus);
        funcionarios.add(g);
        System.out.println("Gerente cadastrado com sucesso!");
    }

    private static void cadastrarOperadorCaixa(Scanner scanner) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (idExiste(id)) {
            System.out.println("Erro: Ja existe um funcionario com esse ID!");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Salario: ");
        double salario = scanner.nextDouble();
        System.out.print("Numero do Caixa: ");
        int numCaixa = scanner.nextInt();

        Caixa op = new Caixa(id, nome, cpf, salario, numCaixa);
        funcionarios.add(op);
        System.out.println("Operador de Caixa cadastrado com sucesso!");
    }

    private static void realizarPagamento(Scanner teclado) {
        System.out.print("Valor do pagamento: ");
        double valor = teclado.nextDouble();
        teclado.nextLine();
        System.out.print("Modalidade (Dinheiro, Cartao, PIX): ");
        String modalidade = teclado.nextLine();

        Pagamento p = new Pagamento(contadorPagamento++, valor, modalidade);
        if (p.processarPagamento()) {
            pagamentos.add(p);
            System.out.println("Pagamento APROVADO e registrado!");
        } else {
            System.out.println("Falha no pagamento: Valor deve ser maior que zero e a modalidade deve ser informada.");
        }
    }

    private static void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado no sistema.");
            return;
        }
        System.out.println("\n--- LISTA DE FUNCIONARIOS ---");
        for (Funcionario f : funcionarios) {
            f.mostrarDados();
            System.out.println("-----------------------------");
        }
    }

    private static void listarPagamentos() {
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento registrado no sistema.");
            return;
        }
        System.out.println("\n--- LISTA DE PAGAMENTOS ---");
        for (Pagamento p : pagamentos) {
            p.mostrarPagamento();
            System.out.println("-----------------------------");
        }
    }

    private static void alterarStatusCaixa(Scanner teclado, boolean abrir) {
        System.out.print("Digite o ID do Operador de Caixa: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Funcionario f = buscarFuncionarioPorId(id);

        if (f == null) {
            System.out.println("Erro: Funcionario nao encontrado!");
        } else if (f instanceof Caixa) {
            Caixa op = (Caixa) f;
            if (abrir) {
                op.abrirCaixa();
            } else {
                op.fecharCaixa();
            }
        } else {
            System.out.println("Erro: O funcionario informado nao e um Operador de Caixa!");
        }
    }
}
