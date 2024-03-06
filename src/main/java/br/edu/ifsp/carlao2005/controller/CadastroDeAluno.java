package br.edu.ifsp.carlao2005.controller;

import br.edu.ifsp.carlao2005.dao.AlunoDao;
import br.edu.ifsp.carlao2005.model.Aluno;
import br.edu.ifsp.carlao2005.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


public class CadastroDeAluno {
    public void cadastroAluno() {

        Scanner leitorTeclado = new Scanner(System.in);
        System.out.printf("Digite o nome: ");
        String nome = leitorTeclado.nextLine();
        System.out.printf("Digite o Ra: ");
        String ra = leitorTeclado.nextLine();
        System.out.printf("Digite o email: ");
        String email = leitorTeclado.nextLine();
        System.out.printf("Digite a nota 1: ");
        String nota1 = leitorTeclado.nextLine();
        System.out.printf("Digite a nota 2: ");
        String nota2 = leitorTeclado.nextLine();
        System.out.printf("Digite a nota 3: ");
        String nota3 = leitorTeclado.nextLine();

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setRa(ra);
        aluno.setNota1(new BigDecimal(nota1));
        aluno.setNota2(new BigDecimal(nota2));
        aluno.setNota3(new BigDecimal(nota3));
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);

        // Iniciando a transação:
        em.getTransaction().begin();

        // Mudando para o estado managed:
        alunoDao.cadastrar(aluno);

        // Finalizando a transação:
        em.getTransaction().commit();

        // Fechando o EntityManager
        em.close();

    }
    public void findByName(String nome){
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);
        List<Aluno> listaAlunos = alunoDao.buscarPorNome(nome);
        for(Aluno al : listaAlunos) {
            System.out.println("-----------------------");
            System.out.println(al.getNome());
            System.out.println(al.getEmail());
            System.out.println(al.getRa());
            System.out.println(al.getNota1());
            System.out.println(al.getNota2());
            System.out.println(al.getNota3());
        }
    }
}
