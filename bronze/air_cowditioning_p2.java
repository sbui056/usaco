/*

Approach 2: 
desired: 1 5 3 3 4
initial: 1 2 2 2 1
difference:  0 3 1 1 3 -> 0 0 0 0 0
delta:        +3 -2 0 +2

delta track changes necessary

answer = 5
sum up positive elements in delta: 3 + 2 = 5


Approach 1: 
|d_i - d_{i+1}| operations to make both d_i and d_{i+1} equal, where D is the difference array between p and t
Count the number of operations needed
Then sum it up
Over overcounting 
desired: 1 5 3 3 4
initial: 1 2 2 2 1
difference:        0 3 1 1 3
|d_i - d_{i+1}|: 0 3 2 0 2 3
sum(|d_i - d_{i+1}|) = 3 + 2 + 2 + 3 = 10
avoid overcounting: 10/2 = 5

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    //A Move is an int[] where:
    //index 0 is the span of the movemtn
    //index 1 is the start index
    //index 2 is the end index
    /*
    public static ArrayList<int[]> getMoves(int[] current, int[] desired){


    }

     */

    public static int spanValue(int[] array, int start, int end){
        int value = 0;
        for(int check = start; check <= end; check++){
            value += array[check];
        }

        return value;
    }

    public static int[] getMaxSpanIncrease(int[] toZero){
        int max = 0;
        int start = 0;
        int end = 0;
        int val;
        int[] move = new int[3];
        for(int cow = 0; cow <= toZero.length-1; cow++){
            if(toZero[cow] < 0) {
                for(int i = toZero.length - 1; i >= cow; i--){
                    if(toZero[i] < 0){
                        val = spanValue(toZero, cow, i);
                        if (val < 0 && i-cow > max) {
                            start = cow;
                            end = i;
                            max = i - cow;
                        }
                    }
                }
            }
        }
        move[0] = max;
        move[1] = start;
        move[2] = end;
        return move;
    }
    public static int[] getMaxSpanDecrease(int[] toZero){
        int max = 0;
        int start = 0;
        int end = 0;
        int val;
        int[] move = new int[3];
        for(int cow = 0; cow <= toZero.length-1; cow++){
            if(toZero[cow] > 0) {
                for (int i = toZero.length - 1; i >= cow; i--) {
                    if(toZero[i] > 0) {
                        val = spanValue(toZero, cow, i);
                        if (val > 0 && i-cow > max) {
                            start = cow;
                            end = i;
                            max = i - cow;
                        }
                    }
                }
            }
        }
        move[0] = max;
        move[1] = start;
        move[2] = end;
        return move;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] start = new int[N];
        int[] desired = new int[N];

        for(int i = 0; i < N; i++){
            desired[i] = sc.nextInt();
        }
        for(int i = 0; i < N; i++){
            start[i] = sc.nextInt();

        }

        int[] differenceArray = new int[N];

        for(int i = 0; i < N; i++){
            differenceArray[i] = desired[i]-start[i];
        }

        int[] maxIncrease;
        int[] maxDecrease;

        boolean done = true;
        for(int check = 0; check < differenceArray.length; check++){
            //System.out.print(differenceArray[check] + ", ");
            if(differenceArray[check] !=0){done=false;}
        }
        //System.out.println();
        int reps = 0;

        while(!done && reps < 10){
            reps++;
            maxIncrease = getMaxSpanIncrease(differenceArray);
            maxDecrease = getMaxSpanDecrease(differenceArray);
            if (maxIncrease[0] >= maxDecrease[0]){
                //System.out.println(maxIncrease[0]);
                //System.out.println("Increase between " + maxIncrease[1] + " and " + maxIncrease[2] + ".");
                for(int i = maxIncrease[1]; i <= maxIncrease[2]; i++){differenceArray[i]++;}
            }
            else if (maxDecrease[0] > maxIncrease[0]){
                //System.out.println(maxDecrease[0]);
                //System.out.println("Decrease between " + maxDecrease[1] + " and " + maxDecrease[2] + ".");
                for(int i = maxDecrease[1]; i <= maxDecrease[2]; i++){differenceArray[i]--;}
            }

            done = true;
            for(int check = 0; check < differenceArray.length; check++){
                //System.out.print(differenceArray[check] + ", ");
                if(differenceArray[check] !=0){done=false;}
            }
            //System.out.println();
        }
        System.out.println(reps);

    }
}

Approach 1: 
|d_i - d_{i+1}| operations to make both d_i and d_{i+1} equal, where D is the difference array between p and t
Count the number of operations needed
Then sum it up
avoid overcounting 

// c++ code
#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<int> p(n), t(n);
    for (int i = 0; i < n; i++) cin >> p[i];
    for (int i = 0; i < n; i++) cin >> t[i];

    vector<int> d(n + 2, 0);
    for (int i = 1; i <= n; i++) {
        d[i] = p[i-1] - t[i-1];
    }

    long long sum = 0;
    for (int i = 0; i <= n; i++) {
        sum += abs(d[i+1] - d[i]);
    }

    cout << sum / 2 << "\n";
}

Approach 2: 
Consider the delta between i and i+1 values in the difference array,
We only sum up the positive deltas