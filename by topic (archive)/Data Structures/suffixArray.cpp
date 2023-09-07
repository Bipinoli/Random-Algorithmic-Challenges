#include<bits/stdc++.h>

using namespace std;

vector<int> suffixArray(string s) {
    s += '$';
    int n = s.size();

    vector<int> ord(n), cls(n);
   
    {
        // k = 0
        vector<pair<char, int>> arr(n);
        for (int i=0; i<n; i++) arr[i] = {s[i], i};
        sort(arr.begin(), arr.end());

        for (int i=0; i<n; i++) ord[i] = arr[i].second;

        cls[ord[0]] = 0;
        for (int i=1; i<n; i++) {
            if (arr[i].first == arr[i-1].first)
                cls[ord[i]] = cls[ord[i-1]];
            else
                cls[ord[i]] = cls[ord[i-1]] + 1;
        }
    }

    int k = 0;
    while ((1 << k) < n) {
        // k -> k+1
        vector<pair<pair<int,int>, int>> arr(n); // ((left-class, right-class), index)
        for (int i=0; i<n; i++) {
            arr[i] = {{cls[i], cls[(i + (1 << k)) % n]}, i};
        }
        sort(arr.begin(), arr.end());

        for (int i=0; i<n; i++) ord[i] = arr[i].second;

        cls[ord[0]] = 0;
        for (int i=1; i<n; i++) {
            if (arr[i].first == arr[i-1].first)
                cls[ord[i]] = cls[ord[i-1]];
            else 
                cls[ord[i]] = cls[ord[i-1]] + 1;
        }
        k++;
    }

    // for (int i=0; i<n; i++) {
    //     cout << ord[i] << ": " << s.substr(ord[i], s.size()) << endl;
    // }

    return ord;
}

int main() {
    string s;
    cin >> s;
    
    for (auto x: suffixArray(s))
        cout << x << " ";
}