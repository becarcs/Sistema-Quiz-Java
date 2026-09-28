package models;

public class Aluno extends Usuario {
    public Aluno(String nome) {
        super(nome);
    }
    @Override
    public void exibirPerfil() {
        System.out.println("Aluno: " + getNome());
    }
}
