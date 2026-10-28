package br.com.aula.escola.repository;

import br.com.aula.escola.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    boolean existsByEmail(String email);
}
