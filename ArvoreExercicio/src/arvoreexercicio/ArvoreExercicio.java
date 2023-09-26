package ArvoreExercicio;

import java.util.Scanner;

class No {
    int dado;
    No esquerda;
    No direita;

    public No(int dado) {
        this.dado = dado;
        esquerda = null;
        direita = null;
    }
}

class ArvoreBinariaBusca {
    No raiz;

    public ArvoreBinariaBusca() {
        raiz = null;
    }

    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private No inserirRec(No raiz, int valor) {
        if (raiz == null) {
            raiz = new No(valor);
            return raiz;
        }

        if (valor < raiz.dado) {
            raiz.esquerda = inserirRec(raiz.esquerda, valor);
        } else if (valor > raiz.dado) {
            raiz.direita = inserirRec(raiz.direita, valor);
        }

        return raiz;
    }

    public void percorrerPreOrdem(No no) {
        if (no != null) {
            System.out.print(no.dado + " ");
            percorrerPreOrdem(no.esquerda);
            percorrerPreOrdem(no.direita);
        }
    }

    public void percorrerEmOrdem(No no) {
        if (no != null) {
            percorrerEmOrdem(no.esquerda);
            System.out.print(no.dado + " ");
            percorrerEmOrdem(no.direita);
        }
    }

    public void percorrerPosOrdem(No no) {
        if (no != null) {
            percorrerPosOrdem(no.esquerda);
            percorrerPosOrdem(no.direita);
            System.out.print(no.dado + " ");
        }
    }

    public void removerMaximo() {
        raiz = removerMaximoRec(raiz);
    }

    private No removerMaximoRec(No raiz) {
        if (raiz == null) {
            return null;
        }

        if (raiz.direita == null) {
            return raiz.esquerda;
        }

        raiz.direita = removerMaximoRec(raiz.direita);

        return raiz;
    }

    public void removerMinimo() {
        raiz = removerMinimoRec(raiz);
    }

    private No removerMinimoRec(No raiz) {
        if (raiz == null) {
            return null;
        }

        if (raiz.esquerda == null) {
            return raiz.direita;
        }

        raiz.esquerda = removerMinimoRec(raiz.esquerda);

        return raiz;
    }

    public void remover(int valor) {
        raiz = removerRec(raiz, valor);
    }

    private No removerRec(No raiz, int valor) {
        if (raiz == null) {
            return raiz;
        }

        if (valor < raiz.dado) {
            raiz.esquerda = removerRec(raiz.esquerda, valor);
        } else if (valor > raiz.dado) {
            raiz.direita = removerRec(raiz.direita, valor);
        } else {
            if (raiz.esquerda == null) {
                return raiz.direita;
            } else if (raiz.direita == null) {
                return raiz.esquerda;
            }

            raiz.dado = encontrarMinimo(raiz.direita);

            raiz.direita = removerRec(raiz.direita, raiz.dado);
        }

        return raiz;
    }

    private int encontrarMinimo(No raiz) {
        int minimo = raiz.dado;
        while (raiz.esquerda != null) {
            minimo = raiz.esquerda.dado;
            raiz = raiz.esquerda;
        }
        return minimo;
    }
}

public class ArvoreExercicio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

        while (true) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1 - Inserir elemento");
            System.out.println("2 - Percorrer pré-ordem");
            System.out.println("3 - Percorrer em ordem");
            System.out.println("4 - Percorrer pós-ordem");
            System.out.println("5 - Remover maior elemento");
            System.out.println("6 - Remover menor elemento");
            System.out.println("7 - Remover elemento específico");
            System.out.println("0 - Sair");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 0:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Digite o valor a ser inserido:");
                    int valorInserir = scanner.nextInt();
                    arvore.inserir(valorInserir);
                    break;
                case 2:
                    System.out.println("Percorrendo pré-ordem:");
                    arvore.percorrerPreOrdem(arvore.raiz);
                    break;
                case 3:
                    System.out.println("Percorrendo em ordem:");
                    arvore.percorrerEmOrdem(arvore.raiz);
                    break;
                case 4:
                    System.out.println("Percorrendo pós-ordem:");
                    arvore.percorrerPosOrdem(arvore.raiz);
                    break;
                case 5:
                    arvore.removerMaximo();
                    System.out.println("Maior elemento removido.");
                    break;
                case 6:
                    arvore.removerMinimo();
                    System.out.println("Menor elemento removido.");
                    break;
                case 7:
                    System.out.println("Digite o valor a ser removido:");
                    int valorRemover = scanner.nextInt();
                    arvore.remover(valorRemover);
                    System.out.println("Elemento removido.");
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    break;
            }
        }
    }
}
