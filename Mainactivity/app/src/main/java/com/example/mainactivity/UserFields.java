package com.example.mainactivity;

public class UserFields {
    String email;
    String password;
    String user;

    public UserFields(String email, String password, String user) {
        this.email = email;
        this.password = password;
        this.user = user;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return password;
    }

    public void setPass(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
