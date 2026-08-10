package fase2;

public class Caixa extends Funcionario {
    private int numeroCaixa;
    private String statusCaixa;

    public Caixa(int id, String nome, String cpf, double salario, int numeroCaixa) {
        super(id, nome, cpf, salario, "Operador de Caixa");
        this.numeroCaixa = numeroCaixa;
        this.statusCaixa = "FECHADO";
    }

    public int getNumeroCaixa() {
        return numeroCaixa;
    }

    public String getStatusCaixa() {
        return statusCaixa;
    }

    public void abrirCaixa() {
        this.statusCaixa = "ABERTO";
        System.out.println("Caixa " + numeroCaixa + " ABERTO com sucesso!");
    }

    public void fecharCaixa() {
        this.statusCaixa = "FECHADO";
        System.out.println("Caixa " + numeroCaixa + " FECHADO com sucesso!");
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.println("Numero do Caixa: " + numeroCaixa);
        System.out.println("Status do Caixa: " + statusCaixa);
    }
}