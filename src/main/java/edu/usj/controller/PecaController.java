package edu.usj.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import edu.usj.entity.Peca;
import edu.usj.repository.PecaRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PecaController {

    @Autowired
    PecaRepository pecaRepository;

    @GetMapping(value = "/peca")
    public ModelAndView readListaPecas() {
        List<Peca> listaPeca = pecaRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("peca");
        modelAndView.addObject("listaPeca", listaPeca);
        return modelAndView;
    }

    @GetMapping(value = "/detalhesPeca/{id}")
    public ModelAndView readPecaById(@PathVariable Long id) {

        // ler do banco
        Peca peca = pecaRepository.findById(id).get();

        // acrescentar na modelAndView
        ModelAndView modelAndView = new ModelAndView("detalhesPeca");
        modelAndView.addObject("peca", peca);
        return modelAndView;

    }

    @GetMapping(value = "/cadastroPeca")
    public ModelAndView createPeca() {
        Peca peca = new Peca();
        ModelAndView modelAndView = new ModelAndView("cadastroPeca");
        modelAndView.addObject("peca", peca);
        return modelAndView;
    }

    @PostMapping(value = "/adicionarPeca")
    public String adicionarPeca(Peca peca) {

        pecaRepository.save(peca);
        return "redirect:/peca";

        // List<Contato> listaContatos = contatoRepository.findAll();

        // ModelAndView modelAndView = new ModelAndView("index");
        // modelAndView.addObject("listaContatos", listaContatos);
        // return modelAndView;
    }

    @GetMapping(value = "/deletarPeca/{id}")
    public String deletePecaById(@PathVariable Long id) {
        pecaRepository.deleteById(id);
        return "redirect:/peca";
    }

    @GetMapping(value = "/editarPeca/{id}")
    public ModelAndView updatePecaById(@PathVariable Long id) {
        Peca peca = pecaRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("cadastroPeca");
        modelAndView.addObject("peca", peca);
        return modelAndView;
    }

    @GetMapping(value = "/pesquisarPeca")
    public String getSelectPeca() {
        return "pesquisarPeca";
    }

    @PostMapping(value = "/pesquisarPeca")
    public ModelAndView postSelectPeca(@RequestParam String nome) {
        List<Peca> listaPeca = pecaRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);

        ModelAndView modelAndView = new ModelAndView("pesquisarPeca");
        modelAndView.addObject("nome", nome);
        modelAndView.addObject("listaPeca", listaPeca);
        return modelAndView;
    }

    @PostMapping(value = "/error")
    public ModelAndView postError() {

        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }

}
