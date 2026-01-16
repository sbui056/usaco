#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, K;
    cin >> N >> K;
    int Q;
    cin >> Q;

    vector<vector<int>> grid(N, vector<int>(N, 0));

    int W = N - K + 1;                 // window top-left grid size
    vector<long long> win(1LL * W * W, 0LL);  // KxK window sums (flattened)

    long long best = 0;

    for (int qi = 0; qi < Q; qi++) {
        int r, c, newVal;
        cin >> r >> c >> newVal;
        --r; --c;

        int delta = newVal - grid[r][c];
        grid[r][c] = newVal;

        // windows (i,j) whose KxK includes (r,c)
        int iLo = max(0, r - K + 1);
        int iHi = min(W - 1, r);
        int jLo = max(0, c - K + 1);
        int jHi = min(W - 1, c);

        for (int i = iLo; i <= iHi; i++) {
            int base = i * W;
            for (int j = jLo; j <= jHi; j++) {
                int idx = base + j;
                long long s = win[idx] + delta;
                win[idx] = s;
                if (s > best) best = s;
            }
        }

        cout << best << "\n";
    }

    return 0;
}