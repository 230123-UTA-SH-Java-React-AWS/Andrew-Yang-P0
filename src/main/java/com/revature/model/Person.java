package com.revature.model;

public abstract class Person {
    private static int personID = 0;
    private String name;
    private String email;
    private String password;


    public Person(String email, String password) {
        this.personID += 1;
        this.name = "";
        this.email = email;
        this.password = password;
    }

    public void setName(){};
    public void setEmail(){};
    public void setPassword(){};

    public String getName(){ return this.name; };
    public String getEmail(){ return this.email; };
    public String getPassword(){ return this.password; };

}
