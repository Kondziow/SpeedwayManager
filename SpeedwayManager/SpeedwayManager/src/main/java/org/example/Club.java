package org.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Club {
    private String city;
    private String name;
    private Stadium stadium;
    private List<Rider> riders;

    public void addRider(Rider rider){
        riders.add(rider);
    }
    @Override
    public String toString() {
        /*return "Club{\n" +
                name + ":\n" +
                "Stadium: " + stadium + "\n"+
                "Riders:" + riders +"\n"+
                '}';*/
        sortRiders();
        String clubRiders = "";
        for(var rider : riders){
            clubRiders +=rider.toString();
        }
        return name + city + ":\n" +
                stadium +
                "\nRiders:\n" + clubRiders;
    }

    public Club(String city, String name, Stadium stadium) {
        this.city = city;
        this.name = name;
        this.stadium = stadium;
        this.riders = new LinkedList<Rider>();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public List<Rider> getRiders() {
        return riders;
    }

    public void setRiders(List<Rider> riders) {
        this.riders = riders;
    }

    public void sortRiders() {
        Collections.sort(riders,
                Comparator.comparing(Rider::getAbilities)
                .reversed()
                .thenComparing(Rider::getSurname));
    }
}
