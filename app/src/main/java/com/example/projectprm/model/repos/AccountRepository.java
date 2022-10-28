package com.example.projectprm.model.repos;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projectprm.dao.AccountDao;
import com.example.projectprm.dao.room.AccountDatabase;
import com.example.projectprm.model.entities.Account;

import java.util.List;

public class AccountRepository {

    private AccountDao accountDao;

    private Account login;

    public AccountRepository(Application application) {
        AccountDatabase accountDatabase = AccountDatabase.getINSTANCE(application);
        accountDao = accountDatabase.accountDao();
    }

    public AccountRepository(Application application, String username, String password) {
        AccountDatabase accountDatabase = AccountDatabase.getINSTANCE(application);
        accountDao = accountDatabase.accountDao();
        login = accountDao.login(username, password);
    }

    public void insert(Account model) {
        new AccountRepository.InsertCourseAsyncTask(accountDao).execute(model);
    }

    // creating a method to update data in database.
    public void update(Account model) {
        new AccountRepository.UpdateCourseAsyncTask(accountDao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(Account model) {
        new AccountRepository.DeleteCourseAsyncTask(accountDao).execute(model);
    }

    public Account login() {return login;}

    private static class InsertCourseAsyncTask extends AsyncTask<Account, Void, Void> {
        private AccountDao accountDao;

        private InsertCourseAsyncTask(AccountDao AccountDao) {
            this.accountDao = AccountDao;
        }

        @Override
        protected Void doInBackground(Account... model) {
            // below line is use to insert our modal in dao.
            accountDao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateCourseAsyncTask extends AsyncTask<Account, Void, Void> {
        private AccountDao accountDao;

        private UpdateCourseAsyncTask(AccountDao AccountDao) {
            this.accountDao = AccountDao;
        }

        @Override
        protected Void doInBackground(Account... models) {
            // below line is use to update
            // our modal in dao.
            accountDao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCourseAsyncTask extends AsyncTask<Account, Void, Void> {
        private AccountDao accountDao;

        private DeleteCourseAsyncTask(AccountDao AccountDao) {
            this.accountDao = AccountDao;
        }

        @Override
        protected Void doInBackground(Account... models) {
            // below line is use to delete
            // our course modal in dao.
            accountDao.delete(models[0]);
            return null;
        }
    }
}
