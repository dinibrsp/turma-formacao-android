package br.com.cast.turmaformacao.taskmanager.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

public final class TaskContract {

    public static final String TABLE = "task";
    public static final String ID = "id";
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String LABELID = "label_id";

    public static final String[] COLUNS = {ID, _ID, NAME, DESCRIPTION, LABELID};


    private TaskContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(_ID + " TEXT, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(DESCRIPTION + " TEXT, ");
        create.append(LABELID + " INTEGER ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskContract.ID, task.getId());
        values.put(TaskContract._ID, task.get_id());
        values.put(TaskContract.NAME, task.getName());
        values.put(TaskContract.DESCRIPTION, task.getDescription());
        values.put(TaskContract.LABELID, 1L);
        return values;
    }

    public static Task getTask(Cursor cursor) {
        Task task = new Task();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            task.setId(cursor.getLong(cursor.getColumnIndex(TaskContract.ID)));
            task.set_id(cursor.getString(cursor.getColumnIndex(TaskContract._ID)));
            task.setName(cursor.getString(cursor.getColumnIndex(TaskContract.NAME)));
            task.setDescription(cursor.getString(cursor.getColumnIndex(TaskContract.DESCRIPTION)));

            Label label = new Label();
            label.setId(cursor.getLong(cursor.getColumnIndex(TaskContract.LABELID)));
            task.setLabel(label);

            return task;
        }
        return null;
    }

    public static List<Task> getTasks(Cursor cursor) {
        ArrayList<Task> tasks = new ArrayList<>();
        while (cursor.moveToNext()) {
            tasks.add(getTask(cursor));
        }
        return tasks;
    }

}
