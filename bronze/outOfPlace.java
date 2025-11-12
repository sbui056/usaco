import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = sc = new Scanner(System.in);
        try{
            sc = new Scanner(new File("outofplace.in"));
        }
        catch (Exception e){

        }

        int N = sc.nextInt();

        int[] unsorted = new int[N];

        for(int i = 0; i < N; i++){
            unsorted[i] = sc.nextInt();
        }

        int[] sorted = unsorted.clone();
        Arrays.sort(unsorted);


        int swaps = 0;
        for(int index = 0; index < N; index++){
            if(unsorted[index] != sorted[index]){
                swaps++;
            }
        }
        PrintWriter out = new PrintWriter(System.out);
        try{
            out = new PrintWriter(new File("outofplace.out"));
        }
        catch (Exception e){

        }

        out.println(Math.max(0,swaps-1));

        out.close();

    }
}
