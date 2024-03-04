package com.formacionbdi.microservicios.app.cursos.controllers;

import com.formacionbdi.microservicios.app.cursos.models.entity.Curso;
import com.formacionbdi.microservicios.app.cursos.services.CursoService;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id ){
        Optional<Curso> o = this.service.findById(id);
        // si esto retorna false, o si es distinto a .isPresent()
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();
        dbCurso.setNombre(curso.getNombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }
}


