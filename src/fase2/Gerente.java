package fase2;
public class Gerente extends Funcionario {
    private String setor;
    private double bonus;

    public Gerente(int id, String nome, String cpf, double salario, String setor, double bonus) {
        super(id, nome, cpf, salario, "Gerente");
        this.setor = setor;
        this.bonus = bonus;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.println("Setor: " + setor);
        System.out.println("Bonus: R$ " + String.format("%.2f", bonus));
    }
}
