package Pagamento.newpackage;

import Pagamento.newpackage.Cartao;

public class CartaoCredito extends Cartao {
    private double limiteDisponivel;
    private int quantidadeParcelas;

    public CartaoCredito(double valor, String numeroCartao, String nomeTitular, String bandeira, String cvv, double limiteDisponivel) {
        super(valor, numeroCartao, nomeTitular, bandeira, cvv);
        this.limiteDisponivel = limiteDisponivel;
        this.quantidadeParcelas = 1;
    }

    public CartaoCredito(double valor, String numeroCartao, String nomeTitular, String bandeira, String cvv, double limiteDisponivel, int quantidadeParcelas) {
        super(valor, numeroCartao, nomeTitular, bandeira, cvv);
        this.limiteDisponivel = limiteDisponivel;
        this.quantidadeParcelas = (quantidadeParcelas <= 0) ? 1 : quantidadeParcelas;
    }

    @Override
    public boolean processarPagamento() {
        if (getValor() <= 0) {
            System.out.println("Erro: O valor do pagamento deve ser maior que zero!");
            setStatus("RECUSADO");
            return false;
        }

        if (!validarDadosCartao()) {
            setStatus("RECUSADO");
            return false;
        }

        if (limiteDisponivel < getValor()) {
            System.out.println("Motivo da Recusa: Limite de credito insuficiente!");
            setStatus("RECUSADO");
            return false;
        }

        limiteDisponivel -= getValor();
        setStatus("APROVADO");
        System.out.println(">> Pagamento no Credito aprovado com sucesso!");
        return true;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Modalidade    : Cartao de Credito");
        if (quantidadeParcelas > 1) {
            double valorParcela = getValor() / quantidadeParcelas;
            System.out.println("Condicao      : Parcelado em " + quantidadeParcelas + "x");
            System.out.printf("Valor/Parcela   : R$ %.2f\n", valorParcela);
        } else {
            System.out.println("Condicao      : A Vista");
        }
        System.out.printf("Limite Restante  : R$ %.2f\n", limiteDisponivel);
    }
}