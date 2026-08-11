package Funcionarios.newpackage;

public class Gerente extends Funcionario {
    private String setor;
    private double bonus;

    public Gerente(String nome, String cpf, double salario, String senha, String setor, double bonus) {
        super(nome, cpf, salario, senha);
        this.setor = setor;
        this.bonus = bonus;
    }

    @Override
    public String getCargo() {
        return "Gerente";
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.println("Setor: " + setor);
        System.out.println("Bonus: R$ " + String.format("%.2f", bonus));
    }
}