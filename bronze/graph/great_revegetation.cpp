/*

Mann Shah's solution:

We make a graph with the fields as nodes, and the preferences of cows as edges. Thus, we have the condition that the nodes of the graph are to be colored with 4 colors such that no two fields of the same color are connected by an edge. We also have an additional condition which states that no node of the graph has degree more than 3. Note that field 1 must be colored with the color 1, because, if for example field 1 had been colored with color 2, then we can do the mapping x -> (x + 3) % 4, and get the color 1 for field 1, and also it's possible to show that if (x + 3) % 4 = (y + 3) % 4, then x mod 4 = y mod 4, which implies that x = y in our case. Thus, no two vertices which had equal colors before have unequal colors now, and vice versa. Thus, we can transform the 2 -> 1 without violating the condition. Note that the first field being colored 1 is always better than the first field being colored 2, since no n-digit number with first digit 1 can be greater than an n-digit number with first digit 2. So, the first field must have color 1. Now, we inductively construct the optimal coloring. Assume that the fields 1, 2, ..., k - 1 have been colored according to the conditions, and we are now trying to color the field k. Note that the only thing we need to worry about satisfying is that no edge from k to < k, since the colors of > k haven't been decided yet, and we will decide them in later steps. Also, note that it suffices to minimize the color of vertex k while satisfying all the conditions in this step (it is possible as well, since each vertex has degree <= 3, which basically means that even if it has degree 3, and all the vertices adjacent to it and less than it have distinct colors, still it is possible to color it with the 4th color which is remaining), because of the same reason as why it was optimal to color field 1 with color 1, instead of any other color. Thus, we have the following algorithm:

- For each pair (a, b) of preferences of cows given in the input, add a bidirectional edge from a to b
- Color the vertex 1 with color 1
- For each i from 2 to n, color it with the least color that doesn't appear in previous colors that are connected to it.

(Well, this was a little long, so sorry about that, but I tried to explain everything in a sort of rigorous way)
*/
#include <bits/stdc++.h>
using namespace std;

void setIO(string name = ""){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	if (!name.empty()) {
		freopen((name + ".in").c_str(), "r", stdin);
		freopen((name + ".out").c_str(), "w", stdout);
	}
}

int main() {
	setIO("revegetate");

	int n, m;
	cin >> n >> m;

	vector<vector<int>> adj(n);

	for (int i = 0; i < m; i++) {
        int a, b;
		cin >> a >> b;

		--a, --b;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}

	vector<int> ans(n);
	ans[0] = 1;
	for (int i = 1; i < n; i++) {
		for (int k = 1; k <= 4; k++) {
			bool works = true;
            for (int j : adj[i]) {
                if (ans[j] == k) {
                    works = false;
				}
		    }
			if (works) {
				ans[i] = k;
				break;
			}
		}
	}
	for (int cow : ans) {
		cout << cow;
	}
}