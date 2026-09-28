package exceptions;

public class QuizInvalidoException extends RuntimeException {
    public QuizInvalidoException(String mensagem) {
        super(mensagem);
    }
}

