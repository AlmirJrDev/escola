package br.com.aula.escola.service;

import br.com.aula.escola.entity.Aluno;
import br.com.aula.escola.entity.Curso;
import br.com.aula.escola.repository.AlunoRepository;
import br.com.aula.escola.repository.CursoRepository;
import br.com.aula.escola.request.RequestAluno;
import br.com.aula.escola.response.ResponseAluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;
    @Autowired
    private CursoRepository cursoRepository;

    public ResponseAluno save(RequestAluno request) {
        if (repository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        Aluno aluno = new Aluno();
        aluno.setNome(request.nome());
        aluno.setEmail(request.email());
        aluno.setCurso(curso);

        return new ResponseAluno(repository.save(aluno));
    }

    public List<ResponseAluno> findall() {
        return repository.findAll().stream().map(ResponseAluno::new).toList();
    }
}
