package br.com.aula.escola.response;

import br.com.aula.escola.entity.Professor;

public record ResponseProfessor(
        Integer id,
        String nome,
        String email,
        String departamento
) {
    public ResponseProfessor(Professor professor) {
        this(professor.getId(), professor.getNome(), professor.getEmail(), professor.getDepartamento());
    }
}
