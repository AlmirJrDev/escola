package br.com.aula.escola.controller;

import br.com.aula.escola.request.RequestAluno;
import br.com.aula.escola.response.ResponseAluno;
import br.com.aula.escola.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("aluno")
public class AlunoController {
    @Autowired
    private AlunoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseAluno cadastroaluno(@RequestBody RequestAluno aluno) {
        return service.save(aluno);
    }

    @GetMapping
    public List<ResponseAluno> consultaaluno() {
        return service.findall();
    }
}
