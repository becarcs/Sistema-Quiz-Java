package models;

import enums.Materia;
import enums.TipoQuiz;
import exceptions.QuizInvalidoException;

import java.util.Scanner;

public class QuizVerdadeiroFalso extends Quiz {

    public QuizVerdadeiroFalso(
            String titulo,
            Materia materia,
            Professor professor,
            Scanner scanner) {

        super(
            titulo,
            materia,
            TipoQuiz.VERDADEIRO_FALSO,
            professor,
            scanner
        );
    }

    @Override
    public void adicionarPergunta(Pergunta pergunta) {

        if (pergunta == null) {

            throw new QuizInvalidoException(
                "Pergunta invalida."
            );
        }

        if (pergunta.getAlternativas() == null ||
            pergunta.getAlternativas().size() != 2) {

            throw new QuizInvalidoException(
                "Quiz verdadeiro ou falso precisa ter 2 alternativas."
            );
        }

        if (!pergunta.getAlternativas()
                .get(0)
                .equalsIgnoreCase("Verdadeiro")
            ||
            !pergunta.getAlternativas()
                .get(1)
                .equalsIgnoreCase("Falso")) {

            throw new QuizInvalidoException(
                "As alternativas devem ser Verdadeiro e Falso."
            );
        }

        super.adicionarPergunta(pergunta);
    }

    @Override
    public void iniciar() {

        validarQuiz();

        pontuacaoAtual = 0;

        for (Pergunta pergunta : getPerguntas()) {

            System.out.println(
                "\n" + pergunta.getEnunciado()
            );

            System.out.println(
                "1 - Verdadeiro"
            );

            System.out.println(
                "2 - Falso"
            );

            int resposta = lerAlternativa(2);

            if (resposta ==
                pergunta.getRespostaCorreta()) {

                pontuacaoAtual +=
                    pergunta.getPontuacao();

                System.out.println(
                    "Resposta correta!"
                );

            } else {

                System.out.println(
                    "Resposta incorreta."
                );
            }
        }

        System.out.println(
            "\nPontuacao: " + pontuacaoAtual
        );
    }

    @Override
    public int calcularPontuacao() {
        return pontuacaoAtual;
    }
}
