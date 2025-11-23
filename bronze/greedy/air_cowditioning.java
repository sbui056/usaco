// https://usaco.org/index.php?page=viewproblem2&cpid=1156

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] p = new int[N];
        int[] t = new int[N];

        for (int i = 0; i < N; i++) p[i] = sc.nextInt();
        for (int i = 0; i < N; i++) t[i] = sc.nextInt();

        // delta array (make one bigger to get delta for all elements)
        int[] d = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            d[i] = p[i - 1] - t[i - 1];
        }

        int res = 0;
        // sum positive increases in delta
        for (int i = 1; i <= N; i++) {
            int diff = d[i] - d[i - 1];
            if (diff > 0) {
                res += diff;
            }
        }

        System.out.println(res);
    }
}
/*
    Example Walkthrough:
        p: 1 5 3 3 4
        t: 1 2 2 2 1
        d: 0 3 1 1 3
        diff: 0, +3, -2, 0, +2
        sum only positives: 3 + 2 = 5

        you need 3 operations for the first difference
        then you need 2 operations for the last difference
        # since we are considering a range of values that we can increase,
        # we can literally chose which ones to add to

        # so we can go up (that's one operation)
        # and it doesn't cost any operations to go down or stay the same
        # this is bacuse the movement was already accounted for (like in the actual t and p, we already increased t)
*/