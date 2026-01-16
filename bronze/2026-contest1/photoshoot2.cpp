#include <bits/stdc++.h>
using namespace std;

int main() {
    int N, K, Q;
    cin >> N >> K >> Q;

    int MAX = 0;
    int cows[500][500] = {0};      // cows[x][y] <- beauty of cow (x,y)
    int pictures[500][500] = {0};  // pictures[x][y] <- total beauty
                                   // of K x K subgrid at (x,y)

    // STEP 1: loop over all Q queries
    for (int i = 0; i < Q; i++) {
        int R, C, V;
        cin >> R >> C >> V;
        R--;
        C--;

        int add = V - cows[R][C];

        // STEP 2: update K x K subgrids that include (R,C)
        for (int rShift = 0; rShift < K; rShift++) {
            for (int cShift = 0; cShift < K; cShift++) {
                int rSubgrid = R - rShift;
                int cSubgrid = C - cShift;

                if (rSubgrid >= 0 && cSubgrid >= 0) {
                    pictures[rSubgrid][cSubgrid] += add;
                    MAX = max(MAX, pictures[rSubgrid][cSubgrid]);
                }
            }
        }

        cout << MAX << endl;   // print maximum
        cows[R][C] = V;        // save cow (R,C)'s beauty
    }
}