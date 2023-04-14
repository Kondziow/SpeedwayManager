package org.example;

import java.util.Date;

public class Rider {
    private String name;
    private String surname;
    private String nationality;
    private Date dateOfBirth;
    private int SGPNumber;
    private int abilities;

    public Rider(String name, String surname, String nationality, Date dateOfBirth, int SGPNumber, int abilities) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.SGPNumber = SGPNumber;
        this.abilities = abilities;
    }

    public String getFormattedDate(){
        String day = String.valueOf(dateOfBirth.getDay());
        String month = String.valueOf(dateOfBirth.getMonth());
        String year = String.valueOf(dateOfBirth.getYear());
        if (dateOfBirth.getDay() < 10)
            day = "0" + dateOfBirth.getDay();
        if (dateOfBirth.getMonth() < 10)
            month = "0" + dateOfBirth.getMonth();
        return day + "." + month + "." + year;
    }
    @Override
    public String toString() {
        /*return "Rider{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nationality='" + nationality + '\'' +
                ", dateOfBirth=" + day + "." + month + "." + dateOfBirth.getYear() +
                ", SGPNumber=" + SGPNumber +
                ", abilities=" + abilities +
                '}';*/
        return "\n" + name + " " + surname + "\n" +
                "\tNumber: " + SGPNumber + "\n" +
                "\tAbilities: " + abilities + "\n" +
                "\tFrom: " + nationality + "\n" +
                "\tBorn:" + getFormattedDate() ;
    }

    public int getAge() {
        return (Main.getSeason() - dateOfBirth.getYear());
    }

    public int getAbilities() {
        return abilities;
    }

    public void setAbilities(int abilities) {
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getSGPNumber() {
        return SGPNumber;
    }

    public void setSGPNumber(int SGPNumber) {
        this.SGPNumber = SGPNumber;
    }
}
