#include <bits/stdc++.h>

using namespace std;

class SuffixArray {
    private:
        string s;
        int n;

    public:
        vector<int> get(string s) {
            s += '$';
            this->s = s;
            n = s.size();
            vector<int> rank = buildRank();

            // sort according to rank
            vector<int> retval(n);
            for (int i=0; i<n; i++)
                retval[i] = i;
            sort(retval.begin(), retval.end(), [&](int l, int r) { return rank[l] <= rank[r]; } );
            return retval;
        }
        
        void display(vector<int> sfa, string s) {
            for (int i=0; i<s.size(); i++)
                cout << s.substr(sfa[i]) << endl;
        }

    private:
        vector<int> buildRank() {
            vector<int> r(n);
            // sz = 1
            {
                vector<pair<char,int>> arr(n); // char, index
                for (int i=0; i<n; i++)
                    arr[i] = {s[i], i};
                
                // sort and rank
                sort(arr.begin(), arr.end());
                for (int i=0; i<n; i++) {
                    if (i == 0) {
                        r[arr[0].second] = 0;
                        continue;
                    }
                    if (arr[i-1].first == arr[i].first)
                        r[arr[i].second] = r[arr[i-1].second];
                    else 
                        r[arr[i].second] = r[arr[i-1].second] + 1;
                }
            }

            int pow2 = nearestPowerOf2(n);
            for (int sz = 2; sz <= pow2; sz *= 2) {
                vector< pair< pair<int,int>, int> > arr(n); // value, index
                for (int i=0; i<n; i++) {
                    pair<int,int> value = {r[i]*10, r[(i + sz/2) % n]};
                    arr[i] = {value, i};
                }
                
                // sort and rank
                sort(arr.begin(), arr.end());
                for (int i=0; i<n; i++) {
                    if (i == 0) {
                        r[arr[0].second] = 0;
                        continue;
                    }
                    if (arr[i-1].first == arr[i].first)
                        r[arr[i].second] = r[arr[i-1].second];
                    else 
                        r[arr[i].second] = r[arr[i-1].second] + 1;
                }
            }
            return r;
        }


        int nearestPowerOf2(int n) {
            int retval = 1;
            while (retval < n)
                retval *= 2;
            return retval;
        }
};


int main() {
    string s;
    cin >> s;

    SuffixArray sfa;
    for (auto a: sfa.get(s)) {
        cout << a << " ";
    }  

}