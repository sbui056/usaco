// INPUT
// k meters race, num of diff X's
// diff X's: no more than X speed at the end

import java.io.*;
import java.util.*;

public class Main {

    // Solves one query: given distance K and ending speed limit X
    public static int solve(int dist, int minSpeed) {
        int leftTravel = 0;   // distance while speeding up
        int rightTravel = 0;  // distance while slowing down
        int timeUsed = 0;

        // Try increasing speed forever until we exceed distance
        for (int currSpeed = 1; ; currSpeed++) {

            // Speeding up phase
            leftTravel += currSpeed;
            timeUsed++;

            if (leftTravel + rightTravel >= dist) {
                return timeUsed;
            }

            // Slowing down phase (only allowed if currSpeed >= X)
            if (currSpeed >= minSpeed) {
                rightTravel += currSpeed;
                timeUsed++;

                if (leftTravel + rightTravel >= dist) {
                    return timeUsed;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("race.in"));
        PrintWriter out = new PrintWriter(new FileWriter("race.out"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // distance
        int N = Integer.parseInt(st.nextToken()); // number of queries

        for (int i = 0; i < N; i++) {
            int X = Integer.parseInt(br.readLine());
            out.println(solve(K, X));
        }

        out.close();
        br.close();
    }
}