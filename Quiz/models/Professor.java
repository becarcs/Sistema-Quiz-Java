package models;

import enums.Materia;
import exceptions.MateriaNaoPermitidaException;

public class Professor extends Usuario {

    private Materia materia;

    public Professor(String nome, Materia materia) {
        super(nome);
        this.materia = materia;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public void validarMateria(Materia materiaQuiz) {

        if (materiaQuiz != materia) {
            throw new MateriaNaoPermitidaException(
                "O professor nao pode criar quiz dessa materia."
            );
        }
    }

    @Override
    public void exibirPerfil() {

        System.out.println(
            "Professor: " + getNome()
        );

        System.out.println(
            "Materia: " + materia
        );
    }
}

