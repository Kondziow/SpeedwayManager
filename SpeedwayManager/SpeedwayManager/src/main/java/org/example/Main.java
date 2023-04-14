package org.example;

import java.util.Date;

public class Main {
    private static int season = 2023;
    public static void main(String[] args) {
        Rider[] torunRiders = {
                new Rider("Robert", "Lambert", "UK", new Date(1999,4,5), 505, 80),
                new Rider("Patryk", "Dudek", "POL", new Date(1999,4,5), 505, 80),
                new Rider("Paweł", "Przedpełski", "POL", new Date(1999,4,5), 505, 80),
                new Rider("Emil", "Sayfutdinow", "RUS", new Date(1999,4,5), 505, 80),
                new Rider("Wiktor", "Lampart", "POL", new Date(1999,4,5), 505, 80),
                new Rider("Mateusz", "Affelt", "POL", new Date(1999,4,5), 505, 80),
                new Rider("Krzysztof", "Lewandowski", "POL", new Date(2005,6,10), 110, 60)
        };
        Club torunClub = new Club("Toruń", "Apator Toruń", new Stadium("Motoarena", 15000,"Toruń"));
        for(var rider : torunRiders){
            torunClub.getRiders().add(rider);
        }

        System.out.println(torunClub);
        System.out.println(torunRiders[0].getAge());
        System.out.println(torunRiders[0].getFormattedDate());
        System.out.println(torunRiders[0].getDateOfBirth().getYear());
        System.out.println(Main.getSeason());

    }

    public static int getSeason() {
        return season;
    }

    public static void setSeason(int season) {
        Main.season = season;
    }
}