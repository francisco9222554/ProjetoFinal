package Pagamento.newpackage;
public abstract class Pagamento {
    private static int contador = 1;
    private int numeroPagamento;
    private double valor;
    private String status;

    public Pagamento(double valor) {
        this.numeroPagamento = contador++;
        this.valor = valor;
        this.status = "PENDENTE";
    }

    public int getNumeroPagamento() {
        return numeroPagamento;
    }

    public double getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    public abstract boolean processarPagamento();

    public void exibirInformacoes() {
        System.out.println("----------------------------------------");
        System.out.println("Num. Pagamento: " + numeroPagamento);
        System.out.printf("Valor Total   : R$ %.2f\n", valor);
        System.out.println("Status        : " + status);
    }
}