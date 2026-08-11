package Sistema.newpackage;
import Funcionarios.newpackage.Funcionario;
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
    }

    public void cadastrarFuncionario(Funcionario f) {
        if (buscarFuncionarioPorId(f.getId()) != null) {
            System.out.println("Erro: Ja existe um funcionario cadastrado com o ID " + f.getId() + "!");
            return;
        }
        funcionarios.add(f);
        System.out.println("Funcionario cadastrado com sucesso!");
    }

    public void registrarPagamento(Pagamento p) {
        if (p != null) {
            if (p.processarPagamento()) {
                pagamentos.add(p);
            }
        }
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public void abrirCaixaFuncionario(int id) {
        Funcionario f = buscarFuncionarioPorId(id);
        if (f instanceof Caixa) {
            ((Caixa) f).abrirCaixa();
        } else if (f != null) {
            System.out.println("Erro: O funcionario informado nao e um Operador de Caixa!");
        } else {
            System.out.println("Erro: Funcionario nao encontrado!");
        }
    }

    public void fecharCaixaFuncionario(int id) {
        Funcionario f = buscarFuncionarioPorId(id);
        if (f instanceof Caixa) {
            ((Caixa) f).fecharCaixa();
        } else if (f != null) {
            System.out.println("Erro: O funcionario informado nao e um Operador de Caixa!");
        } else {
            System.out.println("Erro: Funcionario nao encontrado!");
        }
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado no sistema.");
        } else {
            System.out.println("\n--- LISTA DE FUNCIONARIOS ---");
            for (Funcionario f : funcionarios) {
                f.mostrarDados();
                System.out.println("-----------------------------");
            }
        }
    }

    public void listarPagamentos() {
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento cadastrado.");
        } else {
            System.out.println("\n--- LISTA DE PAGAMENTOS ---");
            for (Pagamento p : pagamentos) {
                p.exibirInformacoes();
            }
            System.out.println("----------------------------------------");
        }
    }
}