package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TarefasController {

    @RequestMapping("novaTarefa")
    public String form() {
        return "tarefa/formulario";
    }

    
    // Método instancia e seta o objeto a partir dos campos de mesmo nome dos atributos da Classe
    @RequestMapping("adicionaTarefa")
    public String adiciona(@Valid Tarefa tarefa, BindingResult result) {
    	
    	if(result.hasErrors()) {
            return "tarefa/formulario";
        }
    	
        //JdbcTarefaDao dao = new JdbcTarefaDao();
        //dao.adiciona(tarefa);
        return "tarefa/adicionada";
    }
}