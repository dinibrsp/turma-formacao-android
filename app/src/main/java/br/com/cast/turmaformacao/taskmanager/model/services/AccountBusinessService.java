package br.com.cast.turmaformacao.taskmanager.model.services;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Account;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.persistence.AccountReposiroty;
import br.com.cast.turmaformacao.taskmanager.model.persistence.LabelReposiroty;

public final class AccountBusinessService {


    private AccountBusinessService(){
        super();
    }


    public static List<Account> findAll() {
        return AccountReposiroty.getAll();
    }

    public static void  save(Account account){
        AccountReposiroty.save(account);

    }

    public static void delete(Account selectedAccount){
        AccountReposiroty.delete(selectedAccount.getId());
    }
}
