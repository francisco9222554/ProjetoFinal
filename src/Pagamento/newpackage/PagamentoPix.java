package Pagamento.newpackage;

public class PagamentoPix extends Pagamento {
    private String chavePix;

    public PagamentoPix(double valor, String chavePix) {
        super(valor);
        this.chavePix = chavePix;
    }

    @Override
    public boolean processarPagamento() {
        if (getValor() <= 0) {
            System.out.println("Erro: O valor do pagamento deve ser maior que zero!");
            setStatus("RECUSADO");
            return false;
        }
        if (chavePix == null || chavePix.trim().isEmpty()) {
            System.out.println("Erro: Chave PIX e obrigatoria!");
            setStatus("RECUSADO");
            return false;
        }

        setStatus("APROVADO");
        System.out.println(">> Pagamento PIX realizado com sucesso!");
        return true;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Modalidade    : PIX");
        System.out.println("Chave PIX     : " + chavePix);
    }
}