package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bean.Filme;
import dao.CategoriaDao;
import dao.DataSource;
import dao.FilmeDao;
import dao.GeneroDao;



@Controller
public class FilmesController {
	
	private DataSource dataSource = new DataSource();
	private FilmeDao filmeDao = new FilmeDao(dataSource);  
	private GeneroDao generoDao = new GeneroDao(dataSource);
	private CategoriaDao categoriaDao = new CategoriaDao(dataSource);
	
	

    // Recebe o modelo, adiciona um atributo a ele e o retorno redireciona para o JSP
    @RequestMapping(value = "filmes", method = RequestMethod.GET)
	public String lista(Model model) {
	    
    	model.addAttribute("filmes", filmeDao.selectAll());
	    return "filme/lista";
    }
    
    

    @RequestMapping("formularioFilme")
    public String form(/*Filme filme,*/ Model model) {   	
    	
    	// N�o pode receber um objeto pq impede a visualiza��o da validacao do Insert/Update
    	model.addAttribute("generos", generoDao.selectAll());
    	model.addAttribute("categorias", categoriaDao.selectAll());
        return "filme/cadastro";
    }

    
    
    // M�todo instancia e seta o objeto Filme a partir dos campos de mesmo nome dos atributos da Classe
    @RequestMapping(value = {"/insertFilme"}, method = RequestMethod.POST)
    public String adiciona(@Valid Filme filme , BindingResult result) {
    		
    	// Verifica algum erro geral
    	if(result.hasErrors()) {	
            return "forward:formularioFilme";
        }

        filmeDao.insert(filme);
        return "redirect:filmes";
    }
    
    
    
    @RequestMapping("editFilme")
    public String edit(Filme filme, Model model) {
    	    
    	// Recebe o id do filme, consulta ele no BD, add ao atributo do Model e repassa para o formul�rio de cadastro/edicao
    	model.addAttribute("filme", filmeDao.select(filme.getId()));
        return "forward:formularioFilme";
    }
    
    
    @RequestMapping("updateFilme")
    public String update(@Valid Filme filme , BindingResult result) {
    	
    	// Verifica algum erro geral
    	if(result.hasErrors()) {
            return "forward:formularioFilme";
        }
    	
		filmeDao.update(filme);
		return "redirect:filmes";
    }
    
    
    @RequestMapping(value="deleteFilme", method=RequestMethod.GET)
    public String remove(Filme filme) {
        filmeDao.delete(filme.getId());
        return "redirect:filmes";
    }
    
}