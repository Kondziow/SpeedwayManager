package org.example;

import java.util.Date;

public class Main {
    private static int season = 2023;
    private static Rider[] torunRiders = {
            new Rider("Robert", "Lambert", "UK", new Date(1998,4,5), 505, 88),
            new Rider("Patryk", "Dudek", "POL", new Date(1992,6,20), 692, 88),
            new Rider("Paweł", "Przedpełski", "POL", new Date(1998,6,23), 323, 84),
            new Rider("Emil", "Sajfutdinow", "RUS", new Date(1989,10,26), 89, 89),
            new Rider("Wiktor", "Lampart", "POL", new Date(2001,5,21), 47, 78),
            new Rider("Mateusz", "Affelt", "POL", new Date(2006,6,13), 17, 63),
            new Rider("Krzysztof", "Lewandowski", "POL", new Date(2005,1,20), 18, 68)
    };
    public static void main(String[] args) {

        Club torunClub = createClub(torunRiders);

        System.out.println(torunClub);
        System.out.println(torunRiders[0].getAge());
        System.out.println(torunRiders[0].getFormattedDate());
        System.out.println(torunRiders[0].getDateOfBirth().getYear());
        System.out.println(Main.getSeason());

    }
    private static Club createClub(Rider[] riders){
        Club club = new Club("Toruń", "Apator Toruń", new Stadium("Motoarena", 15000,"Toruń"));
        for(var rider : riders){
            club.getRiders().add(rider);
        }
        return club;
    }
    public static int getSeason() {
        return season;
    }
    public static void setSeason(int season) {
        Main.season = season;
    }
}