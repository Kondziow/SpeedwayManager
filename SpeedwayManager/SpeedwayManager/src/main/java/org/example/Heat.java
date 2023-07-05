package org.example;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Heat {
    private int numberOfRiders;
    private int heatNumber;
    private Rider[] riders;

    public Heat(int heatNumber, Rider rider1, Rider rider2, Rider rider3, Rider rider4) {
        this.heatNumber = heatNumber;
        this.riders = new Rider[]{rider1, rider2, rider3, rider4};
        this.numberOfRiders = riders.length;
    }

    public void startHeat() throws InterruptedException {
        System.out.println("STARTING HEAT " + this.heatNumber + "!");
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("Waiting for the results...");
        TimeUnit.MILLISECONDS.sleep(1000);
        printResults(calculateResults());
    }

    private void printResults(int[] results) {
        for (int i = 0; i < results.length; i++) {
            if(results[i] == 3)
                System.out.println(this.riders[i].getName() + " " + this.riders[i].getSurname() + " wins!");
        }
        for (int i = 0; i < results.length; i++) {
            System.out.println(this.riders[i].getName() + " " + this.riders[i].getSurname() + " " + results[i]);
        }
    }

    private int[] calculateResults() {
        int[] abilities = new int[this.numberOfRiders];
        for (int i = 0; i < abilities.length; i++) {
            abilities[i] = riders[i].getAbilities();
        }

        int[] results = new int[abilities.length];
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
}
