package org.example;

public class Stadium {
    private String name;
    private int capacity;
    private String city;

    public Stadium(String name, int capacity, String city) {
        this.name = name;
        this.capacity = capacity;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Stadium{" +
                name + " " + city +
                ", " + capacity + " capacity" +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
