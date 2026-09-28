package exceptions;

public class MateriaNaoPermitidaException extends RuntimeException {

    public MateriaNaoPermitidaException(String mensagem) {
        super(mensagem);
    }
}

