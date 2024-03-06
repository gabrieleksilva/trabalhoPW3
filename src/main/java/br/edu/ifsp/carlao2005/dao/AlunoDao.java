package br.edu.ifsp.carlao2005.dao;

import br.edu.ifsp.carlao2005.model.Aluno;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AlunoDao {
    // EntityManager, que será usado por todos os métodos:
    private EntityManager em;
    // Construtor que já recebe o EntityManager criado:
    public AlunoDao(EntityManager em) {
        this.em = em;
    }
    // Método para gravar um aluno no BD:
    public void cadastrar(Aluno aluno) {
        this.em.persist(aluno);
    }
    public List<Aluno> buscarPorNome(String nome) {
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = :n";
        return em.createQuery(jpql, Aluno.class)
                .setParameter("n", nome)
                .getResultList();
    }

}
