package Funcionarios.newpackage;
public abstract class Funcionario {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String cpf;
    private double salario;

    public Funcionario(String nome, String cpf, double salario) {
        this.id = contadorId++;
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public double getSalario() { return salario; }

    public abstract String getCargo();

    public void mostrarDados() {
        System.out.printf("ID: %d | Nome: %s | CPF: %s | Cargo: %s | Salario: R$ %.2f%n",
                id, nome, cpf, getCargo(), salario);
    }
}