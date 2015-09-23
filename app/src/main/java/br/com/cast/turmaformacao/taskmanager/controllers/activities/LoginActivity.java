package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.Util.FormHelper;
import br.com.cast.turmaformacao.taskmanager.model.entities.Account;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.persistence.AccountReposiroty;
import br.com.cast.turmaformacao.taskmanager.model.services.LabelBusinessService;


public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonCreate;
    private Account account;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindEditTextLogin();
        bindEditTextPassword();
        bindButtonCreate();

    }
    private void bindButtonCreate() {
        buttonCreate = (Button) findViewById(R.id.buttonCreate);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirectToCreateAccountActivity = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(redirectToCreateAccountActivity);
            }
        });
    }


    public boolean checkLogin(){
        Account checkAccount = new Account();

        checkAccount.setName(editTextLogin.getText().toString());
        checkAccount.setPassword(editTextPassword.getText().toString());

        account = AccountReposiroty.checkAccount(checkAccount);

        if(account != null)
            return true;
        else
            return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_login:
                onMenuLoginClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuLoginClick() {
        binLogin();
    }

    private void binLogin() {
        String failmsg = getResources().getString(R.string.failmsg);
        if(checkLogin()) {
            Intent redirectToTaskListActivity = new Intent(LoginActivity.this, TaskListActivity.class);
            startActivity(redirectToTaskListActivity);
            finish();
        }
        else
            Toast.makeText(LoginActivity.this, failmsg, Toast.LENGTH_LONG).show();
    }


    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
    }

}
