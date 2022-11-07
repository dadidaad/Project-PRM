package com.example.projectprm.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.model.entities.Account;

import java.util.Date;
import java.util.List;

@Dao
public interface AccountDao {
    @Insert
    public void insert(Account account);

    @Update
    public void update(Account account);

    @Delete
    public void delete(Account account);

    @Query("SELECT * FROM `Account`")
    public List<Account> getAll();

    @Query("SELECT * FROM ACCOUNT where acc_id = :accId")
    public Account getById(int accId);

    @Query("SELECT * FROM `Account` WHERE username = :username AND password = :password")
    public Account login(String username, String password);

    @Query("SELECT dob  FROM `Account` WHERE username = :username AND password = :password")
    public Date isDobNull(String username, String password);

    @Query("SELECT * FROM `Account` WHERE username = :username")
    public boolean isUserExist(String username);

    @Query("UPDATE `Account` SET password = :password WHERE acc_id = :acc_id")
    public int changePassword(String password, int acc_id);

    @Query("UPDATE `Account` SET dob = :dob, disp_name = :fullName, address = :address WHERE acc_id = :acc_id")
    public int editProfile(String fullName, String address, int dob, int acc_id);

    @Query("SELECT dob FROM `Account` WHERE username = :username AND password = :password")
    public int getDobByAccId(String username, String password);
/*
    @Query("INSERT INTO Account (username, password, dob, disp_name, address, type, created_date)\n" +
            "VALUES (:username, :password, '', '', '', 'Customer', '')")
    public long register(String username, String password);*/
}
