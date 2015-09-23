package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.os.AsyncTask;
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
import br.com.cast.turmaformacao.taskmanager.model.entities.Account;
import br.com.cast.turmaformacao.taskmanager.model.entities.Address;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.http.AddressService;
import br.com.cast.turmaformacao.taskmanager.model.services.AccountBusinessService;
import br.com.cast.turmaformacao.taskmanager.model.services.LabelBusinessService;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessService;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText editTextCreateAccount;
    private EditText editTextCreatePassword;
    private EditText editTextCep;
    private EditText editTextTipoDeLogradouro;
    private EditText editTextLogradouro;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextEstado;
    private Button buttonCheckCep;
    private Button buttonAdd;
    private Account account;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        initAccount();
        bindEditTextCreateAccount();
        bindEditTextCreatePassword();
        bindButtonAdd();
        bindEditTextCep();
        bindEditTextTipoDeLogradouro();
        bindEditTextLogradouro();
        bindEditTextBairro();
        bindEditTextCidade();
        bindEditTextEstado();
        bindButtonCheckCep();

    }

    private void initAccount() {
        this.account = new Account();
    }


    private class GetAddressTask extends AsyncTask<String, Void, Address> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Address doInBackground(String... params) {
            return AddressService.getAddressByCep(params[0]);
        }

        @Override
        protected void onPostExecute(Address address) {
            super.onPostExecute(address);
        }
    }

    private void bindButtonCheckCep() {
        buttonCheckCep = (Button) findViewById(R.id.buttonCheckCep);
        buttonCheckCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String required = CreateAccountActivity.this.getString(R.string.lbl_required);
                if (!FormHelper.validateRequired(required, editTextCep)) {
                    new GetAddressTask().execute(editTextCep.getText().toString());
                }
            }
        });
    }


    private void bindButtonAdd() {
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String required = CreateAccountActivity.this.getString(R.string.lbl_required);
                if (!FormHelper.validateRequired(required, editTextCreateAccount, editTextCreatePassword)) {
                    binAccount();
                    AccountBusinessService.save(account);
                    Toast.makeText(CreateAccountActivity.this, R.string.lbl_created, Toast.LENGTH_SHORT).show();
                    CreateAccountActivity.this.finish();
                }
            }
        });
    }

    private void binAccount(){
        account.setName(editTextCreateAccount.getText().toString());
        account.setPassword(editTextCreatePassword.getText().toString());
    }

    public void bindEditTextCreateAccount() {
        editTextCreateAccount = (EditText) findViewById(R.id.editTextCreateAccount);
    }

    public void bindEditTextCreatePassword() {
        editTextCreatePassword = (EditText) findViewById(R.id.editTextCreatePassword);
    }

    public void bindEditTextCep() {
        editTextCep = (EditText) findViewById(R.id.editTextCep);
    }

    public void bindEditTextTipoDeLogradouro() {
        editTextTipoDeLogradouro = (EditText) findViewById(R.id.editTextTipoDeLogradouro);
    }

    public void bindEditTextLogradouro() {
        editTextLogradouro = (EditText) findViewById(R.id.editTextLogradouro);
    }

    public void bindEditTextBairro() {
        editTextBairro = (EditText) findViewById(R.id.editTextBairro);
    }

    public void bindEditTextCidade() {
        editTextCidade = (EditText) findViewById(R.id.editTextCidade);
    }

    public void bindEditTextEstado() {
        editTextEstado = (EditText) findViewById(R.id.editTextEstado);
    }


}
