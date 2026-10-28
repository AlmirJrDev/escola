package br.com.aula.escola.request;

public record RequestAluno(
        String nome,
        String email,
        Integer cursoId
) {
}
