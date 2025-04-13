package com.livraria.livros.Controller;

import com.livraria.livros.entity.Livros;
import com.livraria.livros.service.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivrosRestController {

    @Autowired
    private LivrosService livrosService;

    @GetMapping
    public ResponseEntity<List<Livros>> listarLivros() {
        List<Livros> livros = livrosService.getAllLivros();
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livros> obterLivro(@PathVariable Long id) {
        Livros livros = livrosService.obterLivroPorId(id);
        if (livros != null) {
            return new ResponseEntity<>(livros, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Livros> criarLivro(@RequestBody Livros livros) {
        Livros novoLivro = livrosService.salvarLivro(livros);
        return new ResponseEntity<>(novoLivro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livros> atualizarLivro(@PathVariable Long id, @RequestBody Livros livrosAtualizado) {
        Livros livroExistente = livrosService.obterLivroPorId(id);
        if (livroExistente != null) {
            livrosAtualizado.setId(id);
            Livros livrosAtualizadoSalvo = livrosService.salvarLivro(livrosAtualizado);
            return new ResponseEntity<>(livrosAtualizadoSalvo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        Livros livros = livrosService.obterLivroPorId(id);
        if (livros != null) {
            livrosService.deletarLivro(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
