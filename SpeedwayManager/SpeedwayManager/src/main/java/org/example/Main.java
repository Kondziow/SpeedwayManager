package org.example;

import java.io.*;
import java.util.*;

public class Main {
    private static int season = 2023;
    public static List<Club> clubs = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {

        readData();
        /*System.out.println(clubs.get(1).getRiders().get(0));
        Heat heat = new Heat(1, clubs.get(0).getRiders().get(0), clubs.get(1).getRiders().get(0), clubs.get(0).getRiders().get(1), clubs.get(1).getRiders().get(1));
        heat.startHeat();
        System.out.println(clubs.get(1).getRiders().get(0));*/
        Match match = new Match(clubs.get(0), clubs.get(1));
    }
    private static void readData(){
        String filePath = "Riders.txt";

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            boolean reading = true;

            while(reading){

                String clubName = scanner.next();
                String city = scanner.next();
                scanner.nextLine();
                String stadiumName = scanner.nextLine();
                int stadiumCapacity = scanner.nextInt();
                Club club = new Club(city,clubName,new Stadium(stadiumName,stadiumCapacity,city));
                scanner.nextLine();
                String line = scanner.nextLine();
                while(!line.isEmpty()){
                    String[] parts = line.split(" ");
                    String name = parts[0];
                    String surname = parts[1];
                    String nationality = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    int month = Integer.parseInt(parts[4]) - 1;
                    int day = Integer.parseInt(parts[5]);
                    int abilities = Integer.parseInt(parts[6]);
                    Rider rider = new Rider(name, surname, nationality, new Date(year, month, day), null, abilities);
                    club.addRider(rider);
                    if(scanner.hasNextLine())
                        line = scanner.nextLine();
                    else {
                        line = "";
                        reading = false;
                    }
                }
                clubs.add(club);
                //System.out.println(club);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getSeason() {
        return season;
    }
    public static void setSeason(int season) {
        Main.season = season;
    }

}