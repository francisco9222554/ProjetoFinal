package fase1;
import java.util.Scanner;
public class Menu {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Loja loja = new Loja();
        Funcionario usuarioLogado = null;

        System.out.println("==================================");
        System.out.println("   BEM-VINDO AO SISTEMA PDV       ");
        System.out.println("==================================");

        int opcao = 0;

        do {
            System.out.println("\n==================================");
            System.out.println("      SISTEMA DE PDV - LOJA      ");
            if (usuarioLogado != null) {
                System.out.println("Operador Atual: " + usuarioLogado.getNome() + " (" + usuarioLogado.getCargo() + ")");
            } else {
                System.out.println("SessÃ£o: NENHUM USUÃ�RIO CONECTADO");
            }
            System.out.println("==================================");
            System.out.println("0 - Selecionar/Trocar Operador");
            System.out.println("1 - Cadastrar FuncionÃ¡rio");
            System.out.println("2 - Cadastrar Caixa");
            System.out.println("3 - Realizar Pagamento");
            System.out.println("4 - Listar FuncionÃ¡rios");
            System.out.println("5 - Listar Caixas");
            System.out.println("6 - Listar Pagamentos");
            System.out.println("7 - Gerenciar Status do Caixa (Abrir/Fechar)");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opÃ§Ã£o: ");

            try {
                opcao = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("OpÃ§Ã£o invÃ¡lida! Digite um nÃºmero.");
                continue;
            }

            switch (opcao) {
                case 0:
                    System.out.print("Digite o ID do Funcionario para logar: ");
                    int idLog = Integer.parseInt(teclado.nextLine());
                    Funcionario fLog = loja.buscarFuncionarioPorId(idLog);
                    if (fLog != null) {
                        usuarioLogado = fLog;
                        System.out.println("Login realizado com sucesso! Bem-vindo, " + usuarioLogado.getNome());
                    } else {
                        System.out.println("Erro: Funcionario nÃ£o encontrado. Cadastre um funcionÃ¡rio primeiro.");
                    }
                    break;

                case 1:
                    System.out.print("ID: ");
                    int id = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nome: ");
                    String nome = teclado.nextLine();
                    System.out.print("CPF: ");
                    String cpf = teclado.nextLine();
                    System.out.print("SalÃ¡rio: R$ ");
                    double salario = Double.parseDouble(teclado.nextLine());
                    System.out.print("Cargo: ");
                    String cargo = teclado.nextLine();

                    Funcionario novoFunc = new Funcionario(id, nome, cpf, salario, cargo);
                    loja.cadastrarFuncionario(novoFunc);
                    
                    if (usuarioLogado == null) {
                        usuarioLogado = novoFunc;
                    }
                    break;

                case 2:
                    System.out.print("NÃºmero do Caixa: ");
                    int numCaixa = Integer.parseInt(teclado.nextLine());
                    System.out.print("ID do FuncionÃ¡rio Operador deste Caixa: ");
                    int idOperador = Integer.parseInt(teclado.nextLine());

                    Funcionario op = loja.buscarFuncionarioPorId(idOperador);
                    if (op != null) {
                        Caixa novoCaixa = new Caixa(numCaixa, op);
                        loja.cadastrarCaixa(novoCaixa);
                    } else {
                        System.out.println("Erro: FuncionÃ¡rio nÃ£o encontrado.");
                    }
                    break;

                case 3:
                    if (usuarioLogado == null) {
                        System.out.println("Erro: Ã‰ necessÃ¡rio estar logado/selecionado um operador no sistema (OpÃ§Ã£o 0).");
                        break;
                    }

                    System.out.print("NÃºmero do Caixa para OperaÃ§Ã£o: ");
                    int numCaixaPag = Integer.parseInt(teclado.nextLine());
                    Caixa caixa = loja.buscarCaixaPorNumero(numCaixaPag);

                    if (caixa == null) {
                        System.out.println("Erro: Caixa nÃ£o encontrado.");
                        break;
                    }

                    if (!caixa.isAberto()) {
                        System.out.println("Caixa estÃ¡ FECHADO. Deseja abrir agora? (1-Sim / 2-NÃ£o)");
                        int resp = Integer.parseInt(teclado.nextLine());
                        if (resp == 1) {
                            caixa.abrirCaixa();
                        } else {
                            System.out.println("OperaÃ§Ã£o cancelada. O caixa precisa estar ABERTO para realizar pagamentos.");
                            break;
                        }
                    }

                    System.out.print("Valor da venda: R$ ");
                    double valor = Double.parseDouble(teclado.nextLine());
                    System.out.print("Modalidade (Dinheiro, CartÃ£o, PIX, etc.): ");
                    String modalidade = teclado.nextLine();

                    Pagamento novoPagamento = new Pagamento(loja.getProximoIdPagamento());
                    boolean sucesso = novoPagamento.realizarPagamento(valor, modalidade);

                    if (sucesso) {
                        loja.registrarPagamento(novoPagamento);
                    }
                    break;

                case 4:
                    loja.listarFuncionarios();
                    break;

                case 5:
                    loja.listarCaixas();
                    break;

                case 6:
                    loja.listarPagamentos();
                    break;

                case 7:
                    System.out.print("Digite o nÃºmero do Caixa: ");
                    int nCaixa = Integer.parseInt(teclado.nextLine());
                    Caixa cx = loja.buscarCaixaPorNumero(nCaixa);
                    if (cx != null) {
                        System.out.println("Status atual: " + (cx.isAberto() ? "ABERTO" : "FECHADO"));
                        System.out.println("1 - Abrir Caixa");
                        System.out.println("2 - Fechar Caixa");
                        System.out.print("Escolha: ");
                        int acao = Integer.parseInt(teclado.nextLine());
                        if (acao == 1) cx.abrirCaixa();
                        else if (acao == 2) cx.fecharCaixa();
                        else System.out.println("OpÃ§Ã£o invÃ¡lida.");
                    } else {
                        System.out.println("Caixa nÃ£o encontrado.");
                    }
                    break;

                case 8:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("OpÃ§Ã£o invÃ¡lida.");
            }

        } while (opcao != 8);

        teclado.close();
    }
}