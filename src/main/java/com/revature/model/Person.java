package com.revature.model;

public abstract class Person {
    private String email;
    private String password;
    private String name;
    private String employeeStatusString;
    //private EmployeeStatus employeeStatus;
    private int personID;

    public Person(){
        this.name = "";
        this.email = "";
        this.password = "";
        //this.employeeStatus = EmployeeStatus.EMPLOYEE;
        this.employeeStatusString = "EMPLOYEE";
        this.personID = 0;  // handled by the Database to autoincrement the ID
    }

    public void setName(String name){ this.name = name;}
    public void setEmail(String email){ this.email = email; }
    public void setPassword(String password){ this.password = password; }
    //public void setEmployeeStatus(EmployeeStatus e){ this.employeeStatus = e;}
    public void setPersonID(int personID) { this.personID = personID; }
    public void setEmployeeStatusString(String s) { this.employeeStatusString = s; }

    public String getName(){ return this.name; };
    public String getEmail(){ return this.email; };
    public String getPassword(){ return this.password; };
    //public String getEmployeeStatus(){ return this.employeeStatus.toString(); }
    public int getPersonID() { return personID;}
    public String getEmployeeStatusString(){ return this.employeeStatusString; }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID: " + this.personID + "\n");
        sb.append("Name: " + this.name + "\n");
        sb.append("Email: " + this.email + "\n");
        sb.append("Status: " + this.employeeStatusString + "\n");

        return sb.toString();
    }

}
