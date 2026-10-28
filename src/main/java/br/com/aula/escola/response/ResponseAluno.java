package br.com.aula.escola.response;

import br.com.aula.escola.entity.Aluno;

public record ResponseAluno(
        Integer id,
        String nome,
        String email,
        Integer cursoId,
        String cursoNome
) {
    public ResponseAluno(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getEmail(),
                aluno.getCurso() != null ? aluno.getCurso().getId() : null,
                aluno.getCurso() != null ? aluno.getCurso().getNome() : null);
    }
}
