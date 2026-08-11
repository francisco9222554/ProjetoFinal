package Funcionarios.newpackage;

public class Caixa extends Funcionario {
    private int numeroCaixa;
    private String statusCaixa;

    public Caixa(String nome, String cpf, double salario, String senha, int numeroCaixa) {
        super(nome, cpf, salario, senha);
        this.numeroCaixa = numeroCaixa;
        this.statusCaixa = "FECHADO";
    }

    public int getNumeroCaixa() { return numeroCaixa; }
    public String getStatusCaixa() { return statusCaixa; }

    public boolean isAberto() {
        return "ABERTO".equalsIgnoreCase(this.statusCaixa);
    }

    public void abrirCaixa() {
        if (isAberto()) {
            System.out.println(">> ATENCAO: O Caixa " + numeroCaixa + " ja esta ABERTO!");
        } else {
            this.statusCaixa = "ABERTO";
            System.out.println(">> Caixa " + numeroCaixa + " ABERTO com sucesso!");
        }
    }

    public void fecharCaixa() {
        if (!isAberto()) {
            System.out.println(">> ATENCAO: O Caixa " + numeroCaixa + " ja esta FECHADO!");
        } else {
            this.statusCaixa = "FECHADO";
            System.out.println(">> Caixa " + numeroCaixa + " FECHADO com sucesso!");
        }
    }

    @Override
    public String getCargo() {
        return "Operador de Caixa";
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.println("Numero do Caixa: " + numeroCaixa);
        System.out.println("Status do Caixa: " + statusCaixa);
    }
}