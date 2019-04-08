#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> next_permutation(vector<int> v) {
    // algorithm:
    // begin from the lsb
    // try to increase the number
    // if the number to be increased is at the right of the number
    //    then swap with that number
    //    sort elements to the right in ascending order
    // else 
    //    try doing that by moving left towards more significant number
    // O(n^4 * log(n))
    // but average case can be much faster due to amortizations
    // note that sorting once immediately makes next comparison a step apart
    // eg: 1 3 5 4 2 1 -> 1 4 1 2 3 5 : next swap will be between 3, 5 

    int n = v.size();
    for (int i=n-2; i>=0; i--) {
        bool swapped = false;
        int x = v[i] + 1;
        
        while (x <= n) {
            for (int j=i+1; j<n; j++) {
                if (v[j] == x) {
                    // swap
                    int temp = v[j];
                    v[j] = v[i];
                    v[i] = temp;
                    
                    sort(v.begin()+i+1, v.end());
                    
                    swapped = true;
                    break;
                }
            }
            if (swapped) break;
            x++;
        }

        if (swapped) break;
    }

    return v;
}

int main() {
    
    vector<int> v;
    for (int i=1; i<=5; i++) {
        v.push_back(i);
    }

    for (auto it: v)
        cout << it << ", ";
    cout << endl;

    while (true) {
        cout << "enter: 'n' for next-permutation" << endl;
        char c;
        if (cin >> c) {
            v = next_permutation(v);
            for (auto it: v)
                cout << it << ", ";
            cout << endl;
        }

    }

}
