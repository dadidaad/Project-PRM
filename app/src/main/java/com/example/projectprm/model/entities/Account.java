package com.example.projectprm.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Account", indices = {@Index(value = {"username"}, unique = true)})
public class Account {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "acc_id")
    private int accountId;

    @ColumnInfo(name = "username", collate = ColumnInfo.NOCASE)
    @NonNull
    private String username;

    @ColumnInfo(name = "password", collate = ColumnInfo.NOCASE)
    @NonNull
    private String password;

    @ColumnInfo(name = "dob")
    private Date dateOfBirth;

    @ColumnInfo(name = "disp_name")
    private String displayName;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "type", defaultValue = "Customer")
    @NonNull
    private String type;

    @ColumnInfo(name = "created_date")
    private Date createdDate;

    @ColumnInfo(name = "phone")
    private String phone;

    public Account() {
    }

    public Account(int accountId, @NonNull String username, @NonNull String password, Date dateOfBirth, String displayName, String address, @NonNull String type, String phone) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.displayName = displayName;
        this.address = address;
        this.type = type;
        this.phone = phone;
    }

    public Account(int accountId, @NonNull String username, @NonNull String password, Date dateOfBirth, String displayName, String address, @NonNull String type, String phone) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.displayName = displayName;
        this.address = address;
        this.type = type;
        this.phone = phone;
    }

    public Account(@NonNull String username, @NonNull String password, @NonNull String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
