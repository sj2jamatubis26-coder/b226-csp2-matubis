package com.joysistvi.recording.model;

public class Artist {

    private int id;
    private String name;

    // Constructor for Create
    public Artist(String name) {
        this.name = name;
    }

    // Constructor for Read
    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name=" + name + '\'' +
                '}';
    }
}
