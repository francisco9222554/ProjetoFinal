package fase1;
import java.util.ArrayList;
import java.util.List;
public class Loja {
    private List<Funcionario> funcionarios;
    private List<Caixa> caixas;
    private List<Pagamento> pagamentos;

    public Loja() {
        this.funcionarios = new ArrayList<>();
        this.caixas = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
    }

    public void cadastrarFuncionario(Funcionario f) {
        funcionarios.add(f);
        System.out.println("Funcionario cadastrado com sucesso!");
    }

    public void cadastrarCaixa(Caixa c) {
        caixas.add(c);
        System.out.println("Caixa cadastrado com sucesso!");
    }

    public void registrarPagamento(Pagamento p) {
        pagamentos.add(p);
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public Caixa buscarCaixaPorNumero(int numero) {
        for (Caixa c : caixas) {
            if (c.getNumero() == numero) {
                return c;
            }
        }
        return null;
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
        } else {
            System.out.println("\n--- LISTA DE FUNCIONARIOS ---");
            for (Funcionario f : funcionarios) {
                f.mostrarDados();
            }
        }
    }

    public void listarCaixas() {
        if (caixas.isEmpty()) {
            System.out.println("Nenhum caixa cadastrado.");
        } else {
            System.out.println("\n--- LISTA DE CAIXAS ---");
            for (Caixa c : caixas) {
                c.mostrarDados();
            }
        }
    }

    public void listarPagamentos() {
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento registrado.");
        } else {
            System.out.println("\n--- LISTA DE PAGAMENTOS ---");
            for (Pagamento p : pagamentos) {
                p.mostrarPagamento();
            }
        }
    }

    public int getProximoIdPagamento() {
        return pagamentos.size() + 1;
    }
}
