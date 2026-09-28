import enums.Materia;
import exceptions.MateriaNaoPermitidaException;
import exceptions.QuizInvalidoException;
import models.Aluno;
import models.Pergunta;
import models.Professor;
import models.Quiz;
import models.QuizMultiplaEscolha;
import models.QuizVerdadeiroFalso;
import models.Resultado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static List<Professor> professores = new ArrayList<>();
    static List<Aluno> alunos = new ArrayList<>();
    static List<Quiz> quizzes = new ArrayList<>();

    public static void main(String[] args) {

        cadastrarDadosIniciais();
        menuPrincipal();
    }

    public static void cadastrarDadosIniciais() {

        Professor professor =
            new Professor("Marcelo Henrique", Materia.PROGRAMACAO);

        Aluno aluno = new Aluno("Maria");

        professores.add(professor);
        alunos.add(aluno);

        Quiz quiz = new QuizMultiplaEscolha(
            "Fundamentos de Java",
            Materia.PROGRAMACAO,
            professor,
            scanner
        );

        quiz.adicionarPergunta(
            new Pergunta(
                "Qual linguagem esta sendo usada neste projeto?",
                Arrays.asList("Python", "Java", "C++", "C#"),
                2,
                30
            )
        );

        quiz.adicionarPergunta(
            new Pergunta(
                "Qual palavra-chave representa heranca em Java?",
                Arrays.asList("implements", "extends", "inherit", "superclass"),
                2,
                30
            )
        );

        quiz.adicionarPergunta(
            new Pergunta(
                "Qual estrutura pode armazenar varios objetos?",
                Arrays.asList("ArrayList", "Scanner", "String", "Enum"),
                1,
                40
            )
        );

        quizzes.add(quiz);
    }

    public static void menuPrincipal() {

        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE QUIZ =====");
            System.out.println("1 - Menu Professor");
            System.out.println("2 - Menu Aluno");
            System.out.println("3 - Listar Quizzes");
            System.out.println("4 - Ver Rankings");
            System.out.println("0 - Sair");

            opcao = lerInteiro("Escolha: ");

            switch (opcao) {

                case 1:
                    menuProfessor();
                    break;

                case 2:
                    menuAluno();
                    break;

                case 3:
                    listarQuizzes();
                    break;

                case 4:
                    listarRankings();
                    break;

                case 0:
                    System.out.println("Programa encerrado.");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    public static void menuProfessor() {

        Professor professor = escolherProfessor();

        if (professor == null) {
            return;
        }

        int opcao;

        do {
            System.out.println("\n===== MENU PROFESSOR =====");
            System.out.println("Professor: " + professor.getNome());
            System.out.println("Materia: " + professor.getMateria());

            System.out.println("\n1 - Criar Quiz");
            System.out.println("2 - Listar meus Quizzes");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Escolha: ");

            switch (opcao) {

                case 1:
                    criarQuiz(professor);
                    break;

                case 2:
                    listarQuizzesProfessor(professor);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    public static void menuAluno() {

        Aluno aluno = escolherAluno();

        if (aluno == null) {
            return;
        }

        int opcao;

        do {
            System.out.println("\n===== MENU ALUNO =====");
            System.out.println("Aluno: " + aluno.getNome());

            System.out.println("\n1 - Listar Quizzes");
            System.out.println("2 - Fazer Quiz");
            System.out.println("3 - Ver Rankings");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Escolha: ");

            switch (opcao) {

                case 1:
                    listarQuizzes();
                    break;

                case 2:
                    jogarQuiz(aluno);
                    break;

                case 3:
                    listarRankings();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    public static Professor escolherProfessor() {

        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
            return null;
        }

        System.out.println("\n===== PROFESSORES =====");

        for (int i = 0; i < professores.size(); i++) {
            System.out.println(
                (i + 1) + " - " +
                professores.get(i).getNome()
            );
        }

        int opcao = lerInteiro("Escolha o professor: ");

        if (opcao < 1 || opcao > professores.size()) {
            System.out.println("Professor nao encontrado.");
            return null;
        }

        return professores.get(opcao - 1);
    }

    public static Aluno escolherAluno() {

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return null;
        }

        System.out.println("\n===== ALUNOS =====");

        for (int i = 0; i < alunos.size(); i++) {
            System.out.println(
                (i + 1) + " - " +
                alunos.get(i).getNome()
            );
        }

        int opcao = lerInteiro("Escolha o aluno: ");

        if (opcao < 1 || opcao > alunos.size()) {
            System.out.println("Aluno nao encontrado.");
            return null;
        }

        return alunos.get(opcao - 1);
    }

    public static void criarQuiz(Professor professor) {

        try {
            System.out.println("\n===== CRIAR QUIZ =====");

            String titulo = lerTexto("Titulo do quiz: ");

            System.out.println("\n1 - Multipla Escolha");
            System.out.println("2 - Verdadeiro ou Falso");

            int tipo = lerInteiro("Tipo: ");

            if (tipo != 1 && tipo != 2) {
                throw new QuizInvalidoException(
                    "Tipo de quiz invalido."
                );
            }

            System.out.println("\nMaterias:");

            Materia[] materias = Materia.values();

            for (int i = 0; i < materias.length; i++) {
                System.out.println(
                    (i + 1) + " - " + materias[i]
                );
            }

            int opcaoMateria =
                lerInteiro("Escolha a materia: ");

            if (opcaoMateria < 1 ||
                opcaoMateria > materias.length) {

                throw new QuizInvalidoException(
                    "Materia invalida."
                );
            }

            Materia materia = materias[opcaoMateria - 1];

            professor.validarMateria(materia);

            Quiz quiz;

            if (tipo == 1) {
                quiz = new QuizMultiplaEscolha(
                    titulo,
                    materia,
                    professor,
                    scanner
                );
            } else {
                quiz = new QuizVerdadeiroFalso(
                    titulo,
                    materia,
                    professor,
                    scanner
                );
            }

            int quantidade =
                lerInteiro("Quantidade de perguntas: ");

            if (quantidade <= 0) {
                throw new QuizInvalidoException(
                    "O quiz precisa ter pelo menos uma pergunta."
                );
            }

            for (int i = 0; i < quantidade; i++) {

                System.out.println(
                    "\n===== PERGUNTA " + (i + 1) + " ====="
                );

                String enunciado =
                    lerTexto("Enunciado: ");

                List<String> alternativas =
                    new ArrayList<>();

                if (tipo == 2) {

                    alternativas.add("Verdadeiro");
                    alternativas.add("Falso");

                } else {

                    int quantidadeAlternativas =
                        lerInteiro("Quantidade de alternativas: ");

                    if (quantidadeAlternativas < 2) {
                        throw new QuizInvalidoException(
                            "A pergunta precisa ter pelo menos 2 alternativas."
                        );
                    }

                    for (int j = 0;
                         j < quantidadeAlternativas;
                         j++) {

                        alternativas.add(
                            lerTexto(
                                "Alternativa " + (j + 1) + ": "
                            )
                        );
                    }
                }

                int respostaCorreta =
                    lerInteiro("Numero da resposta correta: ");

                int pontuacao =
                    lerInteiro("Pontuacao da pergunta: ");

                Pergunta pergunta = new Pergunta(
                    enunciado,
                    alternativas,
                    respostaCorreta,
                    pontuacao
                );

                quiz.adicionarPergunta(pergunta);
            }

            quizzes.add(quiz);

            System.out.println(
                "\nQuiz criado com sucesso!"
            );

        } catch (QuizInvalidoException |
                 MateriaNaoPermitidaException e) {

            System.out.println(
                "\nErro: " + e.getMessage()
            );
        }
    }

    public static void jogarQuiz(Aluno aluno) {

        if (quizzes.isEmpty()) {
            System.out.println("Nenhum quiz cadastrado.");
            return;
        }

        listarQuizzes();

        int opcao = lerInteiro(
            "Escolha o quiz: "
        );

        if (opcao < 1 || opcao > quizzes.size()) {
            System.out.println("Quiz nao encontrado.");
            return;
        }

        Quiz quiz = quizzes.get(opcao - 1);

        int tentativa = 1;

        for (Resultado resultado : quiz.getResultados()) {

            if (resultado.getAluno()
                    .getNome()
                    .equals(aluno.getNome())) {

                tentativa =
                    resultado.getTentativa() + 1;
            }
        }

        quiz.iniciar();

        int pontuacao =
            quiz.calcularPontuacao();

        Resultado resultado =
            new Resultado(
                aluno,
                pontuacao,
                tentativa
            );

        quiz.adicionarResultado(resultado);

        System.out.println(
            "\nResultado salvo!"
        );
    }

    public static void listarQuizzes() {

        if (quizzes.isEmpty()) {
            System.out.println("Nenhum quiz cadastrado.");
            return;
        }

        System.out.println("\n===== QUIZZES =====");

        for (int i = 0; i < quizzes.size(); i++) {

            Quiz quiz = quizzes.get(i);

            System.out.println(
                (i + 1) + " - " +
                quiz.getTitulo()
            );

            System.out.println(
                "Materia: " +
                quiz.getMateria()
            );

            System.out.println(
                "Tipo: " +
                quiz.getTipo()
            );

            System.out.println(
                "Professor: " +
                quiz.getProfessor().getNome()
            );

            System.out.println();
        }
    }

    public static void listarQuizzesProfessor(
            Professor professor) {

        boolean encontrou = false;

        System.out.println(
            "\n===== MEUS QUIZZES ====="
        );

        for (Quiz quiz : quizzes) {

            if (quiz.getProfessor() == professor) {

                encontrou = true;

                System.out.println(
                    "- " + quiz.getTitulo() +
                    " | " + quiz.getMateria()
                );
            }
        }

        if (!encontrou) {
            System.out.println(
                "Voce ainda nao criou nenhum quiz."
            );
        }
    }

    public static void listarRankings() {

        if (quizzes.isEmpty()) {
            System.out.println("Nenhum quiz cadastrado.");
            return;
        }

        System.out.println("\n===== RANKINGS =====");

        for (Quiz quiz : quizzes) {

            System.out.println(
                "\nQuiz: " + quiz.getTitulo()
            );

            quiz.exibirRanking();
        }
    }

    public static int lerInteiro(String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                return Integer.parseInt(
                    scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                    "Digite apenas numeros."
                );
            }
        }
    }

    public static String lerTexto(String mensagem) {

        System.out.print(mensagem);

        return scanner.nextLine();
    }
}

