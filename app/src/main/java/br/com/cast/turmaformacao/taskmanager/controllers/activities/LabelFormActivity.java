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
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.TaskListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.services.LabelBusinessService;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessService;

public class LabelFormActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextDescription;
    private Spinner spinnerColor;
    private Label label;
    private ListView listViewLabelList;
    private Label selectedLabel;
    private ListView listColorView;
    private Color colorSelect;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_form);

        initLabel();
        bindEditTextName();
        bindEditTextDescription();
        bindSpinnerColor();
        bindLabelList();
        bindListColorView();
    }

    private void bindListColorView(){
        listColorView = (ListView) findViewById(R.id.listViewLabelList);
    }



    private void initLabel() {
        this.label = new Label();
    }

    public void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
    }

    public void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
    }

    public void bindSpinnerColor() {
        spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
        Color[] list = Color.values();

        ColorListAdapter colorAdapter = new ColorListAdapter(LabelFormActivity.this, list);

        spinnerColor.setAdapter(colorAdapter);
    }

    private void onMenuSaveClick() {
        String required = LabelFormActivity.this.getString(R.string.lbl_required);
        if (!FormHelper.validateRequired(required, editTextName, editTextDescription)) {
            binLabel();
            LabelBusinessService.save(label);
            List<Label> all = LabelBusinessService.findAll();
            Toast.makeText(LabelFormActivity.this, R.string.lbl_saved, Toast.LENGTH_SHORT).show();
            updateLabelList();
            finish();
            //Toast.makeText(LabelFormActivity.this, all.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void binLabel() {
        label.setName(editTextName.getText().toString());
        label.setDescription(editTextDescription.getText().toString());
        colorSelect = (Color) spinnerColor.getSelectedItem();
        label.setColor(colorSelect);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                onMenuSaveClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected  void onResume(){
        updateLabelList();
        super.onResume();
    }

    private void updateLabelList() {
        List<Label> values = LabelBusinessService.findAll();
        listViewLabelList.setAdapter(new LabelListAdapter(this, values));
        LabelListAdapter adapter = (LabelListAdapter) listViewLabelList.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void bindLabelList(){
        listViewLabelList = (ListView) findViewById(R.id.listViewLabelList);
        registerForContextMenu(listViewLabelList);
        listViewLabelList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                LabelListAdapter adapter = (LabelListAdapter) listViewLabelList.getAdapter();
                selectedLabel = adapter.getItem(position);
                return false;
            }
        });
    }






}
