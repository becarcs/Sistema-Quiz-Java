package models;

import enums.Materia;
import enums.TipoQuiz;

import java.util.Scanner;

public class QuizMultiplaEscolha extends Quiz {

    public QuizMultiplaEscolha(
            String titulo,
            Materia materia,
            Professor professor,
            Scanner scanner) {

        super(
            titulo,
            materia,
            TipoQuiz.MULTIPLA_ESCOLHA,
            professor,
            scanner
        );
    }

    @Override
    public void iniciar() {

        validarQuiz();

        pontuacaoAtual = 0;

        for (Pergunta pergunta : getPerguntas()) {

            System.out.println(
                "\n" + pergunta.getEnunciado()
            );

            for (int i = 0;
                 i < pergunta.getAlternativas().size();
                 i++) {

                System.out.println(
                    (i + 1) + " - " +
                    pergunta.getAlternativas().get(i)
                );
            }

            int resposta =
                lerAlternativa(
                    pergunta.getAlternativas().size()
                );

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
