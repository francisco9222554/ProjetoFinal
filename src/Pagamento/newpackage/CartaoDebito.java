package Pagamento.newpackage;

import Pagamento.newpackage.Cartao;

public class CartaoDebito extends Cartao {
    private double saldoDisponivel;
    private String senha;

    public CartaoDebito(double valor, String numeroCartao, String nomeTitular, String bandeira, String cvv, double saldoDisponivel, String senha) {
        super(valor, numeroCartao, nomeTitular, bandeira, cvv);
        this.saldoDisponivel = saldoDisponivel;
        this.senha = senha;
    }

    @Override
    public boolean processarPagamento() {
        if (getValor() <= 0) {
            System.out.println("Erro: O valor do pagamento deve ser maior que zero!");
            setStatus("RECUSADO");
            return false;
        }

        if (!validarDadosCartao() || senha == null || senha.trim().isEmpty()) {
            System.out.println("Erro: Credenciais do cartao de debito invalidas!");
            setStatus("RECUSADO");
            return false;
        }

        if (saldoDisponivel < getValor()) {
            System.out.println("Motivo da Recusa: Saldo insuficiente em conta!");
            setStatus("RECUSADO");
            return false;
        }

        saldoDisponivel -= getValor();
        setStatus("APROVADO");
        System.out.println(">> Pagamento no Debito aprovado com sucesso!");
        return true;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Modalidade    : Cartao de Debito");
        System.out.printf("Saldo Rest.   : R$ %.2f\n", saldoDisponivel);
    }
}