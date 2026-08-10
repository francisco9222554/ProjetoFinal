package fase1;
public class Caixa {
    private int numero;
    private Funcionario operador;
    private boolean aberto;

    public Caixa(int numero, Funcionario operador) {
        this.numero = numero;
        this.operador = operador;
        this.aberto = false;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public Funcionario getOperador() { return operador; }
    public void setOperador(Funcionario operador) { this.operador = operador; }

    public boolean isAberto() { return aberto; }

    public void abrirCaixa() {
        if (!aberto) {
            this.aberto = true;
            System.out.println("Caixa " + numero + " ABERTO com sucesso.");
        } else {
            System.out.println("Caixa " + numero + " ja esta aberto.");
        }
    }

    public void fecharCaixa() {
        if (aberto) {
            this.aberto = false;
            System.out.println("Caixa " + numero + " FECHADO com sucesso.");
        } else {
            System.out.println("Caixa " + numero + " ja esta fechado.");
        }
    }

    public void mostrarDados() {
        String status = aberto ? "ABERTO" : "FECHADO";
        String nomeOperador = (operador != null) ? operador.getNome() : "Sem operador";
        System.out.printf("Caixa No: %d | Status: %s | Operador: %s%n", 
                numero, status, nomeOperador);
    }
}
