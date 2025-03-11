package br.senai.sp.escolamvc.api;

import br.senai.sp.escolamvc.model.Tarefa;
import br.senai.sp.escolamvc.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaRestController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping("/listar")
    public List<Tarefa> listar(){
        return tarefaRepository.findAll();
    }

    @GetMapping("/listar/{id}")
    public Tarefa listar(@PathVariable Long id){
        return tarefaRepository.findById(id).get();
    }

    @PostMapping("/inserir")
    public void inserir(@RequestBody Tarefa tarefa){
        tarefaRepository.save(tarefa);
    }

    @PutMapping("/alterar/{id}")
    public void alterar(@RequestBody Tarefa tarefa, @PathVariable Long id){
        // Pesquisar a tarefa pelo id
        Tarefa tarefaBanco = tarefaRepository.findById(id).get();

        // Atualizar os dados da tarefa
        if(tarefa.getTitulo() != null)
            tarefaBanco.setTitulo(tarefa.getTitulo());

        if(tarefa.getDescricao() != null)
            tarefaBanco.setDescricao(tarefa.getDescricao());

        if(tarefa.getPrazo() != null)
            tarefaBanco.setPrazo(tarefa.getPrazo());

        // Salvar a tarefa no banco de dados
        tarefaRepository.save(tarefaBanco);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable Long id){
        tarefaRepository.deleteById(id);
    }
}
