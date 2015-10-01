package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Account;
import br.com.cast.turmaformacao.taskmanager.model.persistence.AccountReposiroty;


public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonCreate;
    private Account account;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindEditTextLogin();
        bindEditTextPassword();
        bindButtonLogin();
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



    private void bindButtonLogin() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String message = getResources().getString(R.string.msg_welcome,editTextLogin.getText());
                //Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                if(checkLogin()) {
                    Intent redirectToTaskListActivity = new Intent(LoginActivity.this, TaskListActivity.class);
                    startActivity(redirectToTaskListActivity);
                    finish();
                }
                else
                    Toast.makeText(LoginActivity.this, "Nao", Toast.LENGTH_LONG).show();
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

    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
    }

}
