package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Match {
    private int[][][] startingSets; //[2][13][4] -> [set -> 0-1][heat -> 0-12][starting gate -> 0 - 3]
    private Club host;
    private Club guest;
    private List<Rider> riders;
    private Heat[] heats;
    private int set;
    private Map<Club, Integer> score;
    private final Map<Rider, List<Integer>> ridersScore;
    private int heatNumber;
    private Rider[][] nominatedRiders;

    public Match(Club host, Club guest) throws InterruptedException {
        this.startingSets = readStartingGates();
        this.host = host;
        this.guest = guest;
        this.riders = new ArrayList<>();
        this.heats = new Heat[15];
        this.set = 0;
        this.score = new HashMap<>();
        this.ridersScore = new HashMap<>();
        this.heatNumber = 0;
        this.nominatedRiders = new Rider[2][4];
        startMatch();
    }

    private void startMatch() throws InterruptedException {
        //Starting settings
        initRiders();
        initHeats();
        initScore();
        initRidersScore();

        //For debugging
        //printRidersScore();
        //System.out.println(riders);
        //System.out.println(heats);

        //Information before match
        printStartingNumbers();

        //Starting match
        startHeats();
        //printRidersScore();
    }

    private void startHeats() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < heats.length; i++) {    //First 13 heats
            heats[i].startHeat();       //Starting heat
            this.heatNumber = i;        //Update heat Number
            int[] results = heats[i].getResults();  //Get array with heat results
            proceedHeatResults(results, i); //Proceed heat results -> udpate match score etc
            //printRidersScore();
            if(i == heats.length - 3) {
                printRidersScore();
                initNominatedHeats();
            }
            scanner.nextLine(); //Wait for next heat
        }
        printRidersScore();
    }

    public void proceedHeatResults(int[] results, int heatNumber) {
        updateTeamsScore(results);
        updateRidersScore(results);
    }

    private void updateRidersScore(int[] results) {
        if(this.heatNumber > 12) {
            updateRidersScoreNominated(results);
            return;
        }
        //Updating riders scores
        for (int i = 0; i < results.length; i++) {
            List<Integer> tempRiderScores = this.ridersScore.get(riders.get(startingSets[set][heatNumber][i]));
            tempRiderScores.add(results[i]);
            this.ridersScore.put(this.riders.get(startingSets[this.set][this.heatNumber][i]), tempRiderScores);
        }
    }

    private void updateRidersScoreNominated(int[] results) {
        for(int i = 0; i < results.length; i++) {
            Rider tempRider = this.nominatedRiders[this.heatNumber - 13][i];
            List<Integer> tempRiderScores = this.ridersScore.get(tempRider);
            tempRiderScores.add(results[i]);
            this.ridersScore.put(tempRider, tempRiderScores);
        }
    }

    private void updateTeamsScore(int[] results) {
        if(this.heatNumber>12) {
            updateTeamsScoreNominated(results);
            return;
        }

        int hostHeatScore = 0;
        int guestHeatScore = 0;
        //Updating teams scores
        if (startingSets[this.set][this.heatNumber][0] <= 8) { //Gate 0 -> guests
            hostHeatScore += results[1] + results[3];
            guestHeatScore += results[0] + results[2];
        } else { //Gate 1-> hosts
            guestHeatScore += results[1] + results[3];
            hostHeatScore += results[0] + results[2];
        }

        int hostMatchScore = (Integer) this.score.get(this.host) + hostHeatScore;
        int guestMatchScore = (Integer) this.score.get(this.guest) + guestHeatScore;

        this.score.put(this.host, hostMatchScore);
        this.score.put(this.guest, guestMatchScore);

        //Printing teams scores
        printResult("Heat results", hostHeatScore, guestHeatScore);
        printResult("Match results", hostMatchScore, guestMatchScore);
    }

    private void updateTeamsScoreNominated(int[] results) {
        int hostHeatScore = 0;
        int guestHeatScore = 0;
        if(this.set == 0 && this.heatNumber == 13 || this.set == 1 && this.heatNumber == 14) {
            hostHeatScore += results[1] + results[3];
            guestHeatScore += results[0] + results[2];
        } else {
            guestHeatScore += results[1] + results[3];
            hostHeatScore += results[0] + results[2];
        }

        int hostMatchScore = (Integer) this.score.get(this.host) + hostHeatScore;
        int guestMatchScore = (Integer) this.score.get(this.guest) + guestHeatScore;

        this.score.put(this.host, hostMatchScore);
        this.score.put(this.guest, guestMatchScore);

        //Printing teams scores
        printResult("Heat results", hostHeatScore, guestHeatScore);
        printResult("Match results", hostMatchScore, guestMatchScore);
    }

    private void printResult(String resultName, int hostResult, int guestResult) {
        System.out.print(resultName + ": ");
        System.out.println(this.host.getName() + " " + hostResult + ":" + guestResult + " " + this.guest.getName());
    }

    private void printStartingNumbers() {
        for (int i = 0; i < this.riders.size(); i++) {
            if (i + 1 == 1)
                System.out.println(this.guest.getName());
            if (i + 1 == 9)
                System.out.println(this.host.getName());

            String riderData;
            if (this.riders.get(i) != null)
                riderData = this.riders.get(i).getName() + " " + this.riders.get(i).getSurname();
            else
                riderData = "";
            System.out.println((i + 1) + ". " + riderData);
        }

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println();
    }

    private void printRidersScore() {
        for (int i = 0; i < this.riders.size(); i++) {
            if (i == 0)
                System.out.println(this.guest.getName());
            if (i == 8)
                System.out.println(this.host.getName());


            Rider rider = this.riders.get(i);
            if (rider != null) {
                List<Integer> scores = this.ridersScore.get(rider);
                String heatsValue = "";
                int matchValue = 0;
                for (int j = 0; j < scores.size(); j++) {
                    heatsValue += scores.get(j) + " ";
                    matchValue += scores.get(j);
                }
                System.out.println((i + 1) + ". " + rider.getName() + " " + rider.getSurname() + "  " + heatsValue + " -> " + matchValue);
            } else {
                System.out.println(i + 1 + ". ");
            }
        }
    }

    private void initNominatedHeats() {
        int n = 4;
        List<Rider> guestsNominatedRiders = selectTopN(0, 7, n);
        List<Rider> hostsNominatedRiders = selectTopN(8, 15, n);

        //Rider[][] nominatedRiders = new Rider[2][4]; // [heat -> 0->13; 1->14][riders -> 0-3]
        if(set == 0) {
            nominatedRiders[0][0] = guestsNominatedRiders.get(3);
            nominatedRiders[0][1] = hostsNominatedRiders.get(3);
            nominatedRiders[0][2] = guestsNominatedRiders.get(2);
            nominatedRiders[0][3] = hostsNominatedRiders.get(2);

            nominatedRiders[1][0] = hostsNominatedRiders.get(1);
            nominatedRiders[1][1] = guestsNominatedRiders.get(1);
            nominatedRiders[1][2] = hostsNominatedRiders.get(0);
            nominatedRiders[1][3] = guestsNominatedRiders.get(0);
        } else {
            nominatedRiders[0][0] = hostsNominatedRiders.get(3);
            nominatedRiders[0][1] = guestsNominatedRiders.get(3);
            nominatedRiders[0][2] = hostsNominatedRiders.get(2);
            nominatedRiders[0][3] = guestsNominatedRiders.get(2);

            nominatedRiders[1][0] = guestsNominatedRiders.get(1);
            nominatedRiders[1][1] = hostsNominatedRiders.get(1);
            nominatedRiders[1][2] = guestsNominatedRiders.get(0);
            nominatedRiders[1][3] = hostsNominatedRiders.get(0);
        }

        this.heats[13] = new Heat(13 + 1, nominatedRiders[0]);
        this.heats[14] = new Heat(14 + 1, nominatedRiders[1]);

        for(int j = 0; j < nominatedRiders.length; j++) {
            System.out.println("HEAT " + (14+j));
            for (int i = 0; i < nominatedRiders[j].length; i++) {
                System.out.println(nominatedRiders[j][i].getName() + " " + nominatedRiders[j][i].getSurname());
            }
            System.out.println();
        }
        /*for (Rider rider : hostsNominatedRiders) {
            System.out.println(rider.getName() + rider.getSurname());
        }

        for (Rider rider : guestsNominatedRiders) {
            System.out.println(rider.getName() + rider.getSurname());
        }*/

    }

    public List<Rider> selectTopN(int startIndex, int endIndex, int n) {
        List<Rider> selectedRiders = this.riders.stream()
                .filter(rider -> {      //Filts riders ex. only number 1-8 or 9-16
                    int index = this.riders.indexOf(rider);
                    return index >= startIndex && index <= endIndex;
                })
                .sorted((rider1, rider2) -> {   //Sorted decreasing
                    int sum1 = ridersScore.get(rider1).stream().filter(Objects::nonNull).mapToInt(Integer::intValue).sum();
                    int sum2 = ridersScore.get(rider2).stream().filter(Objects::nonNull).mapToInt(Integer::intValue).sum();
                    return Integer.compare(sum2, sum1);
                })
                .limit(n)   //First n riders, ex. first 4
                .collect(Collectors.toList());

        return selectedRiders;
    }

    private void initHeats() {
        for (int i = 0; i < 13; i++) {
            Rider[] heatRiders = new Rider[4];
            for (int j = 0; j < 4; j++) {
                heatRiders[j] = this.riders.get(startingSets[set][i][j]);
            }
            heats[i] = new Heat(i + 1, heatRiders);
            //heats[i] = new Heat(i+1, {riders.get(startingSets[set][i][0]3),riders.get(startingSets[set][i][1]),riders.get(startingSets[set][i][2]),riders.get(startingSets[set][i][3])});
        }
    }

    private void initRiders() {
        Club[] club = {guest, host};
        for (int s = 0; s < club.length; s++) {
            for (int i = 0; i < 8; i++) {
                if (i < club[s].getRiders().size())
                    riders.add(club[s].getRiders().get(i));
                else
                    riders.add(null);
            }
        }
    }

    private void initRidersScore() {
        for (int i = 0; i < this.riders.size(); i++) {
            this.ridersScore.put(this.riders.get(i), new ArrayList<>());
        }
    }

    private void initScore() {
        score.put(this.host, 0);
        score.put(this.guest, 0);
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
                        tab[set][row][col] = Integer.parseInt(values[col + 1]) - 1;
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
