package com.livraria.livros.service;

import com.livraria.livros.entity.Livros;
import com.livraria.livros.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {

    @Autowired
    private LivrosRepository livrosRepository;

    public List<Livros> getAllLivros() {
        return livrosRepository.findAll();
    }

    public Livros salvarLivro(Livros livros) {
        return livrosRepository.save(livros);
    }

    public void deletarLivro(Long id) {
        livrosRepository.deleteById(id);
    }

    public Livros obterLivroPorId(Long id) {
        Optional<Livros> livrosOptional = livrosRepository.findById(id);
        return livrosOptional.orElse(null);
    }
}
