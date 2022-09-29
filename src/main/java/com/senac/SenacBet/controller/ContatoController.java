package com.senac.SenacBet.controller;

import com.senac.SenacBet.dao.ContatoDAO;
import com.senac.SenacBet.model.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.model.IModel;

@Controller
public class ContatoController {

    @Autowired
    ContatoDAO dao;

    @GetMapping("/contato")
    public String abrirContato(Contato contato){
        return "contato";
    }

    @PostMapping("/contato")
    public String processarContato(Contato contato){
        dao.save(contato);
        return "contato";
    }

    @GetMapping("/listar-contatos")
    public String listar(Model model){
        model.addAttribute("contatos", dao.findAll());
        return "lista-contato";
    }

    @GetMapping("/contato/delete/{id}")
    public String apagarContato(@PathVariable("id") Integer id, Model model){
        dao.deleteById(id);
        return "redirect:/listar-contatos";
    }

    @GetMapping("/edit/{id}")
    public String editContato(@PathVariable("id") Integer id, Model model) {
        Contato contato = dao.findById(id).get();
        model.addAttribute("contato" , contato);
        return "edit-contato";
    }

    @PostMapping("/edit-contato/{id}")
    public String atualizarContato(@PathVariable Integer id , Contato contato){
        dao.save(contato);
        return "redirect:/listar-contatos";
    }





}
