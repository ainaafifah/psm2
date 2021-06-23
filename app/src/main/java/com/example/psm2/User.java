package com.example.psm2;

public class User {

    public String fullName, lastName, noPhone, email;

    public User(){

    }

    public User(String fullName, String lastName, String noPhone, String email){
        this.fullName = fullName;
        this.lastName = lastName;
        this.noPhone = noPhone;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNoPhone() {
        return noPhone;
    }

    public void setNoPhone(String noPhone) {
        this.noPhone = noPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
