package models;

public class Resultado {

    private Aluno aluno;
    private int pontuacao;
    private int tentativa;

    public Resultado(
            Aluno aluno,
            int pontuacao,
            int tentativa) {

        this.aluno = aluno;
        this.pontuacao = pontuacao;
        this.tentativa = tentativa;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public int getTentativa() {
        return tentativa;
    }
}

