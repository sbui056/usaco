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