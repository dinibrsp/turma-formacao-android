package br.com.cast.turmaformacao.taskmanager.model.services;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.persistence.LabelReposiroty;
import br.com.cast.turmaformacao.taskmanager.model.persistence.TaskReposiroty;

public final class LabelBusinessService {


    private LabelBusinessService(){
        super();
    }


    public static List<Label> findAll() {
        return LabelReposiroty.getAll();
    }

    public static void  save(Label label){
        LabelReposiroty.save(label);

    }

    public static void delete(Label selectedLabel){
        LabelReposiroty.delete(selectedLabel.getId());
    }
}
