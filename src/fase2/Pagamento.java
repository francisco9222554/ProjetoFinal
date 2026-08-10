package fase2;
public class Pagamento {
    private int numeroPagamento;
    private double valor;
    private String modalidade;
    private String status;

    public Pagamento(int numeroPagamento, double valor, String modalidade) {
        this.numeroPagamento = numeroPagamento;
        this.valor = valor;
        this.modalidade = modalidade;
        this.status = "PENDENTE";
    }

    public boolean processarPagamento() {
        if (valor > 0 && modalidade != null && !modalidade.trim().isEmpty()) {
            this.status = "APROVADO";
            return true;
        } else {
            this.status = "RECUSADO";
            return false;
        }
    }

    public void mostrarPagamento() {
        System.out.println("N Pagamento: " + numeroPagamento);
        System.out.println("Valor: R$ " + String.format("%.2f", valor));
        System.out.println("Modalidade: " + modalidade);
        System.out.println("Status: " + status);
    }
}
