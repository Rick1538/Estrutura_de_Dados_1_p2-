import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

//TRABALHO ESTRUTURA DE DADOS 1 P2
//PROF: RICARDO VILAVERDE
// ALUNOS:
//IURY RODRIGUES
//LETICIA REZENDE
//RICARDO RODRIGUES
//PHELIPE VIEIRA


public class SistemaAlunos {
    private static Stack<Aluno> pilhaAlunos = new Stack<>();
    private static Queue<Nota> filaNotas = new LinkedList<>();
    private static int numeroAluno = 1;



    private static void cadastrarAluno(Scanner scanner) {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        Aluno aluno = new Aluno(numeroAluno++, nome);
        pilhaAlunos.push(aluno);   //add um aluno e cria seu id
        System.out.println("Aluno " + nome + " cadastrado com id: " + (numeroAluno - 1) );
    }

    private static void cadastrarNota(Scanner scanner) {
        System.out.print("Digite o número do aluno para cadastrar a nota: ");
        int numeroAluno = scanner.nextInt();
        scanner.nextLine();
        if (!alunoCadastrado(numeroAluno)) {
            System.out.println("Aluno não cadastrado.");
        } else {
            System.out.print("Digite a nota: ");
            double nota = scanner.nextDouble();
            scanner.nextLine();
            filaNotas.add(new Nota(numeroAluno, nota));
            System.out.println("Nota cadastrada.");
        }
    }

    private static void calcularMedia(Scanner scanner) {
        System.out.print("Digite o número do aluno para calcular a média: ");
        int numeroAluno = scanner.nextInt();
        scanner.nextLine();
        Aluno aluno = buscarAluno(numeroAluno);
        if (aluno == null) {
            System.out.println("Aluno não cadastrado.");
        } else {
            ArrayList<Double> notas = obterNotasAluno(numeroAluno);
            if (notas.isEmpty()) {
                System.out.println("Aluno sem notas.");
            } else {
                double soma = 0;
                for (double nota : notas) {
                    soma += nota;
                }
                double media = soma / notas.size();
                System.out.println("Média do aluno " + aluno.nome + " = " + String.format("%.2f", media));
            }
        }
    }

    private static void listarAlunosSemNotas() {
        boolean encontrou = false;
        for (Aluno aluno : pilhaAlunos) {
            if (obterNotasAluno(aluno.numero).isEmpty()) {
                if (!encontrou) {
                    System.out.println("Alunos sem notas cadastradas:");
                    encontrou = true;
                }
                System.out.println(aluno.nome);
            }
        }
        if (!encontrou) {
            System.out.println("Todos os alunos possuem notas.");
        }
    }


    private static void excluirAluno() {
        if (pilhaAlunos.isEmpty()) {
            System.out.println("Pilha vazia.");
        } else {
            Aluno aluno = pilhaAlunos.peek();
            if (obterNotasAluno(aluno.numero).isEmpty()) {
                pilhaAlunos.pop();
                System.out.println("Aluno excluído.");
            } else {
                System.out.println("Este aluno possui notas, logo, não poderá ser excluído.");
            }
        }
    }

    private static void excluirNota() {
        if (filaNotas.isEmpty()) {
            System.out.println("Fila vazia.");
        } else {
            filaNotas.poll();
            System.out.println("Nota excluída.");
        }
    }

    private static boolean alunoCadastrado(int numero) {
        for (Aluno aluno : pilhaAlunos) {
            if (aluno.numero == numero) {
                return true;
            }
        }
        return false;
    }

    private static Aluno buscarAluno(int numero) {
        for (Aluno aluno : pilhaAlunos) {
            if (aluno.numero == numero) {
                return aluno;
            }
        }
        return null;
    }

    private static ArrayList<Double> obterNotasAluno(int numeroAluno) {
        ArrayList<Double> notas = new ArrayList<>();
        for (Nota nota : filaNotas) {
            if (nota.numeroAluno == numeroAluno) {
                notas.add(nota.valor);
            }
        }
        return notas;
    }

    static class Aluno {
        int numero;
        String nome;

        Aluno(int numero, String nome) {
            this.numero = numero;
            this.nome = nome;
        }
    }

    static class Nota {
        int numeroAluno;
        double valor;

        Nota(int numeroAluno, double valor) {
            this.numeroAluno = numeroAluno;
            this.valor = valor;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n-------------------------------------------------------");
            System.out.println("\nMENU");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Cadastrar nota");
            System.out.println("3 - Calcular média de um aluno");
            System.out.println("4 - Listar os nomes dos alunos sem notas");
            System.out.println("5 - Excluir aluno");
            System.out.println("6 - Excluir nota");
            System.out.println("7 - Sair");
            System.out.println("\n-------------------------------------------------------");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAluno(scanner);
                    break;
                case 2:
                    cadastrarNota(scanner);
                    break;
                case 3:
                    calcularMedia(scanner);
                    break;
                case 4:
                    listarAlunosSemNotas();
                    break;
                case 5:
                    excluirAluno();
                    break;
                case 6:
                    excluirNota();
                    break;
                case 7:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);

        scanner.close();
    }
}