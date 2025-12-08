/*
Problem: USACO 2019 February Contest, Bronze Problem 2. The Great Revegetation
Pastures = nodes
Cows = edges
Each connection connects 2 different types

Build from 1 to N
output is smallest number possible!
We start at pasture 1, give that Seed 1
Start at Pasture #1 and continue until reaching the highest numbered pasture.
For each pasture, if not touching a pasture with a determined seed type, set seed type to 1.
If its touching a pasture or pastures with determined seed types, go to the lowest possible seed # that it isn’t touching.
n may have been an input paramter to make the size of the list instead of using hashmap
separate list where the index = pasture, value = seed type

rather than sorting, maybe consider making a boolean array for the seed types of the neighbors,
 so that when you want to determine seed type for this pasture, you can just take the first non used seed type
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

//output is smallest number possible!
//
//We start at pasture 1, give that Seed 1
//
//Start at Pasture #1 and continue until reaching the highest numbered pasture.
//
//For each pasture, if not touching a pasture with a determined seed type, set seed type to 1.
//
//If its touching a pasture or pastures with determined seed types, go to the lowest possible seed # that it isn’t touching.
public class Main {
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            try {
                sc = new Scanner(new File("revegetate.in"));
            }
            catch (Exception e){

            }

            int N = sc.nextInt();
            int M = sc.nextInt();

            List<ArrayList<Integer>> map = new ArrayList<>(N+1);

            for(int pasture = 0; pasture <= N; pasture++){
                map.add(pasture, new ArrayList<>());
            }


            int[] seedTypes = new int[N+1];

            int p1,p2;

            for(int i = 0; i < M; i++){
                p1 = sc.nextInt();
                p2 = sc.nextInt();
                ArrayList<Integer> placeholder1 = new ArrayList<>();
                for(int p: map.get(p1-1)){
                    placeholder1.add(p);
                }
                if(!placeholder1.contains(p2)){placeholder1.add(p2);}
                map.remove(p1-1);
                map.add(p1-1,placeholder1);

                ArrayList<Integer> placeholder2 = new ArrayList<>();

                for(int p: map.get(p2-1)){
                    placeholder2.add(p);
                }
                if(!placeholder2.contains(p1)){placeholder2.add(p1);}
                map.remove(p2-1);
                map.add(p2-1, placeholder2);

            }

            ArrayList<Integer> seeds = new ArrayList<>();
            for(int pasture = 1; pasture <= N; pasture++){

                seeds.clear();
                for(int neighbor: map.get(pasture-1)){

                    if(seedTypes[neighbor] != 0){
                        seeds.add(seedTypes[neighbor]);
                    }
                }

                int largestSeed = 0;

                for(int num: seeds){
                    if(num > largestSeed){largestSeed = num;}
                }

                if(seeds.isEmpty()){seedTypes[pasture] = 1;}
                else if(!seeds.contains(largestSeed-2) && largestSeed-2 >= 1){seedTypes[pasture] = largestSeed-2;}
                else if(!seeds.contains(largestSeed-1) && largestSeed-1 >= 1){seedTypes[pasture] = largestSeed-1;}
                else{seedTypes[pasture] = largestSeed+1;}

            }

            String printOut = "";
            for(int i = 1; i < N+1; i++){
                printOut = printOut + seedTypes[i];
            }

            PrintWriter out = new PrintWriter(System.out);
            try {
                out = new PrintWriter(new File("revegetate.out"));
            }
            catch (Exception e){

            }
            out.println(printOut);
            out.close();

            for(int pasture = 1; pasture <= N; pasture++){
                System.out.print(pasture + ": ");
                for(int p: map.get(pasture-1)){
                    System.out.print(p + ", ");
                }
                System.out.println();
            }


    }
}