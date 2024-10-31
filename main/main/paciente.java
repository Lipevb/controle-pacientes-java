package com.main;

public class paciente {
    private String name;
    private int age;
    private String contact;

    public paciente(String name, int age, String contact) {
        this.name = name;
        this.age = age;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContact() {
        return contact;
    }
}

