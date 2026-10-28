package br.com.aula.escola.controller;

import br.com.aula.escola.request.RequestProfessor;
import br.com.aula.escola.response.ResponseProfessor;
import br.com.aula.escola.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("professor")
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseProfessor cadastroprofessor(@RequestBody RequestProfessor professor) {
        return service.save(professor);
    }

    @GetMapping
    public List<ResponseProfessor> consultaprofessor() {
        return service.findall();
    }
}
