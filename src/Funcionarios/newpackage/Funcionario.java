package Funcionarios.newpackage;

public abstract class Funcionario {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String cpf;
    private double salario;
    private String senha;

    public Funcionario(String nome, String cpf, double salario, String senha) {
        this.id = contadorId++;
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.senha = senha;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public double getSalario() { return salario; }
    public String getSenha() { return senha; }

    public boolean validarSenha(String senhaDigitada) {
        return this.senha != null && this.senha.equals(senhaDigitada);
    }

    public abstract String getCargo();

    public void mostrarDados() {
        System.out.printf("ID: %d | Nome: %s | CPF: %s | Cargo: %s | Salario: R$ %.2f%n",
                id, nome, cpf, getCargo(), salario);
    }
}