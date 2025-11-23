/*
import java.io.;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Scanner;
import java.util.;

public class Main {

    public static void main(String[] args) {
        Scanner sc = sc = new Scanner(System.in);
        try{
            sc = new Scanner(new File("guess.in"));
            //sc = new Scanner(System.in);
        }
        catch (Exception e){

        }

        int N = sc.nextInt();

        HashMap<String, String[]> animals = new HashMap<>();

        HashMap<String, Integer> traitsByNumber = new HashMap<>();

        int maxShared = 0;

        String name;
        int num;
        for(int i = 0; i < N; i++){
            name = sc.next();
            num = sc.nextInt();
            String[] characteristics = new String[num];
            for(int j = 0; j < num; j++){
                characteristics[j] = sc.next();
            }
            animals.put(name, characteristics);
            for(String trait: characteristics){
                if(!traitsByNumber.containsKey(trait)){
                    traitsByNumber.put(trait, 0);
                }
                traitsByNumber.put(trait,traitsByNumber.get(trait)+1);
            }
        }
        int animalShared = 0;
        for(String animal: animals.keySet()){
            animalShared = 0;
            for(String trait: animals.get(animal)){
                if(traitsByNumber.get(trait) >= 2){animalShared++;}
            }
            if(animalShared>maxShared){
                maxShared = animalShared;
            }
        }

        System.out.println(maxShared+1);
        PrintWriter out = new PrintWriter(System.out);
        try{
            out = new PrintWriter(new File("guess.out"));
        }
        catch (Exception e){

        }
        out.println(maxShared + 1);

        out.close();

    }
}
 */
// https://usaco.org/index.php?page=viewproblem2&cpid=893 
// max common characteristics + 1
// check for the max number of shared characterisics between TWO animals

import java.io.*;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = sc = new Scanner(System.in);
        try{
            sc = new Scanner(new File("guess.in"));
            //sc = new Scanner(System.in);
        }
        catch (Exception e){

        }

        int N = sc.nextInt();

        HashMap<String, String[]> animals = new HashMap<>();

        int maxShared = 0;

        String name;
        int num;
        for(int i = 0; i < N; i++){
            name = sc.next();
            num = sc.nextInt();
            String[] characteristics = new String[num];
            for(int j = 0; j < num; j++){
                characteristics[j] = sc.next();
            }
            animals.put(name, characteristics);
        }
        int animalShared;
        for(String animal: animals.keySet()){
            for(String animalSharer: animals.keySet()){
                animalShared = 0;
                if(!animalSharer.equals(animal)){
                    for(String trait: animals.get(animal)){
                        for(int i = 0; i < animals.get(animalSharer).length; i++){
                            if(animals.get(animalSharer)[i].equals(trait)){
                                animalShared++;
                                break;
                            }
                        }
                    }
                }
                if(animalShared>maxShared){
                    maxShared = animalShared;
                }
            }

        }

        System.out.println(maxShared+1);
        PrintWriter out = new PrintWriter(System.out);
        try{
            out = new PrintWriter(new File("guess.out"));
        }
        catch (Exception e){

        }
        out.println(maxShared + 1);

        out.close();

    }
}