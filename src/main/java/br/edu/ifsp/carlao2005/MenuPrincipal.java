package br.edu.ifsp.carlao2005;

import br.edu.ifsp.carlao2005.controller.CadastroDeAluno;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MenuPrincipal {
    public static void main(String[] args) {
        SpringApplication.run(MenuPrincipal.class, args);
        Scanner leitorTeclado = new Scanner(System.in);

        int cont = 0;
        do{
            System.out.println("\n ** CADASTRO DE ALUNOS **");
            System.out.println("\n1. Cadastrar aluno");
            System.out.println("2. Excluir aluno");
            System.out.println("3. Alterar aluno");
            System.out.println("4. Buscar aluno pelo nome");
            System.out.println("5. Listar alunos (com status aprovação)");
            System.out.println("6. FIM");
            System.out.println("\n\nDigite um numero: ");
            cont = leitorTeclado.nextInt();
            CadastroDeAluno cadastroDeAluno = new CadastroDeAluno();
            switch(cont){
                case 1:
                    System.out.println("\nEntrou na opcao 1\n");

                    cadastroDeAluno.cadastroAluno();
                    break;
                case 2:
                   // System.out.println("Digite o nome:");
                   // String nome = leitorTeclado.nextLine();


                    break;
                case 3:
                    System.out.println("\nEntrou na opcao 3\n");

                    break;
                case 4:
                    Scanner scan = new Scanner(System.in);
                     System.out.println("Digite o nome");
                     String nome = scan.nextLine();
                    cadastroDeAluno.findByName(nome);
                    break;
                case 5:
                    System.out.println("\nEntrou na opcao 5\n");
                    break;
                case 6:
                    System.out.println("\nSaindo...");
                    break;

                default: System.out.println("Opcao invalida");
            }
        }

        while (cont != 6);
    }
}