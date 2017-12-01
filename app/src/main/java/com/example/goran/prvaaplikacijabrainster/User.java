package com.example.goran.prvaaplikacijabrainster;

import java.io.Serializable;

/**
 * Created by goran on 11/25/17.
 */

public class User implements Serializable {

    String name;
    String Lastname;
    String Username;
    char gender;

    public User(String name, String lastname, String username, char gender) {
        this.name = name;
        Lastname = lastname;
        Username = username;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {


        return Username;
    }
}
