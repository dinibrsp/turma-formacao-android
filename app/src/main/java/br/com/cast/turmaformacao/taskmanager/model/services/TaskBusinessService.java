package br.com.cast.turmaformacao.taskmanager.model.services;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.persistence.TaskReposiroty;

public final class TaskBusinessService {


    private TaskBusinessService(){
        super();
    }


    public static List<Task> findAll() {
        return TaskReposiroty.getAll();
    }

    public static void  save(Task task){
        TaskReposiroty.save(task);

    }

    public static void delete(Task selectedTask){
        TaskReposiroty.delete(selectedTask.getId());
    }
}
