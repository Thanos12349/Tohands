package com.example.tohand;

public class User {
    String Phone ,deposite,due;

    public User(String deposite, String due) {
        this.deposite = deposite;
        this.due = due;
    }


    public User() {
    }

    public User(String phone) {
        Phone = phone;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
