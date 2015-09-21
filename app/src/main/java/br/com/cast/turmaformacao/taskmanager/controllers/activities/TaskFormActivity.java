package br.com.cast.turmaformacao.taskmanager.controllers.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.Util.FormHelper;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.ColorListAdapter;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.LabelListAdapter;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.SpinnerListAdapter;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.TaskListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;
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
    private Button buttonAddLabel;
    private Spinner spinnerLabels;
    private Label selectedLabel;
    private Color colorSelect;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        initTask();

        bindEditTextName();
        bindEditTextDescription();
        bindButtonAddLabel();
        bindSpinnerLabels();
    }

    protected  void onResume(){
        updateLabelList();
        super.onResume();
    }

    private void updateLabelList() {
        List<Label> values = LabelBusinessService.findAll();
        spinnerLabels.setAdapter(new SpinnerListAdapter(this, values));
        SpinnerListAdapter adapter = (SpinnerListAdapter) spinnerLabels.getAdapter();
        adapter.notifyDataSetChanged();
    }

    public void bindSpinnerLabels() {
        spinnerLabels = (Spinner) findViewById(R.id.spinnerLabels);
        registerForContextMenu(spinnerLabels);
        spinnerLabels.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SpinnerListAdapter adapter = (SpinnerListAdapter) spinnerLabels.getAdapter();
                selectedLabel = adapter.getItem(position);
                return false;
            }
        });

    }


    private void bindButtonAddLabel() {
        buttonAddLabel = (Button) findViewById(R.id.addLabel);
        buttonAddLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirectToLabelFormActivity = new Intent(TaskFormActivity.this, LabelFormActivity.class);
                startActivity(redirectToLabelFormActivity);

            }
        });
    }

    private void initTask(){
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            this.task = (Task) getIntent().getExtras().getParcelable(PARAM_TASK);
        }else{
            this.task = this.task == null ? new Task() : task;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menu_save:
                onMenuSaveClick();
                break;
            case R.id.addLabel:
                bindButtonAddLabel();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void onMenuSaveClick() {
            String required = TaskFormActivity.this.getString(R.string.lbl_required);
            if (!FormHelper.validateRequired(required, editTextName, editTextDescription)) {
                binTask();
                TaskBusinessService.save(task);
                Toast.makeText(TaskFormActivity.this, R.string.lbl_saved, Toast.LENGTH_SHORT).show();
                TaskFormActivity.this.finish();
            }
        }


    private void binTask(){
        task.setName(editTextName.getText().toString());
        task.setDescription(editTextDescription.getText().toString());
        task.setLabel((Label) spinnerLabels.getSelectedItem());
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
