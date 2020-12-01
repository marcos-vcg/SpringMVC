package controller;

import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bean.Filme;
import dao.CategoriaDao;
import dao.DataSource;
import dao.FilmeDao;
import dao.GeneroDao;


@MultipartConfig
@Controller
public class FilmesController {
	
	private DataSource dataSource = new DataSource();
	private FilmeDao filmeDao = new FilmeDao(dataSource);  
	private GeneroDao generoDao = new GeneroDao(dataSource);
	private CategoriaDao categoriaDao = new CategoriaDao(dataSource);
	
	


    @RequestMapping("novoFilme")
    public String form(Model model) {
    	model.addAttribute("generos", generoDao.selectAll());
    	model.addAttribute("categorias", categoriaDao.selectAll());
        return "filme/cadastro";
    }

    
    // Método instancia e seta o objeto a partir dos campos de mesmo nome dos atributos da Classe
    @RequestMapping("insertFilme")
    public String adiciona(/*@Valid*/Filme filme /*, BindingResult result, RedirectAttributes atributes */) {
    	
    	/*
    	// Verifica algum erro geral
    	if(result.hasErrors()) {
    		atributes.addFlashAttribute("mensagem", "Verifique os Campos!");
            return "filme/cadastro";
        }*/

        filmeDao.insert(filme);
        //atributes.addFlashAttribute("mensagem", "Filme Inserido!");
        return "forward:filmes";
    }
    
    
    // Recebe o modelo, adiciona um atributo a ele e o retorno redireciona para o JSP
    @RequestMapping("filmes")
	public String lista(Model model) {
	    model.addAttribute("filmes", filmeDao.selectAll());
	    return "filme/lista";
    }
    
    
    @RequestMapping("editFilme")
    public String edit(Filme filme, Model model) {
    	model.addAttribute("filme", filmeDao.select(filme.getId()));
    	model.addAttribute("generos", generoDao.selectAll());
    	model.addAttribute("categorias", categoriaDao.selectAll());
        return "filme/cadastro";
    }
    
    
    @RequestMapping("deleteFilme")
    public String remove(Filme filme) {
        filmeDao.delete(filme.getId());
        return "forward:filmes";
    }
    
    
    @RequestMapping("updateFilme")
    public String update(Filme filme) {
      filmeDao.update(filme);
      return "redirect:filmes";
    }
}