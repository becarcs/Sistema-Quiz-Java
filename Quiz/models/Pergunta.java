package models;

import exceptions.QuizInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Pergunta {

    private String enunciado;
    private List<String> alternativas;
    private int respostaCorreta;
    private int pontuacao;

    public Pergunta(
            String enunciado,
            List<String> alternativas,
            int respostaCorreta,
            int pontuacao) {

        validarAlternativas(alternativas);
        validarResposta(respostaCorreta, alternativas);
        validarPontuacao(pontuacao);

        this.enunciado = enunciado;
        this.alternativas = new ArrayList<>(alternativas);
        this.respostaCorreta = respostaCorreta;
        this.pontuacao = pontuacao;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<String> alternativas) {

        validarAlternativas(alternativas);
        validarResposta(respostaCorreta, alternativas);

        this.alternativas =
            new ArrayList<>(alternativas);
    }

    public int getRespostaCorreta() {
        return respostaCorreta;
    }

    public void setRespostaCorreta(int respostaCorreta) {

        validarResposta(
            respostaCorreta,
            alternativas
        );

        this.respostaCorreta = respostaCorreta;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {

        validarPontuacao(pontuacao);

        this.pontuacao = pontuacao;
    }

    private void validarAlternativas(
            List<String> alternativas) {

        if (alternativas == null ||
            alternativas.size() < 2) {

            throw new QuizInvalidoException(
                "A pergunta precisa ter pelo menos 2 alternativas."
            );
        }
    }

    private void validarResposta(
            int resposta,
            List<String> alternativas) {

        if (resposta < 1 ||
            resposta > alternativas.size()) {

            throw new QuizInvalidoException(
                "Resposta correta invalida."
            );
        }
    }

    private void validarPontuacao(int pontuacao) {

        if (pontuacao <= 0) {

            throw new QuizInvalidoException(
                "A pontuacao deve ser maior que zero."
            );
        }
    }
}

