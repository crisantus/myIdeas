package com.example.myideas.model;

public class Idea {

    private String Name;
    private String Description;
    private String Owner;

    public Idea() {
    }

    public Idea(String name, String description, String owner) {
        Name = name;
        Description = description;
        Owner = owner;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }
}
