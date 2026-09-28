package models;

import enums.Materia;
import enums.TipoQuiz;
import exceptions.QuizInvalidoException;
import interfaces.InterfaceQuiz;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public abstract class Quiz implements InterfaceQuiz {

    private String titulo;
    private Materia materia;
    private TipoQuiz tipo;
    private Professor professor;

    private List<Pergunta> perguntas;
    private List<Resultado> resultados;

    protected Scanner scanner;
    protected int pontuacaoAtual;

    public Quiz(
            String titulo,
            Materia materia,
            TipoQuiz tipo,
            Professor professor,
            Scanner scanner) {

        this.titulo = titulo;
        this.materia = materia;
        this.tipo = tipo;
        this.professor = professor;
        this.scanner = scanner;

        perguntas = new ArrayList<>();
        resultados = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public Materia getMateria() {
        return materia;
    }

    public TipoQuiz getTipo() {
        return tipo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public List<Resultado> getResultados() {
        return resultados;
    }

    public void adicionarPergunta(Pergunta pergunta) {

        if (pergunta == null) {
            throw new QuizInvalidoException(
                "Pergunta invalida."
            );
        }

        perguntas.add(pergunta);
    }

    protected void validarQuiz() {

        if (perguntas.isEmpty()) {
            throw new QuizInvalidoException(
                "O quiz nao possui perguntas."
            );
        }
    }

    protected int lerAlternativa(int limite) {

        while (true) {

            try {

                int resposta =
                    Integer.parseInt(scanner.nextLine());

                if (resposta >= 1 &&
                    resposta <= limite) {

                    return resposta;
                }

                System.out.println(
                    "Escolha uma alternativa valida."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                    "Digite apenas numeros."
                );
            }
        }
    }

    public void adicionarResultado(
            Resultado resultado) {

        resultados.removeIf(
            r -> r.getAluno()
                  .getNome()
                  .equals(resultado.getAluno().getNome())
        );

        resultados.add(resultado);

        resultados.sort(
            Comparator.comparing(
                Resultado::getPontuacao
            ).reversed()
        );
    }

    public void exibirRanking() {

        if (resultados.isEmpty()) {

            System.out.println(
                "Nenhum resultado."
            );

            return;
        }

        for (int i = 0;
             i < resultados.size();
             i++) {

            Resultado resultado =
                resultados.get(i);

            System.out.println(
                (i + 1) + " - " +
                resultado.getAluno().getNome() +
                " | Pontuacao: " +
                resultado.getPontuacao() +
                " | Tentativa: " +
                resultado.getTentativa()
            );
        }
    }
}
