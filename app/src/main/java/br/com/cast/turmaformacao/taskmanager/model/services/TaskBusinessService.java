package br.com.cast.turmaformacao.taskmanager.model.services;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.http.TaskService;
import br.com.cast.turmaformacao.taskmanager.model.persistence.LabelReposiroty;
import br.com.cast.turmaformacao.taskmanager.model.persistence.TaskReposiroty;

public final class TaskBusinessService {


    private TaskBusinessService(){
        super();
    }


    public static List<Task> findAll() {
        List<Task> tasks = TaskReposiroty.getAll();

        for(Task t: tasks){
            t.setLabel(LabelReposiroty.getId(t.getLabel().getId()));
        }
        return tasks;
    }

    public static void  save(Task task){
        TaskReposiroty.save(task);

    }

    public static void delete(Task selectedTask){
        TaskReposiroty.delete(selectedTask.getId());
    }


    public static void synchronize(){
        List<Task> webTasks = TaskService.getTasks();
        for(Task task : webTasks){

            Long id = TaskReposiroty.getIdByWebId(task.get_id());

            task.setId(id == null ? null : id);

            save(task);
    }
    }
}
