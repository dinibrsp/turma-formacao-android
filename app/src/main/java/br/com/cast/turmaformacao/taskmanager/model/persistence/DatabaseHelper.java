package br.com.cast.turmaformacao.taskmanager.model.persistence;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import br.com.cast.turmaformacao.taskmanager.Util.ApplicationUtil;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NOME = "taskmanagerdb";
    private static final int DATABASE_VERSION = 1;

    private DatabaseHelper(Context context){
        super(context, DATABASE_NOME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(){
        return new DatabaseHelper(ApplicationUtil.getContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TaskContract.getCreateTableScript());
        db.execSQL(LabelContract.getCreateTableScript());
        db.execSQL(AccountContract.getCreateTableScript());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
