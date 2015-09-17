package br.com.cast.turmaformacao.taskmanager;


import android.app.Application;

import br.com.cast.turmaformacao.taskmanager.Util.ApplicationUtil;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;

public class TaskManagerApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());

        Color[] values = Color.values();

    }
}
