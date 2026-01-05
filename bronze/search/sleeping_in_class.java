import java.util.Scanner;

public static void solve(Scanner sc) {
    int n = sc.nextInt();
    int[] a = new int[n];
    int sum = 0;

    for (int i = 0; i < n; i++) {
        a[i] = sc.nextInt();
        sum += a[i];
    }

    for (int r = n; r >= 1; r--) {
        if (sum % r != 0) continue;

        int target = sum / r;
        int currentSum = 0;
        boolean valid = true;

        // greedy make r segments
        for (int i = 0; i < n; i++) {
            currentSum += a[i];

            if (currentSum > target) {
                // not possible divisor!
                valid = false;
                break;
            }

            if (currentSum == target) {
                // valid segment divisor, start a new count for next segment
                currentSum = 0;
            }
        }

        // if successful, print min operations
        if (valid) {
            System.out.println(n - r);
            return;
        }
    }
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    for (int i = 0; i < t; i++) {
        solve(sc);
    }
    sc.close();
}