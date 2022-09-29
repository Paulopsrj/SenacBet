package com.senac.SenacBet.controller;

import com.senac.SenacBet.dao.PerfilDAO;
import com.senac.SenacBet.model.Contato;
import com.senac.SenacBet.model.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PerfilController {

    @Autowired
    private PerfilDAO dao;

    @GetMapping("/perfil")
    public String abrirTela(Perfil perfil){
        return "perfil";
    }

    @PostMapping("/perfil")
    public String salvarPerfil(Perfil perfil){
        dao.save(perfil);
        return "perfil";
    }

    @GetMapping("/listar-perfil")
    public String listarPerfil(Model model){
        model.addAttribute("perfis", dao.findAll());
        return "lista-perfil";
    }

    @GetMapping("/perfil/delete/{perfil}")
    public String deletePerfil(@PathVariable("perfil") String cpf) {
        dao.deleteById(cpf);
        return "redirect:/listar-perfil";
    }
    @GetMapping("/edit/perfil/{cpf}")
    public String editPerfil(@PathVariable("cpf") String cpf, Model model) {
        Perfil perfil = dao.findById(cpf).get();
        model.addAttribute("perfil" , perfil);
        return "edit-perfil";
    }

    @PostMapping("/edit-perfil/{cpf}")
    public String atualizarPerfil(@PathVariable String cpf, Perfil perfil) {
        dao.save(perfil);
        return "redirect:/listar-perfil";
    }
}
