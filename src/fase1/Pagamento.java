package fase1;
public class Pagamento {
    private int numeroPagamento;
    private double valor;
    private String modalidade;
    private String status;

    public Pagamento(int numeroPagamento) {
        this.numeroPagamento = numeroPagamento;
        this.status = "PENDENTE";
    }

    public int getNumeroPagamento() { return numeroPagamento; }
    public double getValor() { return valor; }
    public String getModalidade() { return modalidade; }
    public String getStatus() { return status; }

    public boolean realizarPagamento(double valor, String modalidade) {
        if (valor <= 0) {
            System.out.println("Erro: O valor do pagamento deve ser maior que zero.");
            return false;
        }
        if (modalidade == null || modalidade.trim().isEmpty()) {
            System.out.println("Erro: Uma modalidade de pagamento deve ser informada.");
            return false;
        }

        this.valor = valor;
        this.modalidade = modalidade;
        this.status = "APROVADO";
        System.out.println("Pagamento R$ " + valor + " via " + modalidade + " APROVADO!");
        return true;
    }

    public void mostrarPagamento() {
        System.out.printf("Pagamento No: %d | Valor: R$ %.2f | Modalidade: %s | Status: %s%n",
                numeroPagamento, valor, modalidade, status);
    }
}
