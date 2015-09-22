package br.com.cast.turmaformacao.taskmanager.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Account;


public final class AccountContract {

    public static final String TABLE = "account";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";

    public static final String [] COLUNS = {ID, NAME, PASSWORD};


    private AccountContract(){
        super();
    }

    public static String getCreateTableScript(){
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(PASSWORD + " TEXT NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Account account){
        ContentValues values = new ContentValues();
        values.put(AccountContract.ID, account.getId());
        values.put(AccountContract.NAME, account.getName());
        values.put(AccountContract.PASSWORD, account.getPassword());
        return values;
    }

    public static Account getAccount(Cursor cursor){
        Account account = new Account();
        if(!cursor.isBeforeFirst() || cursor.moveToNext()) {
            account.setId(cursor.getLong(cursor.getColumnIndex(AccountContract.ID)));
            account.setName(cursor.getString(cursor.getColumnIndex(AccountContract.NAME)));
            account.setPassword(cursor.getString(cursor.getColumnIndex(AccountContract.PASSWORD)));
            return account;
        }
        return null;
    }

    public static List<Account> getAccounts(Cursor cursor){
        ArrayList<Account> accounts = new ArrayList<>();
        while(cursor.moveToNext()) {
            accounts.add(getAccount(cursor));
        }
        return accounts;
    }

}
