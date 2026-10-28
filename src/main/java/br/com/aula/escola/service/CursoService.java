package br.com.aula.escola.service;

import br.com.aula.escola.entity.Curso;
import br.com.aula.escola.entity.Professor;
import br.com.aula.escola.repository.CursoRepository;
import br.com.aula.escola.repository.ProfessorRepository;
import br.com.aula.escola.request.RequestCurso;
import br.com.aula.escola.response.ResponseCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;
    @Autowired
    private ProfessorRepository professorRepository;

    public ResponseCurso save(RequestCurso request) {
        Professor professor = professorRepository.findById(request.professorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));

        Curso curso = new Curso();
        curso.setNome(request.nome());
        curso.setProfessor(professor);

        return new ResponseCurso(repository.save(curso));
    }

    public List<ResponseCurso> findall() {
        return repository.findAll().stream().map(ResponseCurso::new).toList();
    }
}
