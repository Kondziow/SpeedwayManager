package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Match {
    private int[][][] startingSets; //[2][13][4] -> [set -> 0-1][heat -> 0-12][starting gate -> 0 - 3]
    private Club host;
    private Club guest;
    private List<Rider> riders;
    private Heat[] heats;
    private int set;

    public Match(Club host, Club guest) throws InterruptedException {
        this.startingSets = readStartingGates();
        this.host = host;
        this.guest = guest;
        this.riders = new ArrayList<>();
        this.heats = new Heat[15];
        this.set = 0;
        startMatch();
    }

    private void startMatch() throws InterruptedException {
        initRiders();
        initHeats();
        //System.out.println(riders);
        //System.out.println(heats);
        startHeats();
    }

    private void startHeats() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < heats.length-2; i++){
            heats[i].startHeat();
            scanner.nextLine();
        }
    }

    private void initHeats() {
        for (int i = 0; i < 13; i++) {
            Rider[] heatRiders = new Rider[4];
            for (int j = 0; j < 4; j++) {
                heatRiders[j] = this.riders.get(startingSets[set][i][j] - 1);
            }
            heats[i] = new Heat(i + 1, heatRiders);
            //heats[i] = new Heat(i+1, {riders.get(startingSets[set][i][0]3),riders.get(startingSets[set][i][1]),riders.get(startingSets[set][i][2]),riders.get(startingSets[set][i][3])});
        }
    }

    private void initRiders() {
        Club[] club = {host, guest};
        for (int s = 0; s < club.length; s++) {
            for (int i = 0; i < 8; i++) {
                if (i < club[s].getRiders().size())
                    riders.add(club[s].getRiders().get(i));
                else
                    riders.add(null);
            }
        }
    }

    public static int[][][] readStartingGates() {
        int[][][] tab = new int[2][13][4];
        try {
            File file = new File("startingGates.txt");
            Scanner scanner = new Scanner(file);

            int set = 0;
            int row = 0;
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    set++;
                    row = 0;
                    scanner.nextLine();
                } else {
                    String[] values = line.split(" ");

                    // Pomiń pierwszy element w linii (numer)
                    for (int col = 0; col < values.length - 1; col++) {
                        tab[set][row][col] = Integer.parseInt(values[col + 1]);
                    }

                    row++;
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return tab;
    }

    private void printStartingSets() {
        for (int s = 0; s < this.startingSets.length; s++) {
            for (int i = 0; i < this.startingSets[s].length; i++) {
                for (int j = 0; j < this.startingSets[s][i].length; j++) {
                    System.out.print(this.startingSets[s][i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\n");
        }
    }
}
