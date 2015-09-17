package br.com.cast.turmaformacao.taskmanager.controllers.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.Util.FormHelper;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.TaskListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.services.LabelBusinessService;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessService;

public class TaskFormActivity extends AppCompatActivity {

    public static final String PARAM_TASK = "PARAM_TASK";
    private EditText editTextName;
    private EditText editTextDescription;
    private Button buttonSave;
    private Task task;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        initTask();

        bindEditTextName();
        bindEditTextDescription();
        bindButton();
    }

    private void initTask(){
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            this.task = (Task) getIntent().getExtras().getParcelable(PARAM_TASK);
        }else{
            this.task = this.task == null ? new Task() : task;
        }
    }


    private void bindButton() {
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String required = TaskFormActivity.this.getString(R.string.lbl_required);
                if (!FormHelper.validateRequired(required, editTextName, editTextDescription)) {
                    binTask();
                    TaskBusinessService.save(task);
                    Toast.makeText(TaskFormActivity.this, R.string.lbl_saved, Toast.LENGTH_SHORT).show();
                    TaskFormActivity.this.finish();
                }
            }
        });
    }

    private void binTask(){
        task.setName(editTextName.getText().toString());
        task.setDescription(editTextDescription.getText().toString());
    }

    private void bindEditTextDescription(){
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextDescription.setText(task.getDescription() == null ? "" : task.getDescription());
    }

    private void bindEditTextName(){
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(task.getName() == null ? "" : task.getName());
    }



}
