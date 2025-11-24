/*

Find the set of all possible bad milks
Consider each of these bad milks
Consider the number of people that drank this
Keep track of the max for people that drank a potentially bad milk
This max is the min dosages that we need

*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

struct Drink {
    int person;
    int milk;
    int time;
};

struct Sickness {
    int person;
    int time;
};

void solve() {
    int N, M, D, S;
    if (!(cin >> N >> M >> D >> S)) return;

    vector<Drink> drinks(D);
    for (int i = 0; i < D; ++i) {
        cin >> drinks[i].person >> drinks[i].milk >> drinks[i].time;
    }

    vector<Sickness> sicknesses(S);
    for (int i = 0; i < S; ++i) {
        cin >> sicknesses[i].person >> sicknesses[i].time;
    }

    set<int> potential_bad_milks;

    for (int m = 1; m <= M; ++m) {
        bool is_milk_bad = true;

        for (const auto& sick : sicknesses) {
            int sick_person = sick.person;
            int time_sick = sick.time;
            
            bool drank_before_sick = false;

            for (const auto& d : drinks) {
                if (d.person == sick_person && d.milk == m && d.time < time_sick) {
                    drank_before_sick = true;
                    break;
                }
            }

            if (!drank_before_sick) {
                is_milk_bad = false;
                break;
            }
        }

        if (is_milk_bad) {
            potential_bad_milks.insert(m);
        }
    }

    int max_doses = 0;

    for (int bad_milk_m : potential_bad_milks) {
        set<int> people_who_drank_this_milk;

        for (const auto& d : drinks) {
            if (d.milk == bad_milk_m) {
                people_who_drank_this_milk.insert(d.person);
            }
        }

        max_doses = max(max_doses, (int)people_who_drank_this_milk.size());
    }

    cout << max_doses << endl;
}

int main() {
    solve();
    return 0;
}