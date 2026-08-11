package Pagamento.newpackage;

import Pagamento.newpackage.Pagamento;

public class PagamentoBoleto extends Pagamento {
    private String codigoBarras;

    public PagamentoBoleto(double valor, String codigoBarras) {
        super(valor);
        this.codigoBarras = codigoBarras;
    }

    @Override
    public boolean processarPagamento() {
        if (getValor() <= 0) {
            System.out.println("Erro: O valor do pagamento deve ser maior que zero!");
            setStatus("RECUSADO");
            return false;
        }
        if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
            System.out.println("Erro: Codigo de barras e obrigatorio!");
            setStatus("RECUSADO");
            return false;
        }

        setStatus("APROVADO");
        System.out.println(">> Pagamento em Boleto processado com sucesso!");
        return true;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Modalidade    : Boleto Bancario");
        System.out.println("Codigo de Barras   : " + codigoBarras);
    }
}