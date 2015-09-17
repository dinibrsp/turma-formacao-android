package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

public final class TaskReposiroty {

    private TaskReposiroty(){
        super();
    }

    public static void save(Task task){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = TaskContract.getContentValues(task);
        if(task.getId() == null) {
            db.insert(TaskContract.TABLE, null, values);
        }else{
            String where = TaskContract.ID + " = ? ";
            String[] params = {task.getId().toString()};
            db.update(TaskContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }

    public static List<Task> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(TaskContract.TABLE, TaskContract.COLUNS, null, null, null, null, TaskContract.ID);
        List<Task> values = TaskContract.getTasks(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }

    public static void delete (long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = TaskContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(TaskContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }




}
