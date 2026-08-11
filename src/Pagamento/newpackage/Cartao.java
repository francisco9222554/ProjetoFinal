package Pagamento.newpackage;

public abstract class Cartao extends Pagamento {
    private String numeroCartao;
    private String nomeTitular;
    private String bandeira;
    private String cvv;

    public Cartao(double valor, String numeroCartao, String nomeTitular, String bandeira, String cvv) {
        super(valor);
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.bandeira = bandeira;
        this.cvv = cvv;
    }

    protected boolean validarDadosCartao() {
        if (numeroCartao == null || numeroCartao.trim().isEmpty() ||
            nomeTitular == null || nomeTitular.trim().isEmpty() ||
            bandeira == null || bandeira.trim().isEmpty() ||
            cvv == null || cvv.trim().isEmpty()) {
            System.out.println("Erro: Todos os dados do cartao devem ser preenchidos!");
            return false;
        }
        return true;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Titular       : " + nomeTitular);
        System.out.println("Bandeira      : " + bandeira);
        String finalCartao = (numeroCartao != null && numeroCartao.length() >= 4) 
                ? numeroCartao.substring(numeroCartao.length() - 4) 
                : numeroCartao;
        System.out.println("Numero Cartao : **** **** **** " + finalCartao);
    }
}