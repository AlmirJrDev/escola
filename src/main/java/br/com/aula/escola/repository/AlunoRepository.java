package br.com.aula.escola.repository;

import br.com.aula.escola.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    boolean existsByEmail(String email);
}
