package br.edu.ifsp.carlao2005.controller;

import br.edu.ifsp.carlao2005.dao.AlunoDao;
import br.edu.ifsp.carlao2005.model.Aluno;
import br.edu.ifsp.carlao2005.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;


public class GerenciarAluno {
    public void insert() {

        Scanner leitorTeclado = new Scanner(System.in);
        System.out.print("Digite o nome: ");
        String nome = leitorTeclado.nextLine();
        System.out.print("Digite o Ra: ");
        String ra = leitorTeclado.nextLine();
        System.out.print("Digite o email: ");
        String email = leitorTeclado.nextLine();
        System.out.print("Digite a nota 1: ");
        String nota1 = leitorTeclado.nextLine();
        System.out.print("Digite a nota 2: ");
        String nota2 = leitorTeclado.nextLine();
        System.out.print("Digite a nota 3: ");
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

    public void findByName(String nome) throws NoResultException {
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);
        Aluno al = alunoDao.buscarPorUnicoNome(nome);
        System.out.println("-----------------------");
        System.out.println(al.getNome());
        System.out.println(al.getEmail());
        System.out.println(al.getRa());
        System.out.println(al.getNota1());
        System.out.println(al.getNota2());
        System.out.println(al.getNota3());
        em.close();
    }

    public void findAlL() {
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);
        List<Aluno> listaAlunos = alunoDao.buscarTodos();
        for (Aluno al : listaAlunos) {
            System.out.println("-----------------------");
            System.out.println("Nome: " + al.getNome());
            System.out.println("Email: " + al.getEmail());
            System.out.println("RA: " + al.getRa());
            System.out.println("Notas: " + al.getNota1() + " - " + al.getNota2() + " - " + al.getNota2());
            System.out.print("Situacao: ");

            BigDecimal media = al.getNota1().add(al.getNota2().add(al.getNota3())).divide(new BigDecimal(3), RoundingMode.DOWN);
            if (media.toBigInteger().floatValue() < 4) {
                System.out.println("reprovado ");
            } else {
                if (media.toBigInteger().floatValue() >= 6) {
                    System.out.println("aprovado ");
                } else {
                    System.out.println("recuperacao ");
                }
            }
        }
    }

    public void deleteByName(String nome) {

        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);
        try {
            alunoDao.buscarPorUnicoNome(nome);
            em.getTransaction().begin();
            alunoDao.deletarAluno(nome);
            // Finalizando a transação:
            em.getTransaction().commit();

            // Fechando o EntityManager
            em.close();
            System.out.println("Aluno removido com sucesso!");
        } catch (NoResultException e) {
            System.out.println("Aluno nao encontrado!");
        }
    }

    public void updateByName(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);
        try {
            findByName(nome);
            Aluno aluno = alunoDao.buscarPorUnicoNome(nome);
            Scanner leitor = new Scanner(System.in);
            System.out.print("Digite o nome: ");
            String name = leitor.nextLine();
            System.out.print("Digite o Ra: ");
            String ra = leitor.nextLine();
            System.out.print("Digite o email: ");
            String email = leitor.nextLine();
            System.out.print("Digite a nota 1: ");
            String nota1 = leitor.nextLine();
            System.out.print("Digite a nota 2: ");
            String nota2 = leitor.nextLine();
            System.out.print("Digite a nota 3: ");
            String nota3 = leitor.nextLine();
            // Iniciando a transação:
            em.getTransaction().begin();
            aluno.setNome(name);
            aluno.setEmail(email);
            aluno.setRa(ra);
            aluno.setNota1(new BigDecimal(nota1));
            aluno.setNota2(new BigDecimal(nota2));
            aluno.setNota3(new BigDecimal(nota3));

            // Finalizando a transação:
            em.getTransaction().commit();

            // Fechando o EntityManager
            em.close();
            System.out.println("Aluno alterado com sucesso!");
        } catch (NoResultException e) {
            System.out.println("Aluno nao encontrado!");
        }
    }
}
