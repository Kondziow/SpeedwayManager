package org.example;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Heat {
    private int numberOfRiders;
    private int heatNumber;
    private Rider[] riders;
    private int[] results;

    public Heat(int heatNumber, Rider[] riders) {
        this.heatNumber = heatNumber;
        this.riders = riders;
        this.numberOfRiders = riders.length;
        this.results = new int[this.riders.length];
    }

    public void startHeat() throws InterruptedException {
        /*System.out.println("STARTING HEAT " + this.heatNumber + "!");
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("Waiting for the results...");
        TimeUnit.MILLISECONDS.sleep(1000);*/
        System.out.println("HEAT NUMBER " + this.heatNumber);
        calculateResults();
        printResults(this.results);
    }

    private void printResults(int[] results) {
        /*for (int i = 0; i < results.length; i++) {
            if(results[i] == 3)
                System.out.println(this.riders[i].getName() + " " + this.riders[i].getSurname() + " wins!");
        }*/
        for (int i = 0; i < results.length; i++) {
            System.out.println(this.riders[i].getName() + " " + this.riders[i].getSurname() + " " + results[i]);
        }
        System.out.println();
    }

    private int[] calculateResults() {
        int[] abilities = new int[this.numberOfRiders];
        for (int i = 0; i < abilities.length; i++) {
            abilities[i] = riders[i].getAbilities();
        }

        this.results = new int[abilities.length];
        Arrays.fill(results, 0);    //Fills array with zeros, like {0,0,0,0}

        for (int i = 0; i < abilities.length; i++) {
            for (int j = i + 1; j < abilities.length; j++) {
                if (abilities[i] > abilities[j])
                    results[i]++;
                else
                    results[j]++;
            }
        }

        return results;
    }

    public int[] getResults() {
        return results;
    }
}
