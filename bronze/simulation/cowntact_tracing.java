import java.io.*;
import java.util.*;

public class Main {
    static int N, T;
    static int[][] interactions = new int[251][2];
    static boolean[] finalInfected;

    static boolean consistent(int patientZero, int K) {
        boolean[] infected = new boolean[N + 1];
        int[] handshakeCount = new int[N + 1];

        infected[patientZero] = true;

        for (int time = 0; time <= 250; time++) {
            int a = interactions[time][0];
            int b = interactions[time][1];

            if (a == 0) continue;

            if (infected[a]) handshakeCount[a]++;
            if (infected[b]) handshakeCount[b]++;

            if (infected[a] && handshakeCount[a] <= K) infected[b] = true;
            if (infected[b] && handshakeCount[b] <= K) infected[a] = true;
        }

        for (int i = 1; i <= N; i++) {
            if (infected[i] != finalInfected[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("tracing.in"));

        N = sc.nextInt();
        T = sc.nextInt();
        String s = sc.next();

        finalInfected = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            finalInfected[i] = (s.charAt(i - 1) == '1');
        }

        for (int i = 0; i < T; i++) {
            int t = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            interactions[t][0] = x;
            interactions[t][1] = y;
        }

        boolean[] possibleCow = new boolean[N + 1];
        boolean[] possibleK = new boolean[252];

        for (int cow = 1; cow <= N; cow++) {
            for (int K = 0; K <= 251; K++) {
                if (consistent(cow, K)) {
                    possibleCow[cow] = true;
                    possibleK[K] = true;
                }
            }
        }

        int countCows = 0;
        for (int i = 1; i <= N; i++) {
            if (possibleCow[i]) countCows++;
        }

        int lowerK = 251, upperK = 0;
        for (int K = 0; K <= 251; K++) if (possibleK[K]) upperK = K;
        for (int K = 251; K >= 0; K--) if (possibleK[K]) lowerK = K;

        PrintWriter out = new PrintWriter(new File("tracing.out"));
        out.print(countCows + " " + lowerK + " ");
        if (upperK == 251) out.println("Infinity");
        else out.println(upperK);
        out.close();
    }
}

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        try{
            sc = new Scanner(new File("tracing.in"));
        }catch (Exception e){

        }

        int[][] interactions = new int[251][2];

        int N = sc.nextInt();
        int T = sc.nextInt();

        boolean[] cows = new boolean[N+1];

        String cowsStatuses = sc.next();
        //System.out.println("Statuses: " + cowsStatuses);
        for(int rep = 1; rep <= N; rep++){
            //System.out.println();
            if((cowsStatuses.substring(rep-1,rep).equals("1"))){cows[rep] = true;}
            else if((cowsStatuses.substring(rep-1,rep).equals("0"))){cows[rep] = false;}

        }

        int t,x,y;
        for(int rep = 0; rep < T; rep++){
            t = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();
            interactions[t][0] = x;
            interactions[t][1] = y;
        }

        int kUpper = Math.min(N, 250);
        int kLower = 0;

        int lowestFoundK = Integer.MAX_VALUE;
        int highestFoundK = Integer.MIN_VALUE;

        int possibleCows = 0;

        boolean check;
        for(int k = kLower; k <= kUpper; k++){ //for each K value
            for(int cow = 1; cow <= N; cow++){ //for each cow
                boolean[] tempCows = new boolean[N+1];
                tempCows[cow] = true;
                HashMap<Integer, Integer> infected = new HashMap<>();
                infected.put(cow, k);
                for(int time = 1; time <= 250; time++){ //for each time step
                    for(int infecter = 0; infecter < tempCows.length; infecter++){
                        if(infected.containsKey(infecter)){
                            if(infected.get(infecter) > 0) {

                                if (interactions[time][0] == infecter) {
                                    tempCows[interactions[time][1]] = true;
                                    infected.put(interactions[time][1], k);
                                    infected.replace(infecter, infected.get(infecter) - 1);
                                } else if (interactions[time][1] == infecter) {
                                    tempCows[interactions[time][0]] = true;
                                    infected.put(interactions[time][0], k);
                                    infected.replace(infecter, infected.get(infecter) - 1);
                                }
                            }
                        }
                    }
                }
                check = true;
                for(int test = 0; test < cows.length; test++){
                    //System.out.println("Temps cows: " + tempCows[test]);
                    //System.out.println("Cows: " + cows[test]);
                    if(tempCows[test] != cows[test]){check = false; break;}
                }
                if(check){
                    System.out.println("winner!");
                    possibleCows++;
                    if(k > highestFoundK){highestFoundK = k;}
                    if(k < lowestFoundK){lowestFoundK = k;}
                }
            }
        }

        PrintWriter out = new PrintWriter(System.out);

        try{
            //out = new PrintWriter(new File("tracing.out"));
        }catch (Exception e){

        }

        out.print(possibleCows);

        out.print(" ");

        if(highestFoundK == Integer.MIN_VALUE){out.print(0);}
        else{out.print(lowestFoundK);}

        out.print(" ");

        if(highestFoundK == Integer.MIN_VALUE || highestFoundK == kUpper){out.print("Infinity");}
        else{out.print(highestFoundK);}

        out.print(" ");

        out.close();

    }
}