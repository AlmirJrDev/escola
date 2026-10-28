package br.com.aula.escola.controller;

import br.com.aula.escola.request.RequestCurso;
import br.com.aula.escola.response.ResponseCurso;
import br.com.aula.escola.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("curso")
public class CursoController {
    @Autowired
    private CursoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCurso cadastrocurso(@RequestBody RequestCurso curso) {
        return service.save(curso);
    }

    @GetMapping
    public List<ResponseCurso> consultacurso() {
        return service.findall();
    }
}
