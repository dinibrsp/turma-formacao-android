package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Account;

public final class AccountReposiroty {

    private AccountReposiroty(){
        super();
    }

    public static void save(Account account){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = AccountContract.getContentValues(account);
        if(account.getId() == null) {
            db.insert(AccountContract.TABLE, null, values);
        }else{
            String where = AccountContract.ID + " = ? ";
            String[] params = {account.getId().toString()};
            db.update(AccountContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }


    public static Account checkAccount(Account account){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = AccountContract.NAME + " = ? and " + AccountContract.PASSWORD + " = ?";
        String[] params = {account.getName(),account.getPassword()};

        Cursor cursor = db.query(AccountContract.TABLE, AccountContract.COLUNS, where, params, null, null, AccountContract.ID);

        account = AccountContract.getAccount(cursor);

        db.close();
        databaseHelper.close();

        return account;
    }

    public static List<Account> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(AccountContract.TABLE, AccountContract.COLUNS, null, null, null, null, null);
        List<Account> values = AccountContract.getAccounts(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }

    public static void delete (long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = AccountContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(AccountContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }




}
