// https://codeforces.com/problemset/problem/1709/A

// recursion for more than 3 doors!

import java.util.Scanner;

public class Main {

    public static boolean door(int keyHeld, int[] doors){
        boolean finished = true;
        for(int status: doors){
            if(status != -1){
                finished = false;
            }
        }
        if(finished){
            return true;
        }
        if(keyHeld == 0){
            return false;
        }
        int[] newDoors = doors.clone();
        int newKeyHeld = doors[keyHeld-1];
        newDoors[keyHeld-1] = -1;
        return door(newKeyHeld, newDoors);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int keyHeld;
        int[] doors;
        boolean finish;
        String[] output = new String[T];
        for(int i = 0; i < T; i++) {
            keyHeld = sc.nextInt();
            doors = new int[3];
            doors[0] = sc.nextInt();
            doors[1] = sc.nextInt();
            doors[2] = sc.nextInt();
            finish = door(keyHeld, doors);
            if(finish){output[i] = "YES";}
            else{output[i] = "NO";}
        }
        for(String entry: output){
            System.out.println(entry);
        }
    }
}