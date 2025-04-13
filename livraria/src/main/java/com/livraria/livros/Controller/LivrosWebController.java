package com.livraria.livros.Controller;

import com.livraria.livros.entity.Livros;
import com.livraria.livros.service.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class LivrosWebController {

    @Autowired
    private LivrosService livrosService;

    @GetMapping
    public String listarLivros(Model model) {
        model.addAttribute("livros", livrosService.getAllLivros());
        return "livros/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovoLivro(Model model) {
        model.addAttribute("livros", new Livros());
        return "livros/form";
    }

    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute Livros livros) {
        livrosService.salvarLivro(livros);
        return "redirect:/livros";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarLivro(@PathVariable Long id, Model model) {
        Livros livros = livrosService.obterLivroPorId(id);
        model.addAttribute("livros", livros);
        return "livros/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletarLivro(@PathVariable Long id) {
        livrosService.deletarLivro(id);
        return "redirect:/livros";
    }
}
