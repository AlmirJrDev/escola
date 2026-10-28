package br.com.aula.escola.response;

import br.com.aula.escola.entity.Curso;

public record ResponseCurso(
        Integer id,
        String nome,
        Integer professorId,
        String professorNome
) {
    public ResponseCurso(Curso curso) {
        this(curso.getId(), curso.getNome(),
                curso.getProfessor() != null ? curso.getProfessor().getId() : null,
                curso.getProfessor() != null ? curso.getProfessor().getNome() : null);
    }
}
