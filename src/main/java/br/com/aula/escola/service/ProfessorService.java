package br.com.aula.escola.service;

import br.com.aula.escola.entity.Professor;
import br.com.aula.escola.repository.ProfessorRepository;
import br.com.aula.escola.request.RequestProfessor;
import br.com.aula.escola.response.ResponseProfessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository repository;

    public ResponseProfessor save(RequestProfessor request) {
        if (repository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }

        Professor professor = new Professor();
        professor.setNome(request.nome());
        professor.setEmail(request.email());
        professor.setDepartamento(request.departamento());

        return new ResponseProfessor(repository.save(professor));
    }

    public List<ResponseProfessor> findall() {
        return repository.findAll().stream().map(ResponseProfessor::new).toList();
    }
}
