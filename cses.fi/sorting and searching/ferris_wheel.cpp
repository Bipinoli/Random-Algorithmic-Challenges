/// Idea:
/// Start filling from the biggest weight
/// Choose the biggest weight that fits together (eg. binary search)
/// When the partner is chosen, it needs to removed and the array needs to be readjusted to avoid the hole in the middle
/// Shifting segment of array like this every time is too costly
/// We can't use linkedlist to avoid this as we can't do binary search there
/// So the best option is to use set datastructure in C++ which is impletemented in tree O(lgn)
/// C++ set provides lower_bound() function which returns address of value just greater or equal to the needed
/// we can use this for our task by negating the values


#include <iostream>
#include <set>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;


int n, max_wt;
set<int> st;
vector<int> lst;
map<int, int> count_map;


struct State {
    int place_left = 0;
    int wt_left = 0;
    int gondola = 0;
};

void require_new_gondola(State &state, int list_index) {
    int li = list_index;
    state.gondola += 1;
    state.place_left = 1;
    state.wt_left = max_wt - lst[li];
    count_map[lst[li]] -= 1;
    if (count_map[lst[li]] == 0) {
        st.erase(st.find(-lst[li]));
    }
}



int main() {
    cin >> n >> max_wt;

    for (int i=0; i<n; i++) {
        int a; cin >> a;
        st.insert(-a);
        lst.push_back(a);
        if (count_map.find(a) == count_map.end()) {
            count_map[a] = 0;
        }
        count_map[a] += 1;
    }

    sort(lst.begin(), lst.end());
    State state;

    int i = n-1;
    while (i >= 0) {
        if (count_map[lst[i]] == 0) {
            // item already selected
            i--;
            continue;
        }
        if (state.place_left == 0) {
            require_new_gondola(state, i);
            i--;
            continue;
        }
        auto just_smaller = st.lower_bound(-state.wt_left);
        if (just_smaller == st.end()) {
            require_new_gondola(state, i);
            i--;
        } else {
            int v = - (*just_smaller);
            state.wt_left -= v;
            state.place_left -= 1;
            count_map[v] -= 1;
            if (count_map[v] == 0) {
                st.erase(st.find(-v));
            }
        }
    }

    cout << state.gondola << endl;
    
}