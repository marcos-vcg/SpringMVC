package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	


    @RequestMapping("novoFilme")
    public String form(Model model) {
    	model.addAttribute("generos", generoDao.selectAll());
    	model.addAttribute("categorias", categoriaDao.selectAll());
        return "filme/cadastro";
    }

    
    // Método instancia e seta o objeto a partir dos campos de mesmo nome dos atributos da Classe
    @RequestMapping("adicionaFilme")
    public String adiciona(@Valid Filme filme, BindingResult result) {
    	
    	/*
    	// Verifica se tem algum erro com o campo descricao
    	if(result.hasFieldErrors("descricao")) {
    		return "filme/cadastro";
    	}*/
    	
    	// Verifica algum erro geral
    	if(result.hasErrors()) {
            return "filme/cadastro";
        }
    	

        filmeDao.insert(filme);
        return "filme/lista";
    }
    
    
    // Recebe o modelo, adiciona um atributo a ele e o retorno redireciona para o JSP
    @RequestMapping("listaFilmes")
	public String lista(Model model) {
	    model.addAttribute("filmes", filmeDao.selectAll());
	    return "filme/lista";
    }
    
    
    /*
    // Instancia um Objeto ModelAndView passando o caminho do JSP, adiciona um objeto a ele e retorna esse ModelAndView
    @RequestMapping("listaTarefas")
    public ModelAndView lista() {
        JdbcTarefaDao dao = new JdbcTarefaDao();
        List<Tarefa> tarefas = dao.lista();

        ModelAndView mv = new ModelAndView("tarefa/lista");
        mv.addObject("tarefas", tarefas);
        return mv;
    } */
    
}