package br.edu.ifsp.carlao2005;

import br.edu.ifsp.carlao2005.controller.GerenciarAluno;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MenuPrincipal {
    public static void main(String[] args) {
        SpringApplication.run(MenuPrincipal.class, args);
        Scanner leitorTeclado = new Scanner(System.in);

        int cont = 0;
        do {
            System.out.println("\n ** CADASTRO DE ALUNOS **");
            System.out.println("\n1. Cadastrar aluno");
            System.out.println("2. Excluir aluno");
            System.out.println("3. Alterar aluno");
            System.out.println("4. Buscar aluno pelo nome");
            System.out.println("5. Listar alunos (com status aprovação)");
            System.out.println("6. FIM");
            System.out.println("\n\nDigite um numero: ");
            cont = leitorTeclado.nextInt();
            GerenciarAluno gerenciarAluno = new GerenciarAluno();
            switch (cont) {
                case 1:
                    System.out.println("CADASTRO DE ALUNO:\n");
                    gerenciarAluno.insert();
                    break;
                case 2:
                    System.out.println("EXCLUIR ALUNO:\n");
                    Scanner scanExc = new Scanner(System.in);
                    System.out.println("Digite o nome para excluir");
                    String excluir = scanExc.nextLine();
                    gerenciarAluno.deleteByName(excluir);

                    break;
                case 3:
                    System.out.println("ALTERAR ALUNO:\n");
                    Scanner scanAluno = new Scanner(System.in);
                    System.out.println("Digite o nome");
                    String aluno = scanAluno.nextLine();
                    gerenciarAluno.updateByName(aluno);
                    break;
                case 4:
                    System.out.println("CONSULTAR ALUNO:\n");
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Digite o nome");
                    String nome = scan.nextLine();
                    gerenciarAluno.findByName(nome);
                    break;
                case 5:
                    System.out.println("\nEntrou na opcao 5\n");
                    gerenciarAluno.findAlL();
                    break;
                case 6:
                    System.out.println("\nSaindo...");
                    break;

                default:
                    System.out.println("Opcao invalida");
            }
        }

        while (cont != 6);
    }
}