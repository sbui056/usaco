// usaco editorial for java recursive
import java.io.*;
import java.util.*;
public class Alchemy {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(in.readLine());
    StringTokenizer st = new StringTokenizer(in.readLine());
    int[] have = new int[n];
    for(int i = 0; i < n; i++) have[i] = Integer.parseInt(st.nextToken());
    int ans = 0;
    int[][] recipes = new int[n][];
    int k = Integer.parseInt(in.readLine());
    while(k-- > 0) {
      st = new StringTokenizer(in.readLine());
      int gain = Integer.parseInt(st.nextToken())-1;
      recipes[gain] = new int[Integer.parseInt(st.nextToken())];
      for(int i = 0; i < recipes[gain].length; i++) recipes[gain][i] = Integer.parseInt(st.nextToken())-1;
    }
    while(canMake(recipes, have, n-1)) ans++;
    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    out.println(ans);
    out.close();
  }
  private static boolean canMake(int[][] recipes, int[] have, int want) {
    if(have[want] > 0) {
      have[want]--;
      return true;
    }
    if(recipes[want] == null) return false;
    for(int component: recipes[want]) if(!canMake(recipes, have, component)) return false;
    return true;
  }
}

// attempt to implement
//recursive_function_name(recipies, inventory, goal_metal)

//Try to make the goal_metal until goal_metal == 1 which is base case since we can’t make a 1

//you want to make the N metal, so call the recusive function to see if you can make N and then if you can’t call it’s subchildren, which would be the ingredients to make it

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int[] inventory;
    public static List<int[]> recipies;

    public static boolean checkMetal(int metal){

        boolean check = true;
        boolean result;

        int[] inventorySave = inventory.clone();
        //System.out.println("Checking: " + metal);
        if(inventory[metal] >= 1){
            //System.out.println("Using 1: " + metal + ", now " + (inventory[metal]-1) + " left.");
            inventory[metal] -= 1;
            check = true;
        }
        else if(recipies.get(metal).length > 1){
            for(int ingredient = 1; ingredient < recipies.get(metal).length; ingredient++){
                result = checkMetal(recipies.get(metal)[ingredient]);
                //System.out.println("Metal #" + metal + ", Ingredient: " + recipies.get(metal)[ingredient] + ": " + result);
                if(!result){check = false; break;}
            }
        }
        else{
            check = false;
        }
        if(!check){
            inventory = inventorySave;
        }
        return check;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        inventory = new int[N+1];

        for(int i = 1; i <= N; i++){inventory[i] = sc.nextInt();}

        int K = sc.nextInt();

        recipies = new ArrayList<>(N);

        for(int i =0; i<=N; i++){
            int[] placeholder = new int[0];
            recipies.add(placeholder);
        }

        int M,L;
        for(int i = 1; i <= K; i++) {
            M = sc.nextInt();
            L = sc.nextInt();
            int[] placeholder = new int[L+1];

            for(int j = 1; j <= L; j++){
                placeholder[j] = sc.nextInt();
            }
            recipies.set(M,placeholder);
        }
        PrintWriter out = new PrintWriter(System.out);
        //for(int i = 1; i <= N; i++){System.out.println(inventory[i]);}
        int total = 0;
        boolean check = true;
        while(check){
            if(!checkMetal(N)){check = false;}
            else{total++;}
        }

        out.println(total);

        out.close();

    }
}